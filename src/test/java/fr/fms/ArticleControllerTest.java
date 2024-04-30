package fr.fms;

import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest

@AutoConfigureMockMvc
class ArticleControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleRepository articleRepository;


    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void testIndex() throws Exception {
        Page<Article> emptyPage = new PageImpl<>(Collections.emptyList());
        when(articleRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("articles"));

    }

    @Test
    @WithMockUser(username = "gege", password = "gegedu40!", roles = "admins")
    void testArticle() throws Exception {
        mockMvc.perform(get("/article"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("article"));

    }

    @Test
    @WithMockUser(username = "gege", password = "gegedu40!", roles = "admins")
    void testDelete() throws Exception {
        Long articleId = 1L;
        int page = 1;
        String kw = "";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/delete")
                        .param("id", String.valueOf(articleId)).param("page", String.valueOf(page)).param("keyword", kw))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index?page=" + page + "&keyword=" + kw));

    }
}
