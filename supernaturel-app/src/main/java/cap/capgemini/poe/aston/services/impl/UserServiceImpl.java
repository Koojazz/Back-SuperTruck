package cap.capgemini.poe.aston.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cap.capgemini.poe.aston.entities.Role;
import cap.capgemini.poe.aston.entities.User;
import cap.capgemini.poe.aston.repositories.IRoleRepository;
import cap.capgemini.poe.aston.repositories.IUserRepository;
import cap.capgemini.poe.aston.services.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User getUser(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}

	@Override
	public User editUser(Long id, User user) {
		final User u = this.userRepository.findById(id).orElse(null);
		u.setFirstname(user.getFirstname());
		u.setLastname(user.getLastname());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setPhone(user.getPhone());
		return this.userRepository.save(u);
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers(int pageNumber, int pageSize) {
		return this.userRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public long countUsers() {
		return this.userRepository.count();
	}

	@Override
	public void deleteUser(User user) {
		this.userRepository.delete(user);
	}

	@Override
	public Role createRole(Role role) {
		return this.roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String userEmail, Role rolename) {
		final User user = this.userRepository.findByEmail(userEmail).orElse(null);
		user.getRoles().add(rolename);
	}

	@Override
	public User getUserByEmail(String userEmail) {
		return this.userRepository.findByEmail(userEmail).orElse(null);
	}
}
