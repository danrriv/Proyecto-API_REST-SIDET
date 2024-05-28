package pe.mundoliterario.mapper;

import pe.mundoliterario.entity.SaleDetails;

public class SaleDetailMapper {

	private Integer saleDetails_id;
	
	private Integer saleDetails_quantity;
	
	private Double saleDetails_unit_price;
	
	private Double saleDetails_subtotal;
	
	private String book;
	
	
	public SaleDetailMapper(SaleDetails saleDetails) {
		this(saleDetails.getSaleDetails_id(), saleDetails.getSaleDetails_quantity(),
				saleDetails.getSaleDetails_unit_price(), saleDetails.getSaleDetails_subtotal(),
				saleDetails.getBook().getBook_name());
	}
	
	public SaleDetailMapper() {
		// TODO Auto-generated constructor stub
	}
	
	public SaleDetailMapper(Integer saleDetails_id, Integer saleDetails_quantity, Double saleDetails_unit_price,
			Double saleDetails_subtotal, String book) {
		this.saleDetails_id = saleDetails_id;
		this.saleDetails_quantity = saleDetails_quantity;
		this.saleDetails_unit_price = saleDetails_unit_price;
		this.saleDetails_subtotal = saleDetails_subtotal;
		this.book = book;
	}

	public Integer getSaleDetails_id() {
		return saleDetails_id;
	}

	public void setSaleDetails_id(Integer saleDetails_id) {
		this.saleDetails_id = saleDetails_id;
	}

	public Integer getSaleDetails_quantity() {
		return saleDetails_quantity;
	}

	public void setSaleDetails_quantity(Integer saleDetails_quantity) {
		this.saleDetails_quantity = saleDetails_quantity;
	}

	public Double getSaleDetails_unit_price() {
		return saleDetails_unit_price;
	}

	public void setSaleDetails_unit_price(Double saleDetails_unit_price) {
		this.saleDetails_unit_price = saleDetails_unit_price;
	}

	public Double getSaleDetails_subtotal() {
		return saleDetails_subtotal;
	}

	public void setSaleDetails_subtotal(Double saleDetails_subtotal) {
		this.saleDetails_subtotal = saleDetails_subtotal;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}
	
	

}
