package pe.mundoliterario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.mundoliterario.entity.Book;

//import pe.mundoliterario.entity.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<Book, Integer> {

}
