package pe.mundoliterario.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import pe.mundoliterario.entity.jwt.JwtCustomer;

@Component
public class JwtValidatorCustomer {
	public JwtCustomer validateToken(String token) {
		JwtCustomer jwtCustomer = null;
		
		try {
			//recupera cifrado interno
			Claims claims = Jwts.parser().setSigningKey("PROYECTO_SECRETO").parseClaimsJws(token).getBody();
			jwtCustomer = new JwtCustomer();
			
			//cargar jwtCustomer
			jwtCustomer.setCustomer_id(Integer.parseInt(claims.get("customer_id").toString()));
			jwtCustomer.setCustomer_email(claims.getSubject());
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return jwtCustomer;
	}
}
