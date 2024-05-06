package fr.fms.web;

import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * contact controller
 *
 * @author Gilles
 */
@Controller
public class ContactController {
    private static final String LIST_ARTICLE = "listContacts";
    private static final String PAGES = "pages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String CAT_LIST = "catList";
    private static final String KEYWORD = "keyword";
    private final ContactRepository contactRepository;
    private final CategoryRepository categoryRepository;

    String contactString = "contact";

    @Autowired
    public ContactController(ContactRepository contactRepository, CategoryRepository categoryRepository) {
        this.contactRepository = contactRepository;
        this.categoryRepository = categoryRepository;

    }

    /**
     * "/index" mapping
     *
     * @param model         spring model
     * @param @RequestParam (name = page name, defaultValue = default page number, int page = page number)
     * @author Gilles
     */
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "name", defaultValue = "") String name,
                        @RequestParam(name = "firstName", defaultValue = "") String firstName,
                        @RequestParam(name = "category", defaultValue = "") String category,
                        Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            if (!name.isEmpty() || !firstName.isEmpty()) {
                // Recherche par nom et prénom
                Page<Contact> contacts = contactRepository.findByNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(name, firstName, PageRequest.of(page, 5));
                model.addAttribute(LIST_ARTICLE, contacts.getContent());
                model.addAttribute(PAGES, new int[contacts.getTotalPages()]);
                model.addAttribute(CURRENT_PAGE, page);
                model.addAttribute(KEYWORD, name);
            } else if (!category.isEmpty()) {
                // Recherche par catégorie
                Page<Contact> contactsByCategory = contactRepository.findByCategoryName(category, PageRequest.of(page, 5));
                model.addAttribute(LIST_ARTICLE, contactsByCategory.getContent());
                model.addAttribute(PAGES, new int[contactsByCategory.getTotalPages()]);
                model.addAttribute(CURRENT_PAGE, page);
                model.addAttribute("category", category);
            } else {
                // Affichage de tous les contacts
                Page<Contact> allContacts = contactRepository.findAll(PageRequest.of(page, 5));
                model.addAttribute(LIST_ARTICLE, allContacts.getContent());
                model.addAttribute(PAGES, new int[allContacts.getTotalPages()]);
                model.addAttribute(CURRENT_PAGE, page);
            }
        } else {
            // Si pas connecté affichage fake contact
            List<Contact> fakeContact = new ArrayList<>();

            fakeContact.add(new Contact((long)1,"Luke", "Skywalker", "luke@example.com", "0667890123", "789 Oak Street", null));
            fakeContact.add(new Contact((long)2,"Leia", "Organa", "leia@example.com", "0667890123", "789 Oak Street", null));
            fakeContact.add(new Contact((long)3,"Han", "Solo", "han@example.com", "0667890123", "789 Oak Street", null));
            fakeContact.add(new Contact((long)4,"Darth", "Vader", "vader@example.com", "0667890123", "789 Oak Street", null));
            fakeContact.add(new Contact((long)5,"Obi-Wan", "Kenobi", "obiwan@example.com", "0667890123", "789 Oak Street", null));

            model.addAttribute(LIST_ARTICLE, fakeContact);
        }
        //Affichage des catégories
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("categories", listCategories);
        return "contacts";
    }


    /**
     * add new contact mapping
     *
     * @param model spring model
     * @author Gilles
     */
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new Contact());
        List<Category> catList = categoryRepository.findAll();
        model.addAttribute(CAT_LIST, catList);
        return contactString;
    }

    /**
     * delete mapping
     *
     * @param id   contact id
     * @param page page number
     * @author Gilles
     * @params keyword searched keyword
     */
    @GetMapping("/delete")
    public String delete(Long id, Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "name", defaultValue = "") String name,
                         @RequestParam(name = "firstName", defaultValue = "") String firstName) {
        contactRepository.deleteById(id);

        // Création de l'URL de redirection avec les paramètres de recherche
        String redirectUrl = "/index?page=" + page;
        if (!name.isEmpty() || !firstName.isEmpty()) {
            redirectUrl += "&name=" + name + "&firstName=" + firstName;
        }

        return "redirect:" + redirectUrl;
    }

    /**
     * update contact mapping
     *
     * @param model spring model
     * @param id    article id to update
     * @author Gilles
     */

    @GetMapping("/update")
    public String update(Model model, Long id) {
        // on inject un article par defaut dans le formulaire de saisi
        Optional<Contact> contactToUpdate = contactRepository.findById(id);
        Contact contact = contactToUpdate.orElse(null);
        List<Category> catList = categoryRepository.findAll();
        model.addAttribute(CAT_LIST, catList);
        model.addAttribute(contactString, contact);
        return "update";
    }


    @PostMapping("/toUpdate")
    public String toUpdate(Long id, @Valid @ModelAttribute("updatedContact") ContactToUpdate contactToUpdate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return contactString;

        Long categoryId = contactToUpdate.getCategoryId();
        Optional<Category> contactCat = categoryRepository.findById(categoryId);
        Optional<Contact> contactToUpdateOptionnal = contactRepository.findById(id);
        if (contactToUpdateOptionnal.isPresent() && contactCat.isPresent()) {
            Contact contact = contactToUpdateOptionnal.get();
            Category cat = contactCat.get();

            contact.setId(contact.getId());
            contact.setName(contactToUpdate.getName());
            contact.setFirstName(contactToUpdate.getFirstName());
            contact.setEmail(contactToUpdate.getEmail());
            contact.setPhone(contactToUpdate.getPhone());
            contact.setAddress(contactToUpdate.getAddress());
            contact.setCategory(cat);
            contactRepository.save(contact);
        }
        return "redirect:/index";
    }

    /**
     * save contact
     *
     * @param bindingResult validation object
     * @author Gilles
     */
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("contact") ContactDTO contactDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Category> catList = categoryRepository.findAll();
            model.addAttribute(CAT_LIST, catList);
            return contactString;
        }

        Category category = categoryRepository.findById(contactDTO.getCategoryId()).orElse(null);
        if (category != null) {
            Contact contact = new Contact(contactDTO.getName(), contactDTO.getFirstName(), contactDTO.getEmail(), contactDTO.getPhone(), contactDTO.getAddress());
            contact.setCategory(category);
            contactRepository.save(contact);

        } else {
            System.out.println("aucune catégorie trouvée");
        }
        return "redirect:/index";
    }


    /**
     * forbidden page
     *
     * @author Gilles
     */
    @GetMapping("/403")
    public String error() {
        return "403";
    }

}

