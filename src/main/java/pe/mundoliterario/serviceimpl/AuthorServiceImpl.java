package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Author;
import pe.mundoliterario.repository.AuthorRepository;
import pe.mundoliterario.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	@Transactional
	public void insert(Author author) {
		authorRepository.save(author);
		
	}

	@Override
	@Transactional
	public void update(Author author) {
		authorRepository.save(author);
		
	}

	@Override
	@Transactional
	public void delete(Integer author_id) {
		authorRepository.deleteById(author_id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Author findById(Integer author_id) {
		return authorRepository.findById(author_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Author> findAll() {
		return authorRepository.findAll();
	}

}
