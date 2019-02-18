package cap.capgemini.poe.aston.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.capgemini.poe.aston.entities.Product;
import cap.capgemini.poe.aston.services.IProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return this.productService.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable Long id) {
		return this.productService.getProduct(id);
	}
	
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return this.productService.createProduct(product);
	}
	
	@PutMapping("/products/{id}")
	public Product update(@PathVariable Long id, @RequestBody Product product){
		return this.productService.editProduct(id, product);
	}
}
