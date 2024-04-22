package pe.mundoliterario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="category")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer category_id;
	
	@Column(nullable=false, length=30)
	private String category_name;
	
	@OneToMany(mappedBy="category")
	@JsonBackReference
	private Collection<Genre>itemsGenus= new ArrayList<>();
	
	public Category() {
	}

	public Category(Integer category_id, String category_name) {
		this.category_id = category_id;
		this.category_name = category_name;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Collection<Genre> getItemsGenus() {
		return itemsGenus;
	}

	public void setItemsGenus(Collection<Genre> itemsGenus) {
		this.itemsGenus = itemsGenus;
	}
	
	
}
