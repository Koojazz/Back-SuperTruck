package cap.capgemini.poe.aston.services;

import java.util.List;

import cap.capgemini.poe.aston.entities.Category;

public interface ICatergoryService {
	
	public Category createCategory(Category category);
	public Category getCategory(Long id);
	public Category editCategory(Long id, Category category);
	public void deleteCategory(Category category);
    public void deleteCategory(Long id);
    public List<Category> getAllCategories(int pageNumber, int pageSize);
    public List<Category> getAllCategories();
    public long countCategories();
    
}
