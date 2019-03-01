package cap.capgemini.poe.aston.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cap.capgemini.poe.aston.entities.Order;
import cap.capgemini.poe.aston.repositories.IOrderRepository;
import cap.capgemini.poe.aston.services.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private IOrderRepository orderRepository;
	
	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order getOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public Order editOrder(Long id, Order order) {
		Order o = orderRepository.findById(id).orElse(null);
		o.setUser(order.getUser());
		o.setProducts(order.getProducts());
		return orderRepository.save(o);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public List<Order> getAllOrders(int pageNumber, int pageSize) {
		return orderRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public long countOrders() {
		return orderRepository.count();
	}

}
