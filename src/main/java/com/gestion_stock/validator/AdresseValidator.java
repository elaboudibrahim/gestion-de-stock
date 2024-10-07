package com.gestion_stock.validator;

import com.gestion_stock.dto.AdresseDto;
import com.gestion_stock.model.Adresse;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdresseValidator {
   public static List<String> validate(AdresseDto adresse){
       List<String> errors=new ArrayList<>();

       if(adresse==null){
           errors.add("Veuillez renseigner l'adresse 1'");
           errors.add("Veuillez renseigner la ville'");
           errors.add("Veuillez renseigner le pays'");
           errors.add("Veuillez renseigner le code postal'");
       }
       if(adresse.getAdresse1().isEmpty()){
           errors.add("Veuillez renseigner l'adresse 1'");
       }
       if(!StringUtils.hasLength(adresse.getAdresse2())){
           errors.add("Veuillez renseigner l'adresse 2");
       }
       if(!StringUtils.hasLength(adresse.getPays())){
           errors.add("Veuillez renseigner le pays");
       }
       if(!StringUtils.hasLength(adresse.getCodePostale())){
           errors.add("Veuillez renseigner le code postal'");
       }
       return errors;
   }
}
