//package fr.fms;
//
//import fr.fms.dao.CategoryRepository;
//import fr.fms.dao.ContactRepository;
//import fr.fms.entities.Category;
//import fr.fms.entities.Contact;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.*;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ContactControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ContactRepository contactRepository;
//
//    @MockBean
//    private CategoryRepository categoryRepository;
//
//    // Test display index
//    @Test
//    void testIndex() throws Exception {
//        Page<Contact> emptyPage = new PageImpl<>(Collections.emptyList());
//        when(contactRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);
//
//        mockMvc.perform(get("/index"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("contacts"));
//    }
//
//    // Test access to /contact when user logged in (with role users)
//    @Test
//    @WithMockUser(username = "gege", password = "gegedu40!", roles = "users")
//    void testContactAuthorized() throws Exception {
//        mockMvc.perform(get("/contact"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("contact"));
//
//    }
//
//    @Test
//    void displayTheRightCategoryWhenSelected() throws Exception {
//        // Simulation d'une catégorie
//        Category category = new Category("test");
//        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
//
//        // Simulation des contacts de la catégorie
//        List<Contact> mockedContacts = new ArrayList<>();
//        // Ajout de contacts
//        mockedContacts.add(new Contact("Doe", "John", "john@example.com", "123456789", "Address 1"));
//        mockedContacts.add(new Contact("Doe", "Jane", "jane@example.com", "987654321", "Address 2"));
//        when(contactRepository.findByCategoryName(any(), any())).thenReturn(new PageImpl<>(mockedContacts));
//
//        // Requête
//        mockMvc.perform(get("/index").param("category", "test"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("contacts"))
//                .andExpect(model().attributeExists("listContacts"))
//                .andExpect(model().attribute("listContacts", hasSize(2)))
//                .andExpect(model().attribute("listContacts", hasItem(
//                        allOf(
//                                hasProperty("firstName", is("John")),
//                                hasProperty("email", is("john@example.com")),
//                                hasProperty("phone", is("123456789")),
//                                hasProperty("address", is("Address 1"))
//                        )
//                )))
//                .andExpect(model().attribute("listContacts", hasItem(
//                        allOf(
//                                hasProperty("firstName", is("Jane")),
//                                hasProperty("email", is("jane@example.com")),
//                                hasProperty("phone", is("987654321")),
//                                hasProperty("address", is("Address 2"))
//                        )
//                )));
//    }
//
//
//}
