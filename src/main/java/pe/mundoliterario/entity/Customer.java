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
    private Integer customer_id;
    
    @Column(nullable=false,length = 8)
    private String customer_dni;
    
    @Column(nullable=false,length = 50)
    private String customer_surnames;
    
    @Column(nullable=false,length = 50)
    private String customer_names;
    
    @Column(length = 9)
    private String customer_phone_number;
    
    @Column(nullable=false,length = 100)
    private String customer_email;
    
    @Column(nullable=false,length = 255)
    private String customer_password;
    
    @Column(length = 10)
    private String customer_status;
    
    @Column(length = 255)
    private String customer_token;
    
    public Customer() {
	}

	
	public Customer(Integer customer_id, String customer_dni, String customer_surnames, String customer_names,
			String customer_phone_number, String customer_email, String customer_password,
			String customer_status, String customer_token) {
		this.customer_id = customer_id;
		this.customer_dni = customer_dni;
		this.customer_surnames = customer_surnames;
		this.customer_names = customer_names;
		this.customer_phone_number = customer_phone_number;
		this.customer_email = customer_email;
		this.customer_password = customer_password;
		this.customer_status = customer_status;
		this.customer_token = customer_token;
	}


	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_dni() {
		return customer_dni;
	}

	public void setCustomer_dni(String customer_dni) {
		this.customer_dni = customer_dni;
	}

	public String getCustomer_surnames() {
		return customer_surnames;
	}

	public void setCustomer_surnames(String customer_surnames) {
		this.customer_surnames = customer_surnames;
	}

	public String getCustomer_names() {
		return customer_names;
	}

	public void setCustomer_names(String customer_names) {
		this.customer_names = customer_names;
	}

	public String getCustomer_phone_number() {
		return customer_phone_number;
	}

	public void setCustomer_phone_number(String customer_phone_number) {
		this.customer_phone_number = customer_phone_number;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_password() {
		return customer_password;
	}

	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}

	public String getCustomer_status() {
		return customer_status;
	}

	public void setCustomer_status(String customer_status) {
		this.customer_status = customer_status;
	}


	public String getCustomer_token() {
		return customer_token;
	}


	public void setCustomer_token(String customer_token) {
		this.customer_token = customer_token;
	}

	
}
