package cap.capgemini.poe.aston.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cap.capgemini.poe.aston.entities.Role;
import cap.capgemini.poe.aston.entities.RoleName;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(RoleName roleName);
	
}
