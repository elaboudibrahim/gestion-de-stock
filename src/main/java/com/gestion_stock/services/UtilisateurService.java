package com.gestion_stock.services;

import com.gestion_stock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    List<UtilisateurDto> findAll();

    void delete(Integer id);

    UtilisateurDto findByEmail(String email);

   // UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);

}
