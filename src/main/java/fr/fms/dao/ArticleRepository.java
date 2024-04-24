package fr.fms.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import fr.fms.entities.*;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>{

Page<Article> findByDescriptionContains(String description, Pageable pageable);
Page<Article> findByCategoryName(String category, Pageable pageable);
List<Article> findByCategoryName(String categoryName);

void deleteById(Long id);
}