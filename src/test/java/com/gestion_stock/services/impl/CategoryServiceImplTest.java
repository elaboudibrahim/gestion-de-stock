package com.gestion_stock.services.impl;

import com.gestion_stock.dto.CategoryDto;
import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    CategoryService categoryService;
    @Test
    public void saveCategoryWithSuccess(){
        CategoryDto cateforyExcpected=CategoryDto.builder()
                .designation("me")
                .code("12")
                .idEntreprise(13)
                .build();
        CategoryDto categorySaved = categoryService.save(cateforyExcpected);
        assertNotNull(categorySaved.getId());
        assertEquals(cateforyExcpected.getCode(),categorySaved.getCode());

    }
    @Test
    public void shouldThrowInvalidEntityException(){
        CategoryDto expectedCategory=CategoryDto.builder().build();
        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class,()-> categoryService.save(expectedCategory));
        assertEquals(expectedException.getHttpCode(), HttpStatus.BAD_REQUEST.value());
        assertEquals("Veuillez renseigner le code de la categorie",expectedException.getErrors().get(0));
        assertEquals("La category n'est pas valide",expectedException.getMessage());

    }
}