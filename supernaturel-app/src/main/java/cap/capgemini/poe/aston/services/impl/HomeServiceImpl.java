package cap.capgemini.poe.aston.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cap.capgemini.poe.aston.entities.Home;
import cap.capgemini.poe.aston.repositories.IHomeRepository;
import cap.capgemini.poe.aston.services.IHomeService;

@Service
public class HomeServiceImpl implements IHomeService{

	@Autowired
	private IHomeRepository homeRepository;

	@Override
	public Home createHome(Home home) {
		return this.homeRepository.save(home);
	}

	@Override
	public Home getHome(Long id) {
		return this.homeRepository.findById(id).orElse(null);
	}

	@Override
	public Home editHome(Long id, Home home) {
		final Home c = this.homeRepository.findById(id).orElse(null);
		c.setParagraphe(home.getParagraphe());
		return this.homeRepository.save(c);
	}

	@Override
	public void deleteHome(Long id) {
		this.homeRepository.deleteById(id);
	}

	@Override
	public List<Home> getAllHomes(int pageNumber, int pageSize) {
		return this.homeRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Home> getAllHomes() {
		return this.homeRepository.findAll();
	}

	@Override
	public long countHome() {
		return this.homeRepository.count();
	}

	@Override
	public void deleteHome(Home home) {
		this.homeRepository.delete(home);
	}
}
