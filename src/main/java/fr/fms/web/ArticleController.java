//package fr.fms.web;
//
//import fr.fms.business.IBusinessImpl;
//import fr.fms.dao.ArticleRepository;
//import fr.fms.dao.CategoryRepository;
//import fr.fms.entities.Article;
//import fr.fms.entities.ArticleDTO;
//import fr.fms.entities.ArticleToUpdateDTO;
//import fr.fms.entities.Category;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
///**
// * article controller
// *
// * @author Gilles
// */
//@Controller
//public class ArticleController {
//    private static final String LIST_ARTICLE = "listArticle";
//    private static final String PAGES = "pages";
//    private static final String CURRENT_PAGE = "currentPage";
//    private static final String CAT_LIST = "catList";
//    private static final String KEYWORD = "keyword";
//    private final ArticleRepository articleRepository;
//    private final CategoryRepository categoryRepository;
//    private final IBusinessImpl business;
//    String articleString = "article";
//
//    @Autowired
//    public ArticleController(ArticleRepository articleRepository, CategoryRepository categoryRepository, IBusinessImpl business) {
//        this.articleRepository = articleRepository;
//        this.categoryRepository = categoryRepository;
//        this.business = business;
//    }
//
//    /**
//     * "/index" mapping
//     *
//     * @param model         spring model
//     * @param @RequestParam (name = page name, defaultValue = default page number, int page = page number)
//     * @author Gilles
//     */
//    @GetMapping("/index")
//    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
//                        @RequestParam(name = "keyword", defaultValue = "") String kw,
//                        @RequestParam(name = "category", defaultValue = "") String category) {
//
//        model.addAttribute("cartSize", business.getCartSize());
//        if (!kw.isEmpty()) {
//            Page<Article> articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page, 5));
//            model.addAttribute(LIST_ARTICLE, articles.getContent());
//            model.addAttribute(PAGES, new int[articles.getTotalPages()]);
//            model.addAttribute(CURRENT_PAGE, page);
//            model.addAttribute(KEYWORD, kw);
//
//
//        } else if (!category.isEmpty()) {
//            Page<Article> articlesByCategory = articleRepository.findByCategoryName(category, PageRequest.of(page, 5));
//            model.addAttribute(LIST_ARTICLE, articlesByCategory.getContent());
//            model.addAttribute(PAGES, new int[articlesByCategory.getTotalPages()]);
//            model.addAttribute(CURRENT_PAGE, page);
//            model.addAttribute("category", category);
//
//
//        } else {
//            Page<Article> allArticles = articleRepository.findAll(PageRequest.of(page, 5));
//            model.addAttribute(LIST_ARTICLE, allArticles.getContent());
//            model.addAttribute(PAGES, new int[allArticles.getTotalPages()]);
//            model.addAttribute(CURRENT_PAGE, page);
//        }
//        List<Category> listCategories = categoryRepository.findAll();
//        model.addAttribute("categories", listCategories);
//        return "articles";
//    }
//
//
//    /**
//     * add new article mapping
//     *
//     * @param model spring model
//     * @author Gilles
//     */
//    @GetMapping("/article")
//    public String article(Model model) {
//        model.addAttribute("article", new Article());
//        List<Category> catList = categoryRepository.findAll();
//        model.addAttribute(CAT_LIST, catList);
//        return articleString;
//    }
//
//    /**
//     * delete mapping
//     *
//     * @param id   article id
//     * @param page page number
//     * @author Gilles
//     * @params keyword searched keyword
//     */
//    @GetMapping("/delete")
//    public String delete(Long id, int page, String keyword) {
//        articleRepository.deleteById(id);
//        return "redirect:/index?page=" + page + "&keyword=" + keyword;
//    }
//
//    /**
//     * update article mapping
//     *
//     * @param model spring model
//     * @param id    article id to update
//     * @author Gilles
//     */
//
//    @GetMapping("/update")
//    public String update(Model model, Long id) {
//        // on inject un article par defaut dans le formulaire de saisi
//        Optional<Article> articleToUpdate = articleRepository.findById(id);
//        Article article = articleToUpdate.orElse(null);
//        List<Category> catList = categoryRepository.findAll();
//        model.addAttribute(CAT_LIST, catList);
//        model.addAttribute(articleString, article);
//        return "update";
//    }
//
//    /**
//     * save article
//     *
//     * @param updatedArticleDTO article to update
//     * @param bindingResult     validation object
//     * @author Gilles
//     */
//
//    @PostMapping("/toUpdate")
//    public String toUpdate(Long id, @Valid @ModelAttribute("updatedArticle") ArticleToUpdateDTO updatedArticleDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) return articleString;
//
//        Long categoryId = updatedArticleDTO.getCategoryId();
//        Optional<Category> articleCat = categoryRepository.findById(categoryId);
//        Optional<Article> articleToUpdate = articleRepository.findById(id);
//        if (articleToUpdate.isPresent() && articleCat.isPresent()) {
//            Article article = articleToUpdate.get();
//            Category cat = articleCat.get();
//            article.setId(updatedArticleDTO.getId());
//            article.setDescription(updatedArticleDTO.getDescription());
//            article.setPrice(updatedArticleDTO.getPrice());
//
//            article.setCategory(cat);
//            articleRepository.save(article);
//        }
//        return "redirect:/index";
//    }
//
//    /**
//     * save article
//     *
//     * @param articleDTO    article to save
//     * @param bindingResult validation object
//     * @author Gilles
//     */
//    @PostMapping("/save")
//    public String save(@Valid @ModelAttribute("article") ArticleDTO articleDTO, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            List<Category> catList = categoryRepository.findAll();
//            model.addAttribute(CAT_LIST, catList);
//            return articleString;
//        }
//        Article article = new Article(articleDTO.getDescription(), articleDTO.getPrice());
//        Category category = categoryRepository.findById(articleDTO.getCategoryId()).orElse(null);
//        article.setCategory(category);
//        articleRepository.save(article);
//        return "redirect:/index";
//    }
//
//
//    /**
//     * forbidden page
//     *
//     * @author Gilles
//     */
//    @GetMapping("/403")
//    public String error() {
//        return "403";
//    }
//
//}
//
//