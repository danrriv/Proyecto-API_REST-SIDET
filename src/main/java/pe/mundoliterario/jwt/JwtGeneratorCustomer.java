package pe.mundoliterario.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.mundoliterario.entity.jwt.JwtCustomer;

@Component
public class JwtGeneratorCustomer {
	private int duracion = 24 * 60 * 60 * 1000; //milisegundos (24 horas)

	public String generateToken(JwtCustomer jwtCustomer) {
		String token = null;
		
		try {
			
			Date tiempoExpiracion =  new Date( new Date().getTime()+duracion);
			
			// informacion para el token
			Claims claims = Jwts.claims().setSubject(jwtCustomer.getCustomer_email())
					.setIssuedAt(new Date()).setExpiration(tiempoExpiracion);
			claims.put("customer_id", jwtCustomer.getCustomer_id());
			
			//token cifrado
			token=Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, "PROYECTO_SECRETO").compact();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return token;
	}
}
