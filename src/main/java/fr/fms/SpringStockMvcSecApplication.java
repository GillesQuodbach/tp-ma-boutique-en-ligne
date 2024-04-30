package fr.fms;

import fr.fms.dao.*;
import fr.fms.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringStockMvcSecApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringStockMvcSecApplication.class);

    private final ArticleRepository articleRepository;

    private final ContactRepository contactRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringStockMvcSecApplication(ArticleRepository articleRepository, ContactRepository contactRepository, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.articleRepository = articleRepository;
        this.contactRepository = contactRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringStockMvcSecApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category perso = categoryRepository.save(new Category(null, "Perso", "contacts personnels", null));
        Category pro = categoryRepository.save(new Category(null, "Pro", "contacts professionnels", null));
        Category famille = categoryRepository.save(new Category(null, "Famille", "Membres de la famille", null));
        Category autre = categoryRepository.save(new Category(null, "Autre", "Autres contact", null));

        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",perso));
        contactRepository
                .save(new Contact("sarah", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",perso));
        contactRepository
                .save(new Contact("etanou", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",perso));
        contactRepository
                .save(new Contact("livio", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",perso));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",perso));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",pro));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",pro));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",pro));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",pro));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",pro));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",pro));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",famille));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",famille));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",famille));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",famille));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",famille));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",famille));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",autre));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",autre));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",autre));
        contactRepository
                .save(new Contact("gilles", "quodbach" ,"quodbach.gilles@gmail.com","0613053349","1318 chemin Harrixurieta",autre));
        articleRepository.findAll().forEach(a -> logger.info(a.toString()));

        generateData();
    }

    public void generateData() {
        Role user = roleRepository.save(new Role("users", null));
        Role admin = roleRepository.save(new Role("admins", null));

        createUserWithRoles("fred2024", "fmsAcad@2024$", true, admin, user);
        createUserWithRoles("Josette", "@Pelote2024!", true, user);

    }

    private void createUserWithRoles(String username, String password, boolean active, Role... roles) {
        List<Role> userRoles = Arrays.asList(roles);
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(username, encodedPassword, active, userRoles, null));
    }

}

