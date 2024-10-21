package com.gestion_stock.controllers;

import com.gestion_stock.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ArticleController {
    @ApiOperation(value = "Enregistrer un article",notes = "Cette methode permet d'enregistrer ou modifier un article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Cette methode permet d'enregistrer ou modifier un article"),
            @ApiResponse(code=400,message="L'objet article n'est pas valide")
    })
    public ArticleDto save(ArticleDto articleDto);
    @ApiOperation(value = "Rechaercher un article par id",notes = "cette methode permet de chercher un article par son id",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code=200,message = "L'article a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    public ArticleDto findById(Integer id);
    @ApiOperation(value = "Renvoi la liste des articles", notes = "Cette methode permet de chercher et renvoyer la liste des articles qui existent "
            + "dans la BDD",responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des article / Une liste vide")
    })
    public List<ArticleDto> findAll();
    @ApiOperation(value = "Rechercher un article par CODE", notes = "Cette methode permet de chercher un article par son CODE", response =
            ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    public ArticleDto findByCode(String code);
    @ApiOperation(value = "Supprimer un article", notes = "Cette methode permet de supprimer un article par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete supprime")
    })
    public void delete(Integer id);

}
