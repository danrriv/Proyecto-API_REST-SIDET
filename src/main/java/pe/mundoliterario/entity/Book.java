package pe.mundoliterario.entity;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="book")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer book_id;
	
	@Column(nullable=false,length = 100)
	private String book_name;
	
	@Column
	private Double book_weight; //peso
	
	@Column(nullable=false,length = 100)
	private String book_editorial;
	
	@Column
	private Double book_width; //alto
	
	@Column
	private Double book_heigth; //ancho
	
	@Column
	private Integer book_stock;
	
	@Column
	private Double book_price;
	
	@Column
	private Integer book_npages;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private LocalDate book_year; //año de publicacion
	
	@Column(length = 2000)
	private String book_synopsis;
	
	@Column
	private Boolean book_status;
	
	/*
	@Column
	@Lob
	private byte[] book_image;
	*/
	@Column
	private String book_img;
	
	@Column
	private Boolean book_notification_status;
	
	
    @ManyToOne
	@JoinColumn(name="subgenre_id",nullable=false)
	private Subgenre subgenre;
    
    @ManyToOne
	@JoinColumn(name="author_id",nullable=false)
	private Author author;
	
	
	public Book() {
	}
	
	

	/*
	public Book(Integer book_id, String book_name, Double book_weight, String book_editorial, Double book_width,
			Double book_heigth, Integer book_stock, Double book_price, Integer book_npages, LocalDate book_year,
			String book_synopsis, Boolean book_status, byte[] book_img, Boolean book_notification_status,
			Subgenre subgenre, Author author) {
		super();
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
		this.book_image = book_image;
		this.book_notification_status = book_notification_status;
		this.subgenre = subgenre;
		this.author = author;
	}
	*/
	/*
	//Método para convertir a BLOB
	   private byte[] convertMultipartFileToBytes(MultipartFile file) {
	        try {
	            return file.getBytes();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null; // Manejo de errores apropiado
	        }
	    }
	*/
	public Book(Integer book_id, String book_name, Double book_weight, String book_editorial, Double book_width,
			Double book_heigth, Integer book_stock, Double book_price, Integer book_npages, LocalDate book_year,
			String book_synopsis, Boolean book_status, String book_img, Boolean book_notification_status,
			Subgenre subgenre, Author author) {
		super();
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
		this.subgenre = subgenre;
		this.author = author;
	}



	public String getBook_img() {
		return book_img;
	}



	public void setBook_img(String book_img) {
		this.book_img = book_img;
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


	/*
	public byte[] getBook_image() {
		return book_image;
	}

	public void setBook_image(byte[] book_image) {
		this.book_image = book_image;
	}
	*/

	public Boolean getBook_notification_status() {
		return book_notification_status;
	}


	public void setBook_notification_status(Boolean book_notification_status) {
		this.book_notification_status = book_notification_status;
	}


	public Subgenre getSubgenre() {
		return subgenre;
	}


	public void setSubgenre(Subgenre subgenre) {
		this.subgenre = subgenre;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}
	
}
