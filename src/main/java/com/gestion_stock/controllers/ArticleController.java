package com.gestion_stock.controllers;

import com.gestion_stock.dto.ArticleDto;
import com.gestion_stock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("gestionStock")
    public ArticleDto save(@RequestBody ArticleDto articleDto){
        return articleService.save(articleDto);
    }
    @GetMapping("gestionStock/{idArticle}")
    public ArticleDto findById(@RequestParam(value = "idArticle") Integer id){
        return articleService.findById(id);
    }
    @GetMapping("gestionStock/all")
    public List<ArticleDto> findAll(){
        return articleService.findAll();
    }
    @GetMapping("gestionStock/{codeArticle}")
    public ArticleDto findByCode(@RequestParam(value = "codeArticle") String code){
        return articleService.findByCode(code);
    }
    @PostMapping(value = "gestionStock/{idArticle}")
    public void delete(@RequestParam(value = "idArticle") Integer id){
        articleService.delete(id);
    }
}
