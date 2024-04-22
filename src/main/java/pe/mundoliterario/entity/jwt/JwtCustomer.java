package pe.mundoliterario.entity.jwt;

public class JwtCustomer {
	
	private Integer customer_id;
	private String customer_email;
	
	public JwtCustomer() {
	}
	
	public JwtCustomer(Integer customer_id, String customer_email) {
		this.customer_id = customer_id;
		this.customer_email = customer_email;
	}
	
	public Integer getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getCustomer_email() {
		return customer_email;
	}
	
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	

}
