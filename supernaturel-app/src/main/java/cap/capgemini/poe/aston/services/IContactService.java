package cap.capgemini.poe.aston.services;

import java.util.List;

import cap.capgemini.poe.aston.entities.Contact;

public interface IContactService {

	public Contact createContact(Contact contact);
	public Contact getContact(Long id);
	public Contact editContact(Long id, Contact contact);
	public void deleteContact(Contact contact);
	public void deleteContact(Long id);
	public List<Contact> getAllContacts(int pageNumber, int pageSize);
	public List<Contact> getAllContacts();
	public long countContact();
}
