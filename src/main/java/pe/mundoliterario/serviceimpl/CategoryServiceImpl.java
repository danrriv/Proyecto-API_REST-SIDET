package pe.mundoliterario.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Category;
import pe.mundoliterario.repository.CategoryRepository;
import pe.mundoliterario.service.CategoryService;
import pe.mundoliterario.vo.MenuData;

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

	@Override
	@Transactional(readOnly=true)
	public Collection<MenuData> listCategoryGenreSubgenre() {
		List<Object[]> results = categoryRepository.listCategoryGenreSubgenreMenu();
		List<MenuData> menuList = new ArrayList<>();
		
		for(Object[] result: results) {
			MenuData md = new MenuData();
			md.setCategory_id((Integer) result[0]);
			md.setCategory_name((String) result[1]);
			md.setGenre_id((Integer) result[2]);
			md.setGenre_name((String) result[3]);
			md.setSubgenre_id((Integer) result[4]);
			md.setSubgenre_name((String) result[5]);
			menuList.add(md);
		}
		return menuList;
	}

}
