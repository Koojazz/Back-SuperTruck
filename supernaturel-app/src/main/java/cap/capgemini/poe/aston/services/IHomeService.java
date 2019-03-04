package cap.capgemini.poe.aston.services;

import java.util.List;

import cap.capgemini.poe.aston.entities.Home;

public interface IHomeService {

	public Home createHome(Home home);
	public Home getHome(Long id);
	public Home editHome(Long id, Home home);
	public void deleteHome(Home home);
	public void deleteHome(Long id);
	public List<Home> getAllHomes(int pageNumber, int pageSize);
	public List<Home> getAllHomes();
	public long countHome();
}
