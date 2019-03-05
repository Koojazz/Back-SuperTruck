package cap.capgemini.poe.aston.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cap.capgemini.poe.aston.entities.Order;
import cap.capgemini.poe.aston.entities.OrderStatus;
import cap.capgemini.poe.aston.entities.Product;
import cap.capgemini.poe.aston.repositories.IOrderRepository;
import cap.capgemini.poe.aston.repositories.IProductRepository;
import cap.capgemini.poe.aston.repositories.IUserRepository;
import cap.capgemini.poe.aston.services.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Order createOrder(String user_id, List<Long> productsIds) {
		final List<Product> products = new ArrayList<>();
		for( final Long id: productsIds) {

			final Product product = this.productRepository.findById(id).orElse(null);
			products.add(product);

		}

		final Order order = new Order();
		order.setProducts(products);
		order.setStatus(OrderStatus.WAITING);
		order.setUser(this.userRepository.findById(Long.valueOf(user_id)).orElse(null));
		return this.orderRepository.save(order);
	}

	@Override
	public Order getOrder(Long id) {
		return this.orderRepository.findById(id).orElse(null);
	}

	@Override
	public Order editOrder(Long id, Order order) {
		final Order o = this.orderRepository.findById(id).orElse(null);
		o.setUser(order.getUser());
		o.setProducts(order.getProducts());
		return this.orderRepository.save(o);
	}

	@Override
	public void deleteOrder(Order order) {
		this.orderRepository.delete(order);
	}

	@Override
	public void deleteOrder(Long id) {
		this.orderRepository.deleteById(id);
	}

	@Override
	public List<Order> getAllOrders(int pageNumber, int pageSize) {
		return this.orderRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public long countOrders() {
		return this.orderRepository.count();
	}

}
