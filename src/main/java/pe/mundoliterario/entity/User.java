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

@Entity
@Table(name="user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(nullable=false,length = 8)
	private String user_dni;
	
	@Column(nullable=false,length = 50)
    private String user_surnames;
    
    @Column(nullable=false,length = 50)
    private String user_names;
	
    @Column(length = 9)
	private String user_phone_number;
	
    @Column(nullable=false,length = 100)
	private String user_email;
	
	@Column(nullable=false,length = 255)
	private String user_password;
	
	@Column(length = 10)
	private String user_status;
	
	@Column(length = 255)
    private String user_token;
	
	@ManyToOne
	@JoinColumn(name="role_id",nullable = false)
	private Role role;
	
	public User() {
	}

	public User(Integer user_id, String user_dni, String user_surnames, String user_names, String user_phone_number,
			String user_email, String user_password, String user_status, String user_token) {
		this.user_id = user_id;
		this.user_dni = user_dni;
		this.user_surnames = user_surnames;
		this.user_names = user_names;
		this.user_phone_number = user_phone_number;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_status = user_status;
		this.user_token = user_token;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_dni() {
		return user_dni;
	}

	public void setUser_dni(String user_dni) {
		this.user_dni = user_dni;
	}

	public String getUser_surnames() {
		return user_surnames;
	}

	public void setUser_surnames(String user_surnames) {
		this.user_surnames = user_surnames;
	}

	public String getUser_names() {
		return user_names;
	}

	public void setUser_names(String user_names) {
		this.user_names = user_names;
	}

	public String getUser_phone_number() {
		return user_phone_number;
	}

	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getUser_token() {
		return user_token;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
