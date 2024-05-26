package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.SaleDetails;

public interface SaleDetailsService {
	
	public abstract Collection<SaleDetails> listDetails(Integer sale_id);
	
	public abstract Collection<SaleDetails> listAll();
	
	public abstract void save(SaleDetails saleDetails);

}