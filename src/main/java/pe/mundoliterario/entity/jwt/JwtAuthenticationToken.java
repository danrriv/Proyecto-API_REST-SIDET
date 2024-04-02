package pe.mundoliterario.entity.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

//clase para envolver nuestro token
public class JwtAuthenticationToken  extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = 1L;

	private String token;
	
	public JwtAuthenticationToken(String token) {
		super(null, null);
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
	
}
