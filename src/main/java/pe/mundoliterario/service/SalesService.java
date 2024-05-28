package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Sale;

public interface SalesService {

	public abstract Sale save(Sale sale);
	
	public abstract Sale edit(Sale sale);
	
	public abstract Sale findId(Integer sale_id);
	
	public abstract Collection<Sale> listAll();
	
	public abstract Collection<Sale> listForCustomer(Integer customer_id);
	
	public abstract Collection<Sale> listPending();
	
}
