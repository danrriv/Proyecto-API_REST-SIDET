package pe.mundoliterario.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="subgenre")
public class Subgenre {

private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer subgenre_id;
	
	@Column(nullable=false,length = 100)
	private String subgenre_name;
	
	/*
	@ManyToMany(mappedBy = "itemsSubgenero")
	private Set<Genero> itemsGenero = new HashSet<>();
	
	*/
	@OneToMany(mappedBy="subgenre")
	private Collection<Book> itemsBook=new ArrayList<>();
	
	public Subgenre() {
	// TODO Auto-generated constructor stub
	}
	
	
	public Subgenre(Integer subgenre_id, String subgenre_name) {
		this.subgenre_id = subgenre_id;
		this.subgenre_name = subgenre_name;
	}


	public Integer getSubgenre_id() {
		return subgenre_id;
	}

	public void setSubgenre_id(Integer subgenre_id) {
		this.subgenre_id = subgenre_id;
	}

	public String getSubgenre_name() {
		return subgenre_name;
	}

	public void setSubgenre_name(String subgenre_name) {
		this.subgenre_name = subgenre_name;
	}

	public Collection<Book> getItemsBook() {
		return itemsBook;
	}

	public void setItemsBook(Collection<Book> itemsBook) {
		this.itemsBook = itemsBook;
	}	
	
	
}
