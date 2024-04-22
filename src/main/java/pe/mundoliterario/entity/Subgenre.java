package pe.mundoliterario.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="subgenre")
public class Subgenre implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer subgenre_id;
	
	@Column(nullable=false,length = 30)
	private String subgenre_name;
	
	@ManyToMany(mappedBy = "itemsSubgenre")
	private Set<Genre> itemsGenre = new HashSet<>();
	
	public Subgenre() {
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
	
	
}

