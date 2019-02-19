package cap.capgemini.poe.aston.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cap.capgemini.poe.aston.entities.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {

}
