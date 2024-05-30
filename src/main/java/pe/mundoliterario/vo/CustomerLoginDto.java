package pe.mundoliterario.vo;

public class CustomerLoginDto {
	
	private Integer customer_id;
	
	private String customer_names;

	
	public CustomerLoginDto() {
	}	

	public CustomerLoginDto(Integer customer_id, String customer_names) {
		this.customer_id = customer_id;
		this.customer_names = customer_names;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}


	public String getCustomer_names() {
		return customer_names;
	}


	public void setCustomer_names(String customer_names) {
		this.customer_names = customer_names;
	}
	
	
	
	
}
