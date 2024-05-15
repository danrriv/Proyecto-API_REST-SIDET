package pe.mundoliterario.vo;

public class MenuData {
	
	private Integer category_id;
	
	private String category_name;
	
	private Integer genre_id;
	
	private String genre_name;
	
	private Integer subgenre_id;
	
	private String subgenre_name;
	
	
	public MenuData() {
		// TODO Auto-generated constructor stub
	}

	public MenuData(Integer category_id, String category_name, Integer genre_id, String genre_name, Integer subgenre_id,
			String subgenre_name) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.genre_id = genre_id;
		this.genre_name = genre_name;
		this.subgenre_id = subgenre_id;
		this.subgenre_name = subgenre_name;
	}

	public Integer getCategory_id() {
		return category_id;
	}


	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}


	public String getCategory_name() {
		return category_name;
	}


	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}


	public Integer getGenre_id() {
		return genre_id;
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
