package cap.capgemini.poe.aston.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cap.capgemini.poe.aston.entities.Order;
import cap.capgemini.poe.aston.repositories.IOrderRepository;
import cap.capgemini.poe.aston.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
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
