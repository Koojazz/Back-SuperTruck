package cap.capgemini.poe.aston.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cap.capgemini.poe.aston.services.IMailService;
import cap.capgemini.poe.aston.services.IUserService;

@CrossOrigin("*")
@RestController
public class EmailController {
	
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/sendemail/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	   public void sendEmail(@PathVariable Long id) {
	      mailService.sendEmail(userService.getUser(id));;
	   }
}
