package cap.capgemini.poe.aston.services;

import cap.capgemini.poe.aston.entities.User;

public interface IMailService {
	
	public void sendEmail(User user);
}
