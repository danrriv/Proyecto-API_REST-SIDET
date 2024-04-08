package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Customer;

public interface CustomerService {
	public abstract void create(Customer customer);
	public abstract void update(Customer customer);
	public abstract void delete(Integer customer_id);
	public abstract Customer find_id(Integer customer_id);
	public abstract Collection<Customer> list();
	public abstract Customer find_email(String email);
	public abstract Customer findByConfirmationToken(String token);
}
