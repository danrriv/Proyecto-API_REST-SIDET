package pe.mundoliterario.util;

import java.util.ArrayList;
import java.util.Collection;

import pe.mundoliterario.entity.Book;
import pe.mundoliterario.entity.Sale;
import pe.mundoliterario.entity.SaleDetails;
import pe.mundoliterario.mapper.BookMapper;
import pe.mundoliterario.mapper.SaleDetailMapper;
import pe.mundoliterario.mapper.SaleMapper;

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
	
	public static Collection<SaleMapper> toSales(Collection<Sale>sales){
		Collection<SaleMapper> collection = new ArrayList<>();
		
		for(Sale sale: sales) {
			SaleMapper mapper = new SaleMapper(sale);
			collection.add(mapper);
		}
		return collection;
	}
	
	public static Collection<SaleDetailMapper> toSalesD(Collection<SaleDetails>salesd){
		Collection<SaleDetailMapper> collection = new ArrayList<>();
		
		for(SaleDetails saleDetails: salesd) {
			SaleDetailMapper mapper = new SaleDetailMapper(saleDetails);
			collection.add(mapper);
		}
		return collection;
	}
	

}
