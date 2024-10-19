package com.gestion_stock.services.impl;

import com.gestion_stock.dto.ArticleDto;
import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.model.Article;
import com.gestion_stock.repository.ArticleRepository;
import com.gestion_stock.services.ArticleService;
import com.gestion_stock.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
// ** -> add a log error---
    @Override
    public ArticleDto save(ArticleDto articleDto){
        if(ArticleValidator.validate(articleDto).isEmpty()){
            throw new InvalidEntityException("Article is not valide",ArticleValidator.validate(articleDto));
        }
        Article article=ArticleDto.toEntity(articleDto);
        articleRepository.save(article);
        return articleDto;
    }

    @Override
    public ArticleDto findById(Integer id) {
        Article article =articleRepository.findById(id).orElseThrow(() -> new InvalidEntityException("Invalid id"));
        return ArticleDto.fromEntity(article);
    }

    @Override
    public List<ArticleDto> findAll() {
        List<Article> article=articleRepository.findAll();
        return article.stream().map(a->ArticleDto.fromEntity(a)).collect(Collectors.toList());
    }

    @Override
    public ArticleDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            //TO-DO add a log error
            return null;
        }
        Optional<Article> optionalArticle=articleRepository.findArticleByCodeArticle(code);

        return Optional.of(ArticleDto.fromEntity(optionalArticle.get()))
                .orElseThrow(()->new InvalidEntityException("Aucun article avec le CODE = " + code + " n' ete trouve dans la BDD"));
    }
    @Override
    public void delete(Integer id) {
        articleRepository.deleteById(id);
    }
}
