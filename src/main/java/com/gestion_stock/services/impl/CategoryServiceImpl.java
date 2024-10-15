package com.gestion_stock.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.gestion_stock.dto.CategoryDto;
import com.gestion_stock.exception.EntityNotFoundException;
import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.model.Article;
import com.gestion_stock.repository.ArticleRepository;
import com.gestion_stock.repository.CategoryRepository;
import com.gestion_stock.services.CategoryService;
import com.gestion_stock.validator.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;
  private ArticleRepository articleRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
    this.categoryRepository = categoryRepository;
    this.articleRepository = articleRepository;
  }

  @Override
  public CategoryDto save(CategoryDto dto) {
    List<String> errors = CategoryValidator.validate(dto);
    if (!errors.isEmpty()) {
      throw new InvalidEntityException("La category n'est pas valide", errors);
    }
    return CategoryDto.fromEntity(
        categoryRepository.save(CategoryDto.toEntity(dto))
    );
  }

  @Override
  public CategoryDto findById(Integer id) {
    if (id == null) {
      return null;
    }
    return categoryRepository.findById(id)
        .map(CategoryDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune category avec l'ID = " + id + " n' ete trouve dans la BDD")
        );
  }

  @Override
  public CategoryDto findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      return null;
    }
    return categoryRepository.findCategoryByCode(code)
        .map(CategoryDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune category avec le CODE = " + code + " n' ete trouve dans la BDD"));
  }

  @Override
  public List<CategoryDto> findAll() {
    return categoryRepository.findAll().stream()
        .map(CategoryDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      return;
    }
//    List<Article> articles = articleRepository.findAllByCategoryId(id);
//    if (!articles.isEmpty()) {
//      throw new InvalidOperationException("Impossible de supprimer cette categorie qui est deja utilise");
//    }
    categoryRepository.deleteById(id);
  }
}
