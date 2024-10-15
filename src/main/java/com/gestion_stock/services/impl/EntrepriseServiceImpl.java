package com.gestion_stock.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.gestion_stock.dto.EntrepriseDto;
import com.gestion_stock.dto.UtilisateurDto;
import com.gestion_stock.exception.EntityNotFoundException;
import com.gestion_stock.repository.EntrepriseRepository;
import com.gestion_stock.services.EntrepriseService;
import com.gestion_stock.services.UtilisateurService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional(rollbackOn = Exception.class)
@Service
public class EntrepriseServiceImpl implements EntrepriseService {
  @Autowired
  private EntrepriseRepository entrepriseRepository;
  @Autowired
  private UtilisateurService utilisateurService;
  //private RolesRepository rolesRepository;


  @Override
  public EntrepriseDto save(EntrepriseDto dto) {
//    List<String> errors = EntrepriseValidator.validate(dto);
//    if (!errors.isEmpty()) {
//      throw new InvalidEntityException("L'entreprise n'est pas valide", errors);
//    }
    EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
        entrepriseRepository.save(EntrepriseDto.toEntity(dto))
    );

    UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

    UtilisateurDto savedUser = utilisateurService.save(utilisateur);

//    RolesDto rolesDto = RolesDto.builder()
//        .roleName("ADMIN")
//        .utilisateur(savedUser)
//        .build();
//
//    rolesRepository.save(RolesDto.toEntity(rolesDto));

    return  savedEntreprise;
  }

  private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
    return UtilisateurDto.builder()
        .adresse(dto.getAdresse())
        .nom(dto.getNom())
        .prenom(dto.getCodeFiscal())
        .email(dto.getEmail())
        .moteDePasse(generateRandomPassword())
        .entreprise(dto)
        .dateDeNaissance(Instant.now())
        .photo(dto.getPhoto())
        .build();
  }

  private String generateRandomPassword() {
    return "som3R@nd0mP@$$word";
  }

  @Override
  public EntrepriseDto findById(Integer id) {
    if (id == null) {
      return null;
    }
    return entrepriseRepository.findById(id)
        .map(EntrepriseDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD")
        );
  }

  @Override
  public List<EntrepriseDto> findAll() {
    return entrepriseRepository.findAll().stream()
        .map(EntrepriseDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      return;
    }
    entrepriseRepository.deleteById(id);
  }
}
