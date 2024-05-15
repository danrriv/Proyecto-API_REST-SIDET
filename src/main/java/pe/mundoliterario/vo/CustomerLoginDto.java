package pe.mundoliterario.vo;

public class CustomerLoginDto {
	
	private Integer customer_id;
	
	private String customer_name;

	
	public CustomerLoginDto() {
	}

	public CustomerLoginDto(Integer customer_id, String customer_name) {
		this.customer_id = customer_id;
		this.customer_name = customer_name;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}


	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	
	
}
