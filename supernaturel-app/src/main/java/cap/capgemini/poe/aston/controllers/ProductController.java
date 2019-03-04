package cap.capgemini.poe.aston.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.capgemini.poe.aston.entities.Product;
import cap.capgemini.poe.aston.services.ICatergoryService;
import cap.capgemini.poe.aston.services.IProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ICatergoryService catergoryService;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return this.productService.getAllProducts();
	}

	@GetMapping("/products/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public Product getProductById(@PathVariable Long id) {
		return this.productService.getProduct(id);
	}

	@PostMapping("/products/{categoryId}")
	@Secured("ROLE_ADMIN")
	public Product createProduct(@PathVariable Long categoryId, @RequestBody Product product) {
		product.setCategory(this.catergoryService.getCategory(categoryId));
		return this.productService.createProduct(product);
	}

	@PutMapping("/products/{id}")
	@Secured("ROLE_ADMIN")
	public Product update(@PathVariable Long id, @RequestBody Product product){
		return this.productService.editProduct(id, product);
	}
}
