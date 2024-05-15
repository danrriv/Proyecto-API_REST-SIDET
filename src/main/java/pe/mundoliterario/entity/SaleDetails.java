package pe.mundoliterario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="saleDetails")
public class SaleDetails implements Serializable {
	
	private static final long serialVersionUID=1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer saleDetails_id;
	
	@Column
	private Integer saleDetails_quantity;
	
	@Column
	private Double saleDetails_unit_price;
	
	@Column
	private Double saleDetails_subtotal;
	

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "sale_id", nullable =false)
	private Sale sale;
	
	@ManyToOne
	private Book book;
	
	public SaleDetails() {
	}

	public SaleDetails(Integer saleDetails_id, Integer saleDetails_quantity, Double saleDetails_unit_price,
			Double saleDetails_subtotal, Book book) {
		super();
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

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
