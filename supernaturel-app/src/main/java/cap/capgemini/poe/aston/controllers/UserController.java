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

import cap.capgemini.poe.aston.entities.User;
import cap.capgemini.poe.aston.services.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public User getProductById(@PathVariable Long id) {
		return this.userService.getUser(id);
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return this.userService.createUser(user);
	}

	@PutMapping("/users/{id}")
	public User update(@PathVariable Long id, @RequestBody User user){
		return this.userService.editUser(id, user);
	}
}
