package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Customer;
import pe.mundoliterario.repository.CustomerRepository;
import pe.mundoliterario.service.CustomerService;
import pe.mundoliterario.vo.CustomerLoginDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public void create(Customer customer) {
		customerRepository.save(customer);
		
	}

	@Override
	@Transactional
	public void update(Customer customer) {
		customerRepository.save(customer);
		
	}

	@Override
	@Transactional
	public void delete(Integer customer_id) {
		customerRepository.deleteById(customer_id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Customer find_id(Integer customer_id) {
		return customerRepository.findById(customer_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Customer> list() {
		return customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Customer find_email(String email) {
		return customerRepository.find_email(email);
	}

	@Override
	public Customer findByConfirmationToken(String token) {
		return customerRepository.findByConfirmationToken(token);
	}

	@Override
	public CustomerLoginDto findByEmailDto(String email) {
		return customerRepository.findyByEmailDto(email);
	}

}
