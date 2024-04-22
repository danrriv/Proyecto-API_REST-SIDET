package pe.mundoliterario.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.mundoliterario.security.jwt.JwtAuthenticationEntryPoint;
import pe.mundoliterario.security.jwt.JwtAuthenticationProvider;
import pe.mundoliterario.security.jwt.JwtAuthenticationSuccessHandler;
import pe.mundoliterario.security.jwt.JwtAuthenticationTokenFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired
	JwtAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/auth/**").authenticated()
	            .anyRequest().permitAll()
	            .and()
	        .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .csrf().disable()
	        .headers().cacheControl();
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter ()
	{
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		
		filter.setAuthenticationManager(authenticationManager());
		
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		
		return filter;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		
		return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {

	   return new BCryptPasswordEncoder();

	}
	
}
