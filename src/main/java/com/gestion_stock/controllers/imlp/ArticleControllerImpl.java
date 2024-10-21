package com.gestion_stock.controllers.imlp;

import com.gestion_stock.controllers.ArticleController;
import com.gestion_stock.dto.ArticleDto;
import com.gestion_stock.services.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Api("")
public class ArticleControllerImpl implements ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("gestionStock")
    @ApiOperation("")
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
