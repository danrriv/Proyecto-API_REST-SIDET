package pe.mundoliterario.util;

import java.util.ArrayList;
import java.util.Collection;

import pe.mundoliterario.entity.Book;
import pe.mundoliterario.mapper.BookMapper;

public class MapperMundoLiterario {
	
	public static Collection<BookMapper> toBook(Collection<Book> books){
		Collection<BookMapper> collection = new ArrayList<>();
		
		for(Book book:books) {
			BookMapper mapper = new BookMapper(book);
			collection.add(mapper);
		}
		return collection;
	}
	
	public static BookMapper toBooks(Book book) {
		BookMapper mapper = new BookMapper(book);
		return mapper;
	} 

}
