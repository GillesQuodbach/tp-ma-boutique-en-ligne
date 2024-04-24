package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class BusinessTest {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void testAddArticle() {
        //GIVEN
        Category newCategory = categoryRepository.save(new Category(null, "newCategory", "tous les ordinateurs et accessoires", null));
        articleRepository.save(new Article(null, "Super PC", 200, newCategory, null));

        //WHEN
        Article article = articleRepository.findByCategoryName("newCategory").get(0);

        //THEN
        assertEquals("Super PC", article.getDescription());
    }

    @Test
    public void FindListArticle() {
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotEmpty();
    }
}
