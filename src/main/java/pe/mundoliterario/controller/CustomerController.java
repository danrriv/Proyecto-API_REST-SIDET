package pe.mundoliterario.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

@RestController
//@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private JwtGeneratorCustomer jwtGenerator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public CustomerController() {
	}
	
	@PostMapping("/customer/login")
	public ResponseEntity<String> generateToken_POST(@RequestBody Login login){
	    
		
		Customer customer = customerService.find_email(login.getEmail());
	    if(customer != null && passwordEncoder.matches(login.getPassword(), customer.getCustomer_password())) {
	        JwtCustomer jwtCustomer = new JwtCustomer(customer.getCustomer_id(), customer.getCustomer_email());
	        String token = this.jwtGenerator.generateToken(jwtCustomer);
	        System.out.println("Inicio de sesión exitoso para el usuario: " + customer.getCustomer_email());
	        return new ResponseEntity<String>(token, HttpStatus.OK);
	    }
	    System.out.println("Usuario o contraseña incorrecta del usuario" + customer.getCustomer_email());
	    return new ResponseEntity<>("Usuario o contraseña incorrecta",HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/auth/customer/list")
	public ResponseEntity<?> list_customers(){
		Collection<Customer> collection = customerService.list();
		
		if(collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection,HttpStatus.OK);
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
	        customer.setCustomer_status("ACTIVO");
	        
	        customerService.create(customer);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Se ha registrado correctamente.");
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
