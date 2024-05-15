package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Category;
import pe.mundoliterario.vo.MenuData;

public interface CategoryService {
	public abstract void create(Category category);
	public abstract void update(Category category);
	public abstract Category find_id(Integer category_id);
	public abstract Collection<Category> list();
	public abstract Collection<Category> findCategory(String name);
	public abstract Collection<MenuData> listCategoryGenreSubgenre();
	
	
}
