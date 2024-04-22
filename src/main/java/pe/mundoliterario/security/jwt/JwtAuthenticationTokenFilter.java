package pe.mundoliterario.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import pe.mundoliterario.entity.jwt.JwtAuthenticationToken;


public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

	public JwtAuthenticationTokenFilter() {
		super("/auth/**"); 
	}
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	        throws AuthenticationException, IOException, ServletException {
	    String value = request.getHeader("Authorization");
	    if (value != null && value.startsWith("Bearer ")) {
	        String token = value.substring(7);
	        System.out.println("Token recibido: " + token);
	        return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
	    }
	    throw new RuntimeException("Error en el proceso de filtrar token");
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

}
