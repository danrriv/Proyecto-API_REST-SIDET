package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Sale;
import pe.mundoliterario.repository.SaleRepository;
import pe.mundoliterario.service.SalesService;
@Service
public class SaleServiceImpl implements SalesService {

	
	@Autowired
	private SaleRepository repository;
	
	
	@Override
	@Transactional
	public Sale save(Sale sale) {
		return repository.save(sale);
	}

	@Override
	@Transactional
	public Sale update(Sale sale) {
		return repository.save(sale);
	}

	@Override
	@Transactional(readOnly=true)
	public Sale findId(Integer sale_id) {
		return repository.findById(sale_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Sale> listAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Sale> listForCustomer(Integer customer_id) {
		return repository.listSalesByCustomer(customer_id);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Sale> listPending() {
		return repository.listPendingSales();
	}
	

}
