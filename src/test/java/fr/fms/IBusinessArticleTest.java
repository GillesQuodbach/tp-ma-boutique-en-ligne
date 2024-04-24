/*package fr.fms;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import javafx.beans.binding.When;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IBusinessArticleTest {

    public static Article article;
    public static Category category;

    @Mock
    static ArticleRepository articleRepository;

    @Mock
    static CategoryRepository categoryRepository;

    @InjectMocks
    IBusinessImpl businessArticle;

    @BeforeAll
    static void initializeData(){

        Category newCategory = categoryRepository.save(new Category(null, "newCategory", "tous les ordinateurs et accessoires", null));
        articleRepository.save(new Article(null, "Super PC", 200, newCategory, null));

        /*category = new Category(null, "newCategory", "tous les ordinateurs et accessoires", null);
        article = new Article(null, "Super PC", 200, null);
    }*/

 /*   @Test
    void ShouldFindByCategory(){
        //GIVEN
        List<Article> articleList = new ArrayList<>();
        articleList.add(article);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.ofNullable(category));
        when(articleRepository.findByCategoryName(category)).thenReturn(articleList);

        //WHEN
       List<Article> expectedResults = businessArticle.findByCategoryName(category);

        //THEN
        verify(articleRepository.findByCategoryName(category));
        assertNotEquals(0, expectedResults.size());
    }
}*/
