package pe.mundoliterario.mapper;


import java.time.LocalDate;
import pe.mundoliterario.entity.Book;

public class BookMapper {

	private Integer book_id;
	
	private String name;
	
	private Double weight; //peso

	private String editorial;

	private Double width; //alto
	
	private Double heigth; //ancho
	
	private Integer stock;
	
	private Double price;
	
	private Integer npages;
	
	private LocalDate year; //año de publicacion
	
	private String synopsis;
	
	private String status;
	
	private String autor;
	
	private String img;
	
	public BookMapper() {
	}
	/*
	public BookMapper(Book book) {
		this(book.getBook_id(), book.getName(), book.getWeight(), book.getEditorial(), book.getWidth(),
				book.getHeigth(), book.getStock(), book.getPrice(), book.getNpages(), book.getYear(),
				book.getSynopsis(), book.getStatus(), book.getAutor(), book.getImg());
	}
	*/

	public BookMapper(Integer book_id, String name, Double weight, String editorial, Double width, Double heigth,
			Integer stock, Double price, Integer npages, java.time.LocalDate year, String synopsis, String status,
			String autor, String img) {
		this.book_id = book_id;
		this.name = name;
		this.weight = weight;
		this.editorial = editorial;
		this.width = width;
		this.heigth = heigth;
		this.stock = stock;
		this.price = price;
		this.npages = npages;
		this.year = year;
		this.synopsis = synopsis;
		this.status = status;
		this.autor = autor;
		this.img = img;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeigth() {
		return heigth;
	}

	public void setHeigth(Double heigth) {
		this.heigth = heigth;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNpages() {
		return npages;
	}

	public void setNpages(Integer npages) {
		this.npages = npages;
	}

	public LocalDate getYear() {
		return year;
	}

	public void setYear(LocalDate year) {
		this.year = year;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
