package pe.mundoliterario.mapper;


import java.time.LocalDate;
import pe.mundoliterario.entity.Book;

public class BookMapper {

	private Integer book_id;
	
	private String book_name;
	
	private Double book_weight; //peso

	private String book_editorial;

	private Double book_width; //alto
	
	private Double book_heigth; //ancho
	
	private Integer book_stock;
	
	private Double book_price;
	
	private Integer book_npages;
	
	private LocalDate book_year; //año de publicacion
	
	private String book_synopsis;
	
	private Boolean book_status;
	
	private String book_img;
	
	private Boolean book_notification_status;
	
	private String author;
	
	private String subgenre;
	
	public BookMapper() {
	}
		 
	public BookMapper(Book book) {
		this(book.getBook_id(),
				book.getBook_name(),
				book.getBook_weight(), 
				book.getBook_editorial(),
				book.getBook_width(),
				book.getBook_heigth(),
				book.getBook_stock(),
				book.getBook_price(),
				book.getBook_npages(),
				book.getBook_year(),
				book.getBook_synopsis(), 
				book.getBook_status(),
				book.getBook_img(),
				book.getBook_notification_status(),
				book.getAuthor().getAuthor_name(),
				book.getSubgenre().getSubgenre_name());
	}
	
	public BookMapper(Integer book_id, String book_name, Double book_weight, String book_editorial, Double book_width,
			Double book_heigth, Integer book_stock, Double book_price, Integer book_npages, LocalDate book_year,
			String book_synopsis, Boolean book_status, String book_img, Boolean book_notification_status, String author,
			String subgenre) {
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_weight = book_weight;
		this.book_editorial = book_editorial;
		this.book_width = book_width;
		this.book_heigth = book_heigth;
		this.book_stock = book_stock;
		this.book_price = book_price;
		this.book_npages = book_npages;
		this.book_year = book_year;
		this.book_synopsis = book_synopsis;
		this.book_status = book_status;
		this.book_img = book_img;
		this.book_notification_status = book_notification_status;
		this.author = author;
		this.subgenre = subgenre;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public Double getBook_weight() {
		return book_weight;
	}

	public void setBook_weight(Double book_weight) {
		this.book_weight = book_weight;
	}

	public String getBook_editorial() {
		return book_editorial;
	}

	public void setBook_editorial(String book_editorial) {
		this.book_editorial = book_editorial;
	}

	public Double getBook_width() {
		return book_width;
	}

	public void setBook_width(Double book_width) {
		this.book_width = book_width;
	}

	public Double getBook_heigth() {
		return book_heigth;
	}

	public void setBook_heigth(Double book_heigth) {
		this.book_heigth = book_heigth;
	}

	public Integer getBook_stock() {
		return book_stock;
	}

	public void setBook_stock(Integer book_stock) {
		this.book_stock = book_stock;
	}

	public Double getBook_price() {
		return book_price;
	}

	public void setBook_price(Double book_price) {
		this.book_price = book_price;
	}

	public Integer getBook_npages() {
		return book_npages;
	}

	public void setBook_npages(Integer book_npages) {
		this.book_npages = book_npages;
	}

	public LocalDate getBook_year() {
		return book_year;
	}

	public void setBook_year(LocalDate book_year) {
		this.book_year = book_year;
	}

	public String getBook_synopsis() {
		return book_synopsis;
	}

	public void setBook_synopsis(String book_synopsis) {
		this.book_synopsis = book_synopsis;
	}

	public Boolean getBook_status() {
		return book_status;
	}

	public void setBook_status(Boolean book_status) {
		this.book_status = book_status;
	}

	public Boolean getBook_notification_status() {
		return book_notification_status;
	}

	public void setBook_notification_status(Boolean book_notification_status) {
		this.book_notification_status = book_notification_status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubgenre() {
		return subgenre;
	}

	public void setSubgenre(String subgenre) {
		this.subgenre = subgenre;
	}

	public String getBook_img() {
		return book_img;
	}

	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}

	
	
	
}
