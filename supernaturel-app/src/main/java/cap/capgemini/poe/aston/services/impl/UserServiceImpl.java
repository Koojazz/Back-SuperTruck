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
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User editUser(Long id, User user) {
		User u = userRepository.findById(id).orElse(null);
		u.setFirstname(user.getFirstname());
		u.setLastname(user.getLastname());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setPhone(user.getPhone());
		return userRepository.save(u);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers(int pageNumber, int pageSize) {
		return userRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public long countUsers() {
		return userRepository.count();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String userEmail, Role rolename) {
		User user = userRepository.findByEmail(userEmail).orElse(null);
		user.getRoles().add(rolename);	
	}
}
