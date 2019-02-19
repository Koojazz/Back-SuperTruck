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

import cap.capgemini.poe.aston.entities.Contact;
import cap.capgemini.poe.aston.services.IContactService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ContactController {

	@Autowired
	private IContactService contactService;

	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		return this.contactService.getAllContacts();
	}

	@GetMapping("/contacts/{id}")
	public Contact getProductById(@PathVariable Long id) {
		return this.contactService.getContact(id);
	}


	@PostMapping("/contacts")
	public Contact createUser(@RequestBody Contact contact) {
		return this.contactService.createContact(contact);
	}

	@PutMapping("/contacts/{id}")
	public Contact update(@PathVariable Long id, @RequestBody Contact contact){
		return this.contactService.editContact(id, contact);
	}

}
