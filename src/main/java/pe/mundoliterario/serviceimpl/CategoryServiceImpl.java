package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Category;
import pe.mundoliterario.repository.CategoryRepository;
import pe.mundoliterario.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	@Transactional
	public void create(Category category) {
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void update(Category category) {
		categoryRepository.save(category);
	}

	@Override
	@Transactional(readOnly=true)
	public Category find_id(Integer category_id) {
		return categoryRepository.findById(category_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Category> list() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Category> findCategory(String name) {
		return categoryRepository.findCategory(name);
	}

}
