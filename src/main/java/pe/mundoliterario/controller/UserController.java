package pe.mundoliterario.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.mundoliterario.entity.Login;
import pe.mundoliterario.entity.Role;
import pe.mundoliterario.entity.User;
import pe.mundoliterario.entity.jwt.JwtCustomer;
import pe.mundoliterario.jwt.JwtGeneratorCustomer;
import pe.mundoliterario.service.RoleService;
import pe.mundoliterario.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtGeneratorCustomer jwtGenerator;

	public UserController() {
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<?> login_user(@RequestBody Login login) {
		if (login.getEmail() == null || login.getEmail().isEmpty() || login.getPassword() == null || login.getPassword().isEmpty()) {
	        System.out.println("Correo electrónico o contraseña nulos.");
	        return new ResponseEntity<>("Correo electrónico o contraseña nulos", HttpStatus.BAD_REQUEST);
	    }
		
		User user = userService.find_email(login.getEmail());
		if(user !=null && passwordEncoder.matches(login.getPassword(), user.getUser_password())) {
			if (user.getUser_status().equals("ACTIVO")) {
				String token = generateToken(user);
				user.setUser_token(token);
				userService.update(user);
				System.out.println("Inicio de sesión exitoso para el usuario: " + user.getUser_email());
				// Crear un Map para almacenar los datos del usuario
	            Map<String, String> userData = new HashMap<>();
	            userData.put("token", token);
	            userData.put("names",user.getUser_names());
	            userData.put("surnames",user.getUser_surnames());
	            userData.put("role", user.getRole().getRole_name());

	            // Devolver el Map como parte de la respuesta
	            return ResponseEntity.ok(userData);
			}
			else {
	            System.out.println("La cuenta no está activa para el usuario: " + login.getEmail());
	            return new ResponseEntity<>("Su cuenta esta inactiva, comuníquese con el administrador", HttpStatus.UNAUTHORIZED);
	        }
			
		}
		
		 System.out.println("Email o contraseña incorrecta para el correo electrónico: " + login.getEmail());
		    return new ResponseEntity<>("Email o contraseña incorrecta", HttpStatus.UNAUTHORIZED);
	
	}
	
	private String generateToken(User user) {
		JwtCustomer jwtCustomer = new JwtCustomer(user.getUser_id(), user.getUser_email());
	    String token = this.jwtGenerator.generateToken(jwtCustomer);
	    return token;
   }
	
	@GetMapping("/user/list")
	public ResponseEntity<?> list_users(){
		Collection<User> collection = userService.list();
		
		if(collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection,HttpStatus.OK);
	}

	@PostMapping("/user/create")
	public ResponseEntity<?> create_user(@RequestBody User user)
	{
		
		User userbd = userService.find_email(user.getUser_email());
		    
		if(userbd != null) {
		     // El email ya existe, retornar una respuesta de error
		     Map<String, String> response = new HashMap<>();
		     response.put("message", "Ya tiene una cuenta, inicie sesión.");
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		else {
			user.setUser_password(passwordEncoder.encode(user.getUser_password()));		
			user.setUser_status("ACTIVO");
			userService.create(user);		
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Usuario registrado correctamente.");
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
	}
	
	@PutMapping("/user/update/{user_id}")
	public ResponseEntity<?> update_user(@RequestBody User user,
			                            @PathVariable Integer user_id)
	{
		Map<String, String> response = new HashMap<>();
		User userDb=userService.find_id(user_id);
		
		if(userDb!=null){
			
			user.setUser_id(user_id);
			
			// Actualizar el estado del usuario si está presente en la solicitud
	        if (user.getUser_status() != null) {
	            userDb.setUser_status(user.getUser_status());
	        }
	        
	        // Actualizar la contraseña del usuario si está presente en la solicitud
	        if (user.getUser_password() != null) {
	            userDb.setUser_password(passwordEncoder.encode(user.getUser_password()));
	        }
	        
	        // Actualizar el teléfono del usuario si está presente en la solicitud
	        if (user.getUser_phone_number() != null) {
	            userDb.setUser_phone_number(user.getUser_phone_number());
	        }
	        
	        if (user.getRole() != null) {
	            userDb.setRole(user.getRole());
	        }
	        
	        userService.update(userDb);
	        
			 response.put("message", "Se ha actualizado sus datos correctamente.");
		        return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		response.put("message", "No se encontró al usuario a editar.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@GetMapping("/user/findId/{user_id}")
	public ResponseEntity<?> find_user_id( @PathVariable Integer user_id)
	{
		User userDb=userService.find_id(user_id);
		
		if(userDb!=null){
			
			return new ResponseEntity<>(userDb,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Usuario no encontrado.",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/user/findSurnames/{surnames}")
	public ResponseEntity<?> find_user_surnames( @PathVariable String surnames)
	{
		User userDb=userService.findBySurnames(surnames);
		
		if(userDb!=null){
			
			return new ResponseEntity<>(userDb,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Usuario no encontrado.",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/role/listRoles")
	public ResponseEntity<?> listRoles()
	{
		Collection<Role> collection = roleService.listRoles();
		
		if(collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection,HttpStatus.OK);
	}
	
	@GetMapping("/role/findRoleId/{role_id}")
	public ResponseEntity<?> find_role_id( @PathVariable Integer role_id)
	{
		Role roleDb=roleService.find_idRole(role_id);
		
		if(roleDb!=null){
			
			return new ResponseEntity<>(roleDb,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Rol no encontrado.",HttpStatus.NOT_FOUND);
	}

	@PostMapping("/user/logout/{token}")
	public ResponseEntity<?> logout_user(@PathVariable String token) {
	    User user = userService.findByToken(token);
	    
	    if (user != null) {
	        // Confirmar la cuenta del usuario
	    	user.setUser_token(null); // Eliminar el token
	        
	        // Guardar el cambio en la base de datos
	    	userService.update(user);
	        
	        return ResponseEntity.ok().build();
	    } else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un usuario con ese token");
	    }
	}
}
