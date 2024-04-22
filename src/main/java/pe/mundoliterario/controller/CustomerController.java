package pe.mundoliterario.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.mundoliterario.entity.Customer;
import pe.mundoliterario.entity.Login;
import pe.mundoliterario.entity.jwt.JwtCustomer;
import pe.mundoliterario.jwt.JwtGeneratorCustomer;
import pe.mundoliterario.service.CustomerService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
//@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private JwtGeneratorCustomer jwtGenerator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//almacena temporalmente los códigos de restablecimiento
	private Map<String, String> resetCodeMap = new HashMap<>();
	
	public CustomerController() {
	}
	
	
	@PostMapping("/customer/login")
	public ResponseEntity<String> generateToken_POST(@RequestBody Login login) {
	    if (login.getEmail() == null || login.getEmail().isEmpty() || login.getPassword() == null || login.getPassword().isEmpty()) {
	        System.out.println("Correo electrónico o contraseña nulos.");
	        return new ResponseEntity<>("Correo electrónico o contraseña nulos", HttpStatus.BAD_REQUEST);
	    }

	    Customer customer = customerService.find_email(login.getEmail());
	    if (customer != null && passwordEncoder.matches(login.getPassword(), customer.getCustomer_password())) {
	    	if (customer.getCustomer_status().equals("ACTIVO")) {
		    	String token = generateConfirmationToken(customer);
		        System.out.println("Inicio de sesión exitoso para el usuario: " + customer.getCustomer_email());
		        return new ResponseEntity<>(token, HttpStatus.OK);
	    	}
	    	else {
	            System.out.println("La cuenta no está activa para el usuario: " + login.getEmail());
	            return new ResponseEntity<>("La cuenta no está activa", HttpStatus.UNAUTHORIZED);
	        }
	    }

	    System.out.println("Email o contraseña incorrecta para el correo electrónico: " + login.getEmail());
	    return new ResponseEntity<>("Correo electrónico o contraseña incorrecta", HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/auth/customer/list")
	public ResponseEntity<?> list_customers(){
		Collection<Customer> collection = customerService.list();
		
		if(collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection,HttpStatus.OK);
	}

	private String generateConfirmationToken(Customer customer) {
		JwtCustomer jwtCustomer = new JwtCustomer(customer.getCustomer_id(), customer.getCustomer_email());
	    String token = this.jwtGenerator.generateToken(jwtCustomer);
	    return token;
   }
	
	@PostMapping("/customer/register")
	public ResponseEntity<?> register_customer(@RequestBody Customer customer) {
	    Customer c = customerService.find_email(customer.getCustomer_email());
	    
	    if(c != null) {
	        // El email ya existe, retornar una respuesta de error
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Ya tiene una cuenta, inicie sesión.");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    } else {
	    	//Registrar cliente
	    	customer.setCustomer_password(passwordEncoder.encode(customer.getCustomer_password()));
	    	 customer.setCustomer_status("PENDIENTE");
	    	 
	    	// Generar y asociar un token único para la confirmación por correo 
	         String confirmationToken = generateConfirmationToken(customer); // Genera un token JWT para la confirmación
	         customer.setCustomer_token(confirmationToken);
	         
	         customerService.create(customer);
	         
	         // Enviar correo electrónico de confirmación
	         sendConfirmationEmail(customer.getCustomer_email(),customer.getCustomer_surnames()+" "+customer.getCustomer_names(), confirmationToken); // método para enviar el correo
	        
	        customerService.create(customer);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Se ha registrado correctamente.");
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }
	}
	
	private void sendConfirmationEmail(String recipientEmail, String customerName, String confirmationToken) {
	    // propiedades para el servidor de correo de Gmail
	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    
	    //Configurar la autenticación con cuenta propia de Gmail
	    String username = "econewscontacto@gmail.com";
	    String password = "jgjo jygl trde rbtp"; //contraseña de aplicaciones
	    Authenticator authenticator = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    };

	    // Crear la sesión de correo
	    Session session = Session.getInstance(properties, authenticator);

	    try {
	        // Mensaje de correo
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username)); // correo del remitente
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); //correo del destinatario
	        message.setSubject("Confirmación de Registro"); // Asunto del correo
	        message.setText("Hola " + customerName + ",\n\n"
	                        + "Gracias por registrarte en nuestra página web. Para confirmar tu cuenta, haz clic en el siguiente enlace:\n\n"
	                        + "http://localhost:4200/mundo-literario/confirmation?token=" + confirmationToken + "\n\n"
	                        + "Este enlace solo estará disponible por 24 horas. ¡No olvides confirmar tu cuenta!\n\n"
	                        + "Atentamente,\n"
	                        + "Mundo Literario"); // Cuerpo del correo
	        
	        // Enviar el mensaje de correo
	        Transport.send(message);

	        System.out.println("Correo de confirmación enviado correctamente a: " + recipientEmail);
	    } catch (MessagingException e) {
	        System.out.println("Error al enviar el correo de confirmación: " + e.getMessage());
	    }
	}
	
	// metodo que verifica la confirmación del correo
	@GetMapping("/customer/confirm/{token}")
	public ResponseEntity<?> confirmAccount(@PathVariable String token) {
	    Customer customer = customerService.findByConfirmationToken(token);
	    
	    if (customer != null) {
	        // Confirmar la cuenta del usuario
	    	customer.setCustomer_status("ACTIVO");
	        customer.setCustomer_token(null); // Eliminar el token de confirmación
	        
	        // Guardar el cambio en la base de datos
	        customerService.update(customer);
	        
	        return ResponseEntity.ok().build(); // Cuenta confirmada exitosamente
	    } else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un usuario con ese token");
	    }
	}
	
	private String generateResetCode() {
	    // Generar un código aleatorio de 6 dígitos
	    Random random = new Random();
	    int code = 100000 + random.nextInt(900000); // Números aleatorios de 100000 a 999999
	    
	    return String.valueOf(code);
	}
	
	
	@PostMapping("/customer/sendResetCode")
	public ResponseEntity<?> sendResetCode(@RequestBody String email) {
	    Customer customer = customerService.find_email(email);
	    
	    if (customer != null) {
	        // Generar y enviar el código de restablecimiento por correo electrónico
	        String resetCode = generateResetCode();
	        resetCodeMap.put(email, resetCode); // Almacenar temporalmente el código
	        sendResetCodeEmail(email, resetCode);
	        
	        return ResponseEntity.ok().build(); // Código de restablecimiento enviado correctamente
	    } else {
	        return ResponseEntity.notFound().build(); // No se encontró un usuario con ese correo electrónico
	    }
	}

	@PostMapping("/customer/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> requestData) {
	    String resetCode = requestData.get("resetCode");
	    String newPassword = requestData.get("newPassword");
	    String email = requestData.get("email"); // También se debería enviar el correo electrónico del usuario
	    
	    // Verificar si el código de restablecimiento coincide con el almacenado temporalmente
	    if (resetCodeMap.containsKey(email) && resetCodeMap.get(email).equals(resetCode)) {
	        // Actualizar la contraseña del usuario
	        Customer customer = customerService.find_email(email);
	        if (customer != null) {
	            customer.setCustomer_password(passwordEncoder.encode(newPassword));
	            customerService.update(customer);
	            
	            // Eliminar el código de restablecimiento después de usarlo
	            resetCodeMap.remove(email);
	            
	            return ResponseEntity.ok().build(); // Contraseña restablecida correctamente
	        }
	    }
	    
	    return ResponseEntity.notFound().build(); // El código de restablecimiento no es válido
	}
	
	private void sendResetCodeEmail(String recipientEmail, String resetCode) {
	    // Configurar las propiedades para el servidor de correo de Gmail
	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    
	    // Configurar la autenticación con tu cuenta de Gmail
	    String username = "econewscontacto@gmail.com";
	    String password = "jgjo jygl trde rbtp"; //contraseña de aplicaciones
	    Authenticator authenticator = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    };

	    // Crear la sesión de correo
	    Session session = Session.getInstance(properties, authenticator);

	    try {
	        // Crear el mensaje de correo
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username)); // Correo del remitente
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Correo del destinatario
	        message.setSubject("Código de restablecimiento de contraseña"); // Asunto del correo
	        String mensajeHtml = "<p>Hola,</p>"
	                + "<p>Recibiste este correo electrónico porque solicitaste restablecer tu contraseña en Mundo Literario. "
	                + "Tu código de restablecimiento es: <strong>" + resetCode + "</strong></p>"
	                + "<p>Si no solicitaste este restablecimiento, puedes ignorar este correo electrónico.</p>"
	                + "<p>Atentamente,<br/>Mundo Literario</p>";

	        // Configurar el contenido del mensaje como HTML
	        message.setContent(mensajeHtml, "text/html");
	        
	        // Enviar el mensaje de correo
	        Transport.send(message);

	        System.out.println("Correo de restablecimiento de contraseña enviado correctamente a: " + recipientEmail);
	    } catch (MessagingException e) {
	        System.out.println("Error al enviar el correo de restablecimiento de contraseña: " + e.getMessage());
	    }
	}
	
	
	@PutMapping("/auth/customer/update/{customer_id}")
	public ResponseEntity<?> update_customer(@RequestBody Customer customer,
			                            @PathVariable Integer customer_id)
	{
		Map<String, String> response = new HashMap<>();
		Customer customerDb=customerService.find_id(customer_id);
		
		if(customerDb!=null){
			
			customer.setCustomer_id(customer_id);
	       
	        if (!customer.getCustomer_status().isEmpty()) {
	            customer.setCustomer_status(customer.getCustomer_status());
	        } else {
	        	 customer.setCustomer_status(customerDb.getCustomer_status());
	        }
	        
	        
	        // Verificar si se proporcionó una nueva contraseña para actualizarla
	        if (!customer.getCustomer_password().isEmpty()) {
	            customer.setCustomer_password(passwordEncoder.encode(customer.getCustomer_password()));
	        } else {
	            // Si no se proporciona una nueva contraseña, mantener la contraseña original
	            customer.setCustomer_password(customerDb.getCustomer_password());
	        }
			
			customerService.update(customer);
			
			 response.put("message", "Datos actualizados correctamente.");
		        return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		response.put("message", "No se encontró el clienteo a editar.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	
	
	@DeleteMapping("/auth/customer/eliminar/{customer_id}")
	public ResponseEntity<?> delete_customer( @PathVariable Integer customer_id)
	{
		Customer customerDb=customerService.find_id(customer_id);
		
		if(customerDb!=null){
			
			customerService.delete(customer_id);
			
			return new ResponseEntity<>("Cliente eliminado correctamente.",HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Cliente no existe.",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/auth/customer/buscar/{customer_id}")
	public ResponseEntity<?> find_customer_id( @PathVariable Integer customer_id)
	{
		Customer customerDb=customerService.find_id(customer_id);
		
		if(customerDb!=null){
			
			return new ResponseEntity<>(customerDb,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Cliente no encontrado.",HttpStatus.NOT_FOUND);
	}


}