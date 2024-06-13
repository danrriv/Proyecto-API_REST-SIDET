package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.mundoliterario.entity.Book;
import pe.mundoliterario.repository.BookRepository;
import pe.mundoliterario.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	
	@Autowired
	private BookRepository r;
	
	
	@Override
	@Transactional
	public void insert(Book book) {
		r.save(book);
	}

	
	@Override
	@Transactional
	public void update(Book book) {
		r.save(book);
	}

	
	@Override
	@Transactional
	public void delete(Integer book_id) {
		r.deleteById(book_id);	
	}

	
	@Override
	@Transactional(readOnly=true)
	public Book findById(Integer book_id) {
		return r.findById(book_id).orElse(null);
	}

	
    @Transactional(readOnly=true)
    public Collection<Book> findAll() {
        return r.findAll();
    }


	@Override
	@Transactional(readOnly=true)
	public Book findByName(String book_name) {
		return r.findBookbyName(book_name);
	}


	@Override
	@Transactional(readOnly=true)
	public Collection<Book> findBySimilarName(String book_name) {
		return r.findBySimilarName(book_name);
	}


	@Override
	@Transactional(readOnly=true)
	public Collection<Book> listRandom9() {
		return r.listRandom9();
	}


	@Override
	@Transactional(readOnly=true)
	public Collection<Book> listByGenre(Integer id) {
		return r.listbyGenre(id);
	}


	@Override
	@Transactional(readOnly=true)
	public Collection<Book> listBySubgenre(Integer id) {
		return r.listbySubGenre(id);
	}


	@Override
	public Collection<Book> searchBySubgenre(String subgenre) {
		return r.searchBySubgenre(subgenre);
	}

}
