package cap.capgemini.poe.aston.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.capgemini.poe.aston.entities.Category;
import cap.capgemini.poe.aston.services.ICatergoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private ICatergoryService categoryService;

	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return this.categoryService.getAllCategories();
	}

	@GetMapping("/categories/{id}")
	public Category getProductById(@PathVariable Long id) {
		return this.categoryService.getCategory(id);
	}

	@DeleteMapping("/categories/{id}")
	public void delete(@PathVariable Long id) {
		this.categoryService.deleteCategory(id);
	}


	@PostMapping("/categories")
	public Category createProduct(@RequestBody Category category) {
		return this.categoryService.createCategory(category);
	}

	@PutMapping("/categories/{id}")
	public Category update(@PathVariable Long id, @RequestBody Category category){
		return this.categoryService.editCategory(id, category);
	}

}
