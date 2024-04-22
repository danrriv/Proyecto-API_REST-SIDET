package pe.mundoliterario.dto;

import java.util.List;

import pe.mundoliterario.entity.Sale;
import pe.mundoliterario.entity.SaleDetails;

public class SaleDetailDTO {

	private Sale sale;
	
	private List<SaleDetails> details;
	
	public SaleDetailDTO() {
		// TODO Auto-generated constructor stub
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<SaleDetails> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetails> details) {
		this.details = details;
	}
	
	
}
