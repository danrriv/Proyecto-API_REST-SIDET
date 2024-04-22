package pe.mundoliterario.entity;

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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="sale")
public class Sale {
	
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer sale_id;
	
	@Column
	private Double total;
	
	@Column
	private Integer total_products;
	
	@Column
	private String status;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private LocalDate date_sale;
	

	public Sale() {
		
	}

	public Sale(Integer sale_id, Double total, Integer total_products, String status, LocalDate date_sale) {
		this.sale_id = sale_id;
		this.total = total;
		this.total_products = total_products;
		this.status = status;
		this.date_sale = date_sale;
	}
	
	
	@OneToMany(mappedBy = "sale")
	@JsonManagedReference
	private Collection<SaleDetails> itemSalesDetail = new ArrayList<>();
	
	@ManyToOne
	//@JsonBackReference
	@JoinColumn(name = "cliente_id", nullable =false)
	private Customer customer;
	

}
