package cap.capgemini.poe.aston.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cap.capgemini.poe.aston.entities.Product;
import cap.capgemini.poe.aston.repositories.IProductRepository;
import cap.capgemini.poe.aston.services.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product getProduct(Long id) {
		return this.productRepository.findById(id).orElse(null);
	}

	@Override
	public Product editProduct(Long id, Product product) {
		final Product p = this.productRepository.findById(id).orElse(null);
		p.setName(product.getName());
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setCategory(product.getCategory());
		return this.productRepository.save(p);
	}

	@Override
	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
	}

	@Override
	public List<Product> getAllProducts(int pageNumber, int pageSize) {
		return this.productRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	@Override
	public long countProducts() {
		return this.productRepository.count();
	}

	@Override
	public void deleteProduct(Product product) {
		this.productRepository.delete(product);
	}

}
