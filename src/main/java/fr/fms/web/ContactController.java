package fr.fms.web;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * article controller
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
    private final IBusinessImpl business;
    String contactString = "contact";

    @Autowired
    public ContactController(ContactRepository contactRepository, CategoryRepository categoryRepository, IBusinessImpl business) {
        this.contactRepository = contactRepository;
        this.categoryRepository = categoryRepository;
        this.business = business;
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
                        @RequestParam(name = "keyword", defaultValue = "") String kw,
                        @RequestParam(name = "category", defaultValue = "") String category) {

        model.addAttribute("cartSize", business.getCartSize());
        if (!kw.isEmpty()) {
            Page<Contact> contacts = contactRepository.findByNameContainingIgnoreCase(kw, PageRequest.of(page, 5));
            model.addAttribute(LIST_ARTICLE, contacts.getContent());
            model.addAttribute(PAGES, new int[contacts.getTotalPages()]);
            model.addAttribute(CURRENT_PAGE, page);
            model.addAttribute(KEYWORD, kw);


        } else if (!category.isEmpty()) {
            Page<Contact> contactsByCategory = contactRepository.findByCategoryName(category, PageRequest.of(page, 5));
            model.addAttribute(LIST_ARTICLE, contactsByCategory.getContent());
            model.addAttribute(PAGES, new int[contactsByCategory.getTotalPages()]);
            model.addAttribute(CURRENT_PAGE, page);
            model.addAttribute("category", category);


        } else {
            Page<Contact> allContacts = contactRepository.findAll(PageRequest.of(page, 5));
            model.addAttribute(LIST_ARTICLE, allContacts.getContent());
            model.addAttribute(PAGES, new int[allContacts.getTotalPages()]);
            model.addAttribute(CURRENT_PAGE, page);
        }
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("categories", listCategories);
        return "contact";
    }


    /**
     * add new article mapping
     *
     * @param model spring model
     * @author Gilles
     */
    @GetMapping("/contact")
    public String article(Model model) {
        model.addAttribute("contact", new Contact());
        List<Category> catList = categoryRepository.findAll();
        model.addAttribute(CAT_LIST, catList);
        return contactString;
    }

    /**
     * delete mapping
     *
     * @param id   article id
     * @param page page number
     * @author Gilles
     * @params keyword searched keyword
     */
    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        contactRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    /**
     * update article mapping
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
            contact.setName(contact.getName());
            contact.setFirstName(contact.getFirstName());

            contact.setCategory(cat);
            contactRepository.save(contact);
        }
        return "redirect:/index";
    }

    /**
     * save article
     *
     * @param articleDTO    article to save
     * @param bindingResult validation object
     * @author Gilles
     */
//    @PostMapping("/save")
//    public String save(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            List<Category> catList = categoryRepository.findAll();
//            model.addAttribute(CAT_LIST, catList);
//            return contactString;
//        }
//        Contact savedContact = new Contact(contact.getName(), contact.getFirstName());
//        Category category = categoryRepository.findById(contact.getCategory()).orElse(null);
//        savedContact.setCategory(category);
//        articleRepository.save(savedContact);
//        return "redirect:/index";
//    }


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

