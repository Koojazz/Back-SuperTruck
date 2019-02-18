package cap.capgemini.poe.aston.services;

import java.util.List;

import cap.capgemini.poe.aston.entities.User;

public interface IUserService {
	
	public User createUser(User user);
	public User getUser(Long id);
	public User editUser(Long id, User user);
	public void deleteUser(User user);
    public void deleteUser(Long id);
    public List<User> getAllUsers(int pageNumber, int pageSize);
    public List<User> getAllUsers();
    public long countUsers();
}
