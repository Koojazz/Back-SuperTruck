package cap.capgemini.poe.aston.services;

import java.util.List;

import cap.capgemini.poe.aston.entities.Role;
import cap.capgemini.poe.aston.entities.User;

public interface IUserService {
	
	User createUser(User user);
	User getUser(Long id);
	User editUser(Long id, User user);
	void deleteUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers(int pageNumber, int pageSize);
    List<User> getAllUsers();
    long countUsers();
    Role createRole(Role role);
    void addRoleToUser(String userEmail, Role rolename);
    
    
}
