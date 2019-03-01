package cap.capgemini.poe.aston;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cap.capgemini.poe.aston.entities.Category;
import cap.capgemini.poe.aston.entities.Contact;
import cap.capgemini.poe.aston.entities.Home;
import cap.capgemini.poe.aston.entities.Product;
import cap.capgemini.poe.aston.entities.Role;
import cap.capgemini.poe.aston.entities.RoleName;
import cap.capgemini.poe.aston.entities.User;
import cap.capgemini.poe.aston.properties.FileStorageProperties;
import cap.capgemini.poe.aston.repositories.ICategoryRepository;
import cap.capgemini.poe.aston.repositories.IContactRepository;
import cap.capgemini.poe.aston.repositories.IHomeRepository;
import cap.capgemini.poe.aston.repositories.IProductRepository;
import cap.capgemini.poe.aston.repositories.IRoleRepository;
import cap.capgemini.poe.aston.repositories.IUserRepository;
import cap.capgemini.poe.aston.services.IUserService;
import cap.capgemini.poe.aston.services.impl.UserServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
@EnableJpaAuditing
public class SupernaturelAppApplication implements CommandLineRunner {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired 
	private IRoleRepository roleRepository;
	
	@Autowired
	private IContactRepository contactRepository;

	@Autowired
	private IHomeRepository homeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SupernaturelAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.save(new Role(null, RoleName.ROLE_ADMIN)));
		roles.add(roleRepository.save(new Role(null, RoleName.ROLE_USER)));
			
		userService.createUser(new User(null, "bob", "square-sponge", passwordEncoder.encode("12345"), "bob@sponge.com", null, null, roles, null));
		userService.createUser(new User(null, "bruce", "wayne", passwordEncoder.encode("batman"), "bruce@wayne.com", null, null, null, null));
		userService.createUser(new User(null, "naruto", "uzumaki", passwordEncoder.encode("kyubi"), "naruto@uzumaki.com", null, null, null, null));
		
		Category c1 = new Category(null, "sandwich", null);
		Category c2 = new Category(null, "salade", null);
		Category c3 = new Category(null, "soupe", null);
		
		categoryRepository.save(c1);
		categoryRepository.save(c2);
		categoryRepository.save(c3);
		
		productRepository.save(new Product(null, "hambourgeois", c1, 8.50, "the best burger", "http://yumm.com/wp-content/uploads/2016/04/1388954443-fergburger-queenstown-new-zealand.jpg", null, null));
		productRepository.save(new Product(null, "taboulé", c2, 7.50, "the best of lebanon", "https://cdn-elle.ladmedia.fr/var/plain_site/storage/images/elle-a-table/recettes-de-cuisine/taboule-2077780/22032212-3-fre-FR/Taboule.jpg", null, null));
		productRepository.save(new Product(null, "soupe miso", c3, 6.50, "the best miso", "https://s3-eu-west-1.amazonaws.com/mae-deli/wp-content/uploads/2017/11/06141329/c.jpg", null, null));
//		productRepository.findAll().forEach(c -> {
//			System.out.println(c);
//		});
		

		this.userRepository.save(new User(null, "bob", "square-sponge", "1234", "bob@sponge.com", null, null,Role.ADMIN));
		this.userRepository.save(new User(null, "bruce", "wayne", "batman", "bruce@wayne.com", null, null,Role.USER));
		this.userRepository.save(new User(null, "naruto", "uzumaki", "kyubi", "naruto@uzumaki.com", null, null,Role.USER));
		this.userRepository.findAll().forEach(c -> {
			System.out.println(c);
		});

		final Category c1 = new Category(null, "sandwich", null);
		final Category c2 = new Category(null, "salade", null);
		final Category c3 = new Category(null, "soupe", null);
		this.categoryRepository.save(c1);
		this.categoryRepository.save(c2);
		this.categoryRepository.save(c3);

		this.productRepository.save(new Product(null, "hambourgeois", c1, 8.50, "the best burger", "http://yumm.com/wp-content/uploads/2016/04/1388954443-fergburger-queenstown-new-zealand.jpg", null));
		this.productRepository.save(new Product(null, "taboulé", c2, 7.50, "the best of lebanon", "https://cdn-elle.ladmedia.fr/var/plain_site/storage/images/elle-a-table/recettes-de-cuisine/taboule-2077780/22032212-3-fre-FR/Taboule.jpg", null));
		this.productRepository.save(new Product(null, "soupe miso", c3, 6.50, "the best miso", "https://s3-eu-west-1.amazonaws.com/mae-deli/wp-content/uploads/2017/11/06141329/c.jpg", null));

		this.contactRepository.save(new Contact(Long.valueOf(1),"testBdd@mail.com","0652521415"));

		this.homeRepository.save(new Home(Long.valueOf(1),"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));



		//		productRepository.findAll().forEach(c -> {
		//			System.out.println(c);
		//		});

	}

}

