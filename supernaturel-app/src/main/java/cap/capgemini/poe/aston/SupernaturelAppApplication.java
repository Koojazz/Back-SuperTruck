package cap.capgemini.poe.aston;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cap.capgemini.poe.aston.entities.Category;
import cap.capgemini.poe.aston.entities.Contact;
import cap.capgemini.poe.aston.entities.Product;
import cap.capgemini.poe.aston.entities.User;
import cap.capgemini.poe.aston.repositories.ICategoryRepository;
import cap.capgemini.poe.aston.repositories.IContactRepository;
import cap.capgemini.poe.aston.repositories.IProductRepository;
import cap.capgemini.poe.aston.repositories.IUserRepository;

@SpringBootApplication
public class SupernaturelAppApplication implements CommandLineRunner {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private IContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(SupernaturelAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.userRepository.save(new User(null, "bob", "square-sponge", "1234", "bob@sponge.com", null, null));
		this.userRepository.save(new User(null, "bruce", "wayne", "batman", "bruce@wayne.com", null, null));
		this.userRepository.save(new User(null, "naruto", "uzumaki", "kyubi", "naruto@uzumaki.com", null, null));
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

		this.contactRepository.save(new Contact(null,"testBdd@mail.com","0652521415"));


		//		productRepository.findAll().forEach(c -> {
		//			System.out.println(c);
		//		});

	}

}

