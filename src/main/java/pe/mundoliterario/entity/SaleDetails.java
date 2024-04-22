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
	private Integer quantity;
	
	@Column
	private Double unit_price;
	
	@Column
	private Double subtotal;
	

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "")
	private Sale sale;
	
	
}
