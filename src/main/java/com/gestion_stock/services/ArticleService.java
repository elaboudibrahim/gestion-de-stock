package com.gestion_stock.services;

import com.gestion_stock.dto.ArticleDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleService {
    public ArticleDto save(ArticleDto articleDto);
    public ArticleDto findById(Integer id);
    public List<ArticleDto> findAll();
    public ArticleDto findByCode(String code);
    public void delete(Integer id);
}
