package fr.fms.web;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    IBusinessImpl business;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("listArticles", business.getCartContent());
        return "cart";
    }

    @GetMapping("/addCart")
    public String addCart(Long id, Model model) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            business.addOneArticleToCart(article);
            model.addAttribute("listArticles", business.getCartContent());
            model.addAttribute("quantityArticle", business.getOrderItem(article).getQuantity());
            model.addAttribute("priceArticle", business.getOrderItem(article).getTotalPrice());
            return "cart";
        } else {
            return "403";
        }
    }
}

