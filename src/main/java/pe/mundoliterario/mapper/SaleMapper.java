package pe.mundoliterario.mapper;

import java.time.LocalDate;

import pe.mundoliterario.entity.Sale;

public class SaleMapper {

	private Integer sale_id;
	
	private Double sale_total;
	
	private Integer sale_total_products;
	
	private String sale_status;
	
	private LocalDate sale_date;
		
	private String customer;
	
	
	public SaleMapper(Sale sale) {
		this(sale.getSale_id(), sale.getSale_total(), sale.getSale_total_products(),
				sale.getSale_status(), sale.getSale_date(), sale.getCustomer().getCustomer_names());
	}
	
	public SaleMapper() {
		// TODO Auto-generated constructor stub
	}

	public SaleMapper(Integer sale_id, Double sale_total, Integer sale_total_products, String sale_status,
			LocalDate sale_date, String customer) {
		this.sale_id = sale_id;
		this.sale_total = sale_total;
		this.sale_total_products = sale_total_products;
		this.sale_status = sale_status;
		this.sale_date = sale_date;
		this.customer = customer;
	}


	public Integer getSale_id() {
		return sale_id;
	}


	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}


	public Double getSale_total() {
		return sale_total;
	}


	public void setSale_total(Double sale_total) {
		this.sale_total = sale_total;
	}


	public Integer getSale_total_products() {
		return sale_total_products;
	}


	public void setSale_total_products(Integer sale_total_products) {
		this.sale_total_products = sale_total_products;
	}


	public String getSale_status() {
		return sale_status;
	}


	public void setSale_status(String sale_status) {
		this.sale_status = sale_status;
	}


	public LocalDate getSale_date() {
		return sale_date;
	}


	public void setSale_date(LocalDate sale_date) {
		this.sale_date = sale_date;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
}
