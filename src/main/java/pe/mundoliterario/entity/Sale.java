package pe.mundoliterario.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="sale")
public class Sale implements Serializable {
	
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer sale_id;
	
	@Column
	private Double sale_total;
	
	@Column
	private Integer sale_total_products;
	
	@Column
	private String sale_status;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private LocalDate sale_date;
		
	@OneToMany(mappedBy = "sale")
	@JsonManagedReference
	private Collection<SaleDetails> itemSalesDetail = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable =false)
	private Customer customer;
	

	public Sale() {
		
	}

	public Sale(Integer sale_id, Double sale_total, Integer sale_total_products, String sale_status, LocalDate sale_date) {
		this.sale_id = sale_id;
		this.sale_total = sale_total;
		this.sale_total_products = sale_total_products;
		this.sale_status = sale_status;
		this.sale_date = sale_date;
	}

	@PrePersist
	public void prePersist() {
		sale_date= LocalDate.now(); //asigna la hora actual para la fecha de venta
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

	public Collection<SaleDetails> getItemSalesDetail() {
		return itemSalesDetail;
	}

	public void setItemSalesDetail(Collection<SaleDetails> itemSalesDetail) {
		this.itemSalesDetail = itemSalesDetail;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
