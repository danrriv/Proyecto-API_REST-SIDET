package pe.mundoliterario.vo;

import pe.mundoliterario.entity.Genre;
import pe.mundoliterario.entity.Subgenre;

public class GenreSubgenre {

	private Genre genre;
	
	private Subgenre subgenre;
	
	public GenreSubgenre() {
	}

	public GenreSubgenre(Genre genre, Subgenre subgenre) {
		this.genre = genre;
		this.subgenre = subgenre;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Subgenre getSubgenre() {
		return subgenre;
	}

	public void setSubgenre(Subgenre subgenre) {
		this.subgenre = subgenre;
	}
	
	
	
}
