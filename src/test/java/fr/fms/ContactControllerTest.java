package fr.fms;

import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactRepository contactRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    // Test display index
    @Test
    void testIndex() throws Exception {
        Page<Contact> emptyPage = new PageImpl<>(Collections.emptyList());
        when(contactRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("contacts"));

    }

    // Test access to /contact when user logged in
    @Test
    @WithMockUser(username = "gege", password = "gegedu40!")
    void testContactAuthorized() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("contact"));

    }

//    // Test access denied to /contact when user not logged in
//    @Test
//    void testContactUnauthorizedIfNotLoggedIn() throws Exception {
//        mockMvc.perform(get("/contact")).andExpect((status().isUnauthorized()));
//    }

}
