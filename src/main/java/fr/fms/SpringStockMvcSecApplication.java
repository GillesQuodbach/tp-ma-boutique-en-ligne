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

    private final ContactRepository contactRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringStockMvcSecApplication(ContactRepository contactRepository, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
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

        Category PERSONAL = categoryRepository.save(new Category(null, "Personnal", "personnal contacts", null));
        Category WORK = categoryRepository.save(new Category(null, "Professional", "professional contacts", null));
        Category FAMILY = categoryRepository.save(new Category(null, "Family", "family members", null));
        Category OTHER = categoryRepository.save(new Category(null, "Other", "other contacts", null));

        contactRepository.save(new Contact("Sophia", "Lopez", "example4@gmail.com", "0667890123", "789 Oak Street", WORK));
        contactRepository.save(new Contact("Elijah", "Brown", "example8@gmail.com", "0656789012", "123 Main Street", PERSONAL));
        contactRepository.save(new Contact("Lucas", "Rodriguez", "example2@gmail.com", "0689012345", "789 Oak Street", PERSONAL));
        contactRepository.save(new Contact("Lucas", "Brown", "example3@gmail.com", "0667890123", "1011 Pine Street", WORK));
        contactRepository.save(new Contact("Elijah", "Moore", "example2@gmail.com", "0689012345", "1415 Cedar Street", WORK));
        contactRepository.save(new Contact("Sophia", "Williams", "example9@gmail.com", "0667890123", "2021 Chestnut Street", WORK));
        contactRepository.save(new Contact("Charlotte", "Johnson", "example6@gmail.com", "0634567890", "123 Main Street", PERSONAL));
        contactRepository.save(new Contact("Charlotte", "Brown", "example5@gmail.com", "0623456789", "1819 Walnut Street", PERSONAL));
        contactRepository.save(new Contact("Sophia", "Davis", "example9@gmail.com", "0667890123", "2223 Spruce Street", OTHER));
        contactRepository.save(new Contact("Elijah", "Miller", "example9@gmail.com", "0645678901", "2223 Spruce Street", PERSONAL));
        contactRepository.save(new Contact("Isabella", "Jackson", "example6@gmail.com", "0690123456", "1819 Walnut Street", PERSONAL));
        contactRepository.save(new Contact("James", "Moore", "example5@gmail.com", "0623456789", "1213 Maple Street", FAMILY));
        contactRepository.save(new Contact("Amelia", "Martin", "example3@gmail.com", "0634567890", "1617 Birch Street", WORK));
        contactRepository.save(new Contact("Sarah", "Williams", "example4@gmail.com", "0634567890", "2223 Spruce Street", PERSONAL));
        contactRepository.save(new Contact("Livio", "Davis", "example2@gmail.com", "0623456789", "123 Main Street", FAMILY));
        contactRepository.save(new Contact("Mia", "Quodbach", "example3@gmail.com", "0623456789", "1011 Pine Street", PERSONAL));
        contactRepository.save(new Contact("James", "Brown", "example2@gmail.com", "0656789012", "1617 Birch Street", FAMILY));
        contactRepository.save(new Contact("Gilles", "Taylor", "example10@gmail.com", "0634567890", "1011 Pine Street", WORK));
        contactRepository.save(new Contact("Oliver", "Hernandez", "example4@gmail.com", "0612345678", "456 Elm Street", FAMILY));
        contactRepository.save(new Contact("Charlotte", "Hernandez", "example10@gmail.com", "0690123456", "456 Elm Street", FAMILY));
        contactRepository.save(new Contact("Elijah", "Moore", "example1@gmail.com", "0612345678", "789 Oak Street", WORK));
        contactRepository.save(new Contact("Oliver", "Moore", "example6@gmail.com", "0690123456", "789 Oak Street", OTHER));
        contactRepository.save(new Contact("William", "Martinez", "example2@gmail.com", "0612345678", "123 Main Street", OTHER));
        contactRepository.save(new Contact("Isabella", "Lopez", "example3@gmail.com", "0645678901", "123 Main Street", PERSONAL));
        contactRepository.save(new Contact("Benjamin", "Moore", "example4@gmail.com", "0678901234", "2021 Chestnut Street", WORK));
        contactRepository.save(new Contact("Liam", "Anderson", "example7@gmail.com", "0612345678", "1617 Birch Street", PERSONAL));
        contactRepository.save(new Contact("Elijah", "Williams", "example9@gmail.com", "0623456789", "1011 Pine Street", PERSONAL));
        contactRepository.save(new Contact("Ava", "Anderson", "example8@gmail.com", "0612345678", "1617 Birch Street", PERSONAL));
        contactRepository.save(new Contact("William", "Miller", "example6@gmail.com", "0601234567", "456 Elm Street", OTHER));
        contactRepository.save(new Contact("Noah", "Taylor", "example8@gmail.com", "0656789012", "1011 Pine Street", OTHER));
        contactRepository.save(new Contact("Oliver", "Garcia", "example8@gmail.com", "0612345678", "2223 Spruce Street", OTHER));
        contactRepository.save(new Contact("Benjamin", "Wilson", "example1@gmail.com", "0689012345", "123 Main Street", PERSONAL));
        contactRepository.save(new Contact("Ava", "Martin", "example2@gmail.com", "0656789012", "1819 Walnut Street", OTHER));
        contactRepository.save(new Contact("Emma", "Anderson", "example6@gmail.com", "0667890123", "1011 Pine Street", WORK));
        contactRepository.save(new Contact("Sarah", "Lopez", "example5@gmail.com", "0690123456", "1617 Birch Street", WORK));
        contactRepository.save(new Contact("Liam", "Lopez", "example10@gmail.com", "0612345678", "1011 Pine Street", OTHER));
        contactRepository.save(new Contact("Elijah", "Rodriguez", "example4@gmail.com", "0656789012", "1415 Cedar Street", WORK));
        contactRepository.save(new Contact("Sarah", "Taylor", "example5@gmail.com", "0601234567", "1415 Cedar Street", PERSONAL));
        contactRepository.save(new Contact("Benjamin", "Thomas", "example4@gmail.com", "0656789012", "1213 Maple Street", WORK));
        contactRepository.save(new Contact("Olivia", "Jones", "example9@gmail.com", "0656789012", "789 Oak Street", WORK));

        generateData();
    }

    public void generateData() {
        Role user = roleRepository.save(new Role("users", null));

        createUserWithRoles("fred2024", "fmsAcad@2024$", true, user);
        createUserWithRoles("Josette", "@Pelote2024!", true, user);

    }

    private void createUserWithRoles(String username, String password, boolean active, Role... roles) {
        List<Role> userRoles = Arrays.asList(roles);
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(username, encodedPassword, active, userRoles, null));
    }

}

