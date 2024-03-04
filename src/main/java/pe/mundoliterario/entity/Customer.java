package pe.mundoliterario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer client_id;
    
    @Column(nullable=false,length = 8)
    private String dni;
    
    @Column(nullable=false,length = 50)
    private String surnames;
    
    @Column(nullable=false,length = 50)
    private String names;
    
    @Column(length = 9)
    private String phone_number;
    
    @Column(length = 200)
    private String address;
    
    @Column(nullable=false,length = 100)
    private String email;
    
    @Column(nullable=false,length = 255)
    private String password;
    
    @Column(length = 10)
    private String status;
    
    public Customer() {
	}

	public Customer(Integer client_id, String dni, String surnames, String names, String phone_number, String address,
			String email, String password, String status) {
		this.client_id = client_id;
		this.dni = dni;
		this.surnames = surnames;
		this.names = names;
		this.phone_number = phone_number;
		this.address = address;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	
}
