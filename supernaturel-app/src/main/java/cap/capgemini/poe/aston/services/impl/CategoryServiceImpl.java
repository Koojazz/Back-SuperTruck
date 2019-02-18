package cap.capgemini.poe.aston.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cap.capgemini.poe.aston.entities.Category;
import cap.capgemini.poe.aston.repositories.ICategoryRepository;
import cap.capgemini.poe.aston.services.ICatergoryService;

@Service
public class CategoryServiceImpl implements ICatergoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategory(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category editCategory(Long id, Category category) {
		Category c = categoryRepository.findById(id).orElse(null);
		c.setName(category.getName());
		return categoryRepository.save(c);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> getAllCategories(int pageNumber, int pageSize) {
		return categoryRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public long countCategories() {
		return categoryRepository.count();
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

}
