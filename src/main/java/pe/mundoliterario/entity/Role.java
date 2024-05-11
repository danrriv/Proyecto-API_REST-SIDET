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
@Table(name="role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer role_id;
	
	@Column(nullable=false,length = 20)
	private String role_name;
	
	@Column(length = 100)
	private String description;

	@OneToMany(mappedBy="role")
	@JsonBackReference
	private Collection<User>itemsUser= new ArrayList<>();
	
	public Role() {
	}

	public Role(Integer role_id, String role_name, String description) {
		this.role_id = role_id;
		this.role_name = role_name;
		this.description = description;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<User> getItemsUser() {
		return itemsUser;
	}

	public void setItemsUser(Collection<User> itemsUser) {
		this.itemsUser = itemsUser;
	}
	
	
}
