package pe.mundoliterario.security.jwt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import pe.mundoliterario.entity.jwt.JwtAuthenticationToken;
import pe.mundoliterario.entity.jwt.JwtCustomer;
import pe.mundoliterario.entity.jwt.JwtCustomerDetail;
import pe.mundoliterario.jwt.JwtValidatorCustomer;

//proceso de autenticacion
@Component
public class JwtAuthenticationProvider  extends AbstractUserDetailsAuthenticationProvider{
	@Autowired
	private JwtValidatorCustomer jwtValidatorCustomer;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		
		String token = ((JwtAuthenticationToken)authentication).getToken();
		JwtCustomer jwtCustomer = jwtValidatorCustomer.validateToken(token);
		
		if(jwtCustomer!=null) {
			Collection<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(null);
			JwtCustomerDetail jwtCustomerDetail = new JwtCustomerDetail(jwtCustomer.getCustomer_id(), jwtCustomer.getCustomer_email(), 
					token, null);
			return jwtCustomerDetail;
		}
		
		throw new RuntimeException("¡Error, JWT el token es incorrecto!");
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
