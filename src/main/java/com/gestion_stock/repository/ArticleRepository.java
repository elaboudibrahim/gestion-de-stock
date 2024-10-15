package com.gestion_stock.repository;

import com.gestion_stock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Optional<Article> findArticleByCodeArticle(String code);
}
