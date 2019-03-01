package cap.capgemini.poe.aston.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cap.capgemini.poe.aston.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

	Optional<User> findByLastnameOrEmail(String lastname, String email);

	List<User> findByIdIn(List<Long> userIds);

	//    Optional<User> findByUsername(String username);

	//    Boolean existsByUsername(String username);

	Boolean existsByLastname(String lastname);

	Boolean existsByFirstname(String firstname);

	Boolean existsByEmail(String email);

}
