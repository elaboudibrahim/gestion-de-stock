package com.gestion_stock.services.impl;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gestion_stock.dto.UtilisateurDto;
import com.gestion_stock.exception.EntityNotFoundException;
import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.model.Utilisateur;
import com.gestion_stock.repository.UtilisateurRepository;
import com.gestion_stock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

  @Autowired

  private UtilisateurRepository utilisateurRepository;
  //private PasswordEncoder passwordEncoder;



  @Override
  public UtilisateurDto save(UtilisateurDto dto) {
//    List<String> errors = UtilisateurValidator.validate(dto);
//    if (!errors.isEmpty()) {
//      throw new InvalidEntityException("L'utilisateur n'est pas valide", errors);
//    }

    if(userAlreadyExists(dto.getEmail())) {
      throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja",
          Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
    }


    //dto.setMoteDePasse(passwordEncoder.encode(dto.getMoteDePasse()));

    return UtilisateurDto.fromEntity(
        utilisateurRepository.save(
            UtilisateurDto.toEntity(dto)
        )
    );
  }

  private boolean userAlreadyExists(String email) {
    Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
    return user.isPresent();
  }

  @Override
  public UtilisateurDto findById(Integer id) {
    if (id == null) {
      return null;
    }
    return utilisateurRepository.findById(id)
        .map(UtilisateurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD"));
  }

  @Override
  public List<UtilisateurDto> findAll() {
    return utilisateurRepository.findAll().stream()
        .map(UtilisateurDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      return;
    }
    utilisateurRepository.deleteById(id);
  }

  @Override
  public UtilisateurDto findByEmail(String email) {
    return utilisateurRepository.findUtilisateurByEmail(email)
        .map(UtilisateurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
        "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD"));
  }

}
