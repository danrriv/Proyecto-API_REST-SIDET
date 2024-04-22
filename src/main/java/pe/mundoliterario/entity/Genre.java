package pe.mundoliterario.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="genre")
public class Genre implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer genre_id;
	
	@Column(nullable=false, length=20)
	private String genre_name;
	
	@ManyToOne
	@JoinColumn(name="category_id",nullable = false)
	private Category category;
	
	@ManyToMany
	@JoinTable(name="genre_subgenre",
		joinColumns=@JoinColumn(name="genre_id"),
		inverseJoinColumns=@JoinColumn(name="subgenre_id"))
	private Set<Subgenre> itemsSubgenre=new HashSet<>();
	
	public Genre() {
	}
	
	public Genre(Integer genre_id, String genre_name) {
		this.genre_id = genre_id;
		this.genre_name = genre_name;
	}


	public Integer getGenre_id() {
		return genre_id;
	}
	
	public void addSubgenre(Subgenre subgenre) {
		itemsSubgenre.add(subgenre);
	}


	public void setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Subgenre> getItemsSubgenre() {
		return itemsSubgenre;
	}

	public void setItemsSubgenre(Set<Subgenre> itemsSubgenre) {
		this.itemsSubgenre = itemsSubgenre;
	}

	
}
