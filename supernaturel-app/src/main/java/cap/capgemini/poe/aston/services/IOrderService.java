package cap.capgemini.poe.aston.services;

import java.util.List;

import cap.capgemini.poe.aston.entities.Order;

public interface IOrderService {
	
	public Order createOrder(Order order);
	public Order getOrder(Long id);
	public Order editOrder(Long id, Order order);
	public void deleteOrder(Order order);
    public void deleteOrder(Long id);
    public List<Order> getAllOrders(int pageNumber, int pageSize);
    public List<Order> getAllOrders();
    public long countOrders();
}
