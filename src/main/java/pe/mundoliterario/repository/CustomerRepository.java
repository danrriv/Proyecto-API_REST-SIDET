package pe.mundoliterario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.mundoliterario.entity.Customer;
import pe.mundoliterario.vo.CustomerLoginDto;

public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
	
	@Query(value = "SELECT * FROM customer where customer_email=?1" ,nativeQuery = true)
	public abstract Customer find_email(String email);
	
	@Query(value = "SELECT * FROM customer where customer_token = :token" ,nativeQuery = true)
    Customer findByConfirmationToken(String token);
	
	@Query(value = "SELECT * FROM customer where customer_email =:email" ,nativeQuery = true)
    Customer findyByEmailDto(String email);
}
