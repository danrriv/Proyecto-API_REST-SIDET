package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.SaleDetails;
import pe.mundoliterario.repository.SaleDetailsRepository;
import pe.mundoliterario.service.SaleDetailsService;

@Service
public class SaleDetailsServiceImpl implements SaleDetailsService {

	@Autowired
	private SaleDetailsRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public Collection<SaleDetails> listDetails(Integer sale_id) {
		return repository.obtainSaleDetails(sale_id);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<SaleDetails> listAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void save(SaleDetails saleDetails) {
		repository.save(saleDetails);
		
	}



}
