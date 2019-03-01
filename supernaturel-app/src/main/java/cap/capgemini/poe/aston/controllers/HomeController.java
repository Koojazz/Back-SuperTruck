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

import cap.capgemini.poe.aston.entities.Home;
import cap.capgemini.poe.aston.services.IHomeService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HomeController {

	@Autowired
	private IHomeService homeService;

	@GetMapping("/homes")
	public List<Home> getAllHomes() {
		return this.homeService.getAllHomes();
	}

	@GetMapping("/homes/{id}")
	public Home getProductById(@PathVariable Long id) {
		return this.homeService.getHome(id);
	}


	@PostMapping("/homes")
	public Home createUser(@RequestBody Home home) {
		return this.homeService.createHome(home);
	}

	@PutMapping("/homes/{id}")
	public Home update(@PathVariable Long id, @RequestBody Home home){
		return this.homeService.editHome(id, home);
	}
}
