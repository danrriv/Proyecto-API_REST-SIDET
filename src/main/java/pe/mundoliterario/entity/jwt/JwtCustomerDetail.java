package pe.mundoliterario.entity.jwt;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtCustomerDetail implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Integer customer_id;
	private String customer_email;
	private String customer_token;
	private Collection <? extends GrantedAuthority> authorities;
	
	public JwtCustomerDetail() {
	}
	
	public JwtCustomerDetail(Integer customer_id, String customer_email, String customer_token,
			Collection<? extends GrantedAuthority> authorities) {
		this.customer_id = customer_id;
		this.customer_email = customer_email;
		this.customer_token = customer_token;
		this.authorities = authorities;
	}
	
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.customer_email;
	}

	@Override
	public boolean isAccountNonExpired() { //se usa para que la cuenta no expire
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //se usa para que la cuenta no se bloquee
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {//se usa para que las credenciales no expiren
		return true;
	}

	@Override
	public boolean isEnabled() { //la cuenta está habilitada
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //Devuelve una lista vacía, ya que el cliente no tiene roles
	    return Collections.emptyList();
	}
	

}
