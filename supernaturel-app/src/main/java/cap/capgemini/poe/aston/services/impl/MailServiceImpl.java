package cap.capgemini.poe.aston.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cap.capgemini.poe.aston.entities.User;
import cap.capgemini.poe.aston.services.IMailService;

@Service
public class MailServiceImpl implements IMailService {

	private final JavaMailSender javaMailSender;


	@Autowired
	public MailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendEmail(User user) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot. This
		 * JavaMailSender extends the MailSender Interface which contains send()
		 * function. SimpleMailMessage Object is required because send() function uses
		 * object of SimpleMailMessage as a Parameter
		 */

		final SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("supernaturelmaubeuge@gmail.com");
		mail.setSubject("Confirmation de votre Commande");
		mail.setText("Votre commande portant le numéro 1,est en cours de préparation et sera disponible pour 12h00");

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		this.javaMailSender.send(mail);

	}

}
