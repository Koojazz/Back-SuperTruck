package cap.capgemini.poe.aston.services;

import java.util.List;

import cap.capgemini.poe.aston.entities.Product;

public interface IProductService {
	
	public Product createProduct(Product product);
	public Product getProduct(Long id);
	public Product editProduct(Long id, Product product);
	public void deleteProduct(Product product);
    public void deleteProduct(Long id);
    public List<Product> getAllProducts(int pageNumber, int pageSize);
    public List<Product> getAllProducts();
    public long countProducts();
    
}
