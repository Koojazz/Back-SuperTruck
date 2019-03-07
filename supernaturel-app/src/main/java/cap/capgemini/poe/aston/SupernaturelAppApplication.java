package cap.capgemini.poe.aston;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
import cap.capgemini.poe.aston.services.IUserService;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
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
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IContactRepository contactRepository;

	@Autowired
	private IHomeRepository homeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SupernaturelAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		final Set<Role> role1 = new HashSet<>();
		role1.add(this.roleRepository.save(new Role(null, RoleName.ROLE_ADMIN)));
		final Set<Role> role2 = new HashSet<>();
		role2.add(this.roleRepository.save(new Role(null, RoleName.ROLE_USER)));

		this.contactRepository.save(new Contact(null, "cricri@gmail.com", "0600000000"));
		this.homeRepository.save(new Home(null, "Du frais maison... mais en camion! Venez découvrir notre concept de restauration responsable ..."));

		this.userService.createUser(new User(null, "bob", "square-sponge", this.passwordEncoder.encode("12345"), "bob@sponge.com",
				null, null, role1, null));

		final Category c1 = new Category(null, "sandwich", null);
		final Category c2 = new Category(null, "salade", null);
		final Category c3 = new Category(null, "soupe", null);

		this.categoryRepository.save(c1);
		this.categoryRepository.save(c2);
		this.categoryRepository.save(c3);

		this.productRepository.save(new Product(null, "Hambourgeois", c1, 8.50, "Un hambourgeois vegan à l'algue kombu.",
				"http://laurencariscooks.com/1_lcc/wp-content/uploads/2016/08/Black-Bean-Burgers-4-600x600.jpg", null));
		this.productRepository.save(new Product(null, "Taboulé", c2, 7.50, "Un taboulé savoureux, aux 3 légumes.",
				"https://cdn-elle.ladmedia.fr/var/plain_site/storage/images/elle-a-table/recettes-de-cuisine/taboule-2077780/22032212-3-fre-FR/Taboule.jpg",
				null));
		this.productRepository.save(new Product(null, "Soupe miso", c3, 6.50, "Une soupe onctueuse, aux champignons et Cébette.",
				"https://www.foodette.fr/files/products/soupemisodashitofuwakamealgueshiitakeciboule-squashed-2.JPG", null));

		// productRepository.findAll().forEach(c -> {
		// System.out.println(c);
		// });

	}

}
