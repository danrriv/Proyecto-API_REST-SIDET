package pe.mundoliterario.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name="book")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer book_id;
	
	@Column(nullable=false,length = 100)
	private String name;
	
	@Column
	private Double weight; //peso
	
	@Column(nullable=false,length = 100)
	private String editorial;
	
	@Column
	private Double width; //alto
	
	@Column
	private Double heigth; //ancho
	
	@Column
	private Integer stock;
	
	@Column
	private Double price;
	
	@Column
	private Integer npages;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private LocalDate year; //año de publicacion
	
	@Column
	private String synopsis;
	
	@Column
	private String status;
	
	@Column
	private String img;
	
    @ManyToOne
	@JoinColumn(name="subgenre_id",nullable=false)
	private Subgenre subgenre;
    
    @ManyToOne
	@JoinColumn(name="autor_id",nullable=false)
	private Autor autor;
	
	
	public Book() {
	}

	public Book(Integer book_id, String name, Double weight, String editorial, Double width, Double heigth,
			Integer stock, Double price, Integer npages, LocalDate year, String synopsis, String status,
			String img) {
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


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}

	public Subgenre getSubgenre() {
		return subgenre;
	}

	public void setSubgenre(Subgenre subgenre) {
		this.subgenre = subgenre;
	}
	
}
