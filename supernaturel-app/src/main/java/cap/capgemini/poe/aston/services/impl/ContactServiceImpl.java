package cap.capgemini.poe.aston.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cap.capgemini.poe.aston.entities.Contact;
import cap.capgemini.poe.aston.repositories.IContactRepository;
import cap.capgemini.poe.aston.services.IContactService;

@Service
@Transactional
public class ContactServiceImpl implements IContactService {

	@Autowired
	private IContactRepository contactRepository;

	@Override
	public Contact createContact(Contact contact) {
		return this.contactRepository.save(contact);
	}

	@Override
	public Contact getContact(Long id) {
		return this.contactRepository.findById(id).orElse(null);
	}

	@Override
	public Contact editContact(Long id, Contact contact) {
		final Contact c = this.contactRepository.findById(id).orElse(null);
		c.setEmail(contact.getEmail());
		c.setTel(contact.getTel());
		return this.contactRepository.save(c);
	}

	@Override
	public void deleteContact(Long id) {
		this.contactRepository.deleteById(id);
	}

	@Override
	public List<Contact> getAllContacts(int pageNumber, int pageSize) {
		return this.contactRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Contact> getAllContacts() {
		return this.contactRepository.findAll();
	}

	@Override
	public long countContact() {
		return this.contactRepository.count();
	}

	@Override
	public void deleteContact(Contact contact) {
		this.contactRepository.delete(contact);
	}

}
