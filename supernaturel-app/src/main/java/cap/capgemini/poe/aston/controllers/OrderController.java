package cap.capgemini.poe.aston.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.capgemini.poe.aston.entities.Order;
import cap.capgemini.poe.aston.entities.RequestWrapper;
import cap.capgemini.poe.aston.services.IOrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return this.orderService.getAllOrders();
	}
	
	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return this.orderService.getOrder(id);
	}
	
	
	@PostMapping("/orders")
	public Order createOrder(@RequestBody RequestWrapper requestWrapper) {
		return this.orderService.createOrder(requestWrapper.getOrder(), requestWrapper.getIds());
	}
	
	@PutMapping("/orders/{id}")
	public Order update(@PathVariable Long id, @RequestBody Order order){
		return this.orderService.editOrder(id, order);
	}
}
