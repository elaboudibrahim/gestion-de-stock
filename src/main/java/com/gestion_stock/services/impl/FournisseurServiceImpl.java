package com.gestion_stock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.gestion_stock.dto.FournisseurDto;
import com.gestion_stock.exception.EntityNotFoundException;
import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.repository.CommandeFournisseurRepository;
import com.gestion_stock.repository.FournisseurRepository;
import com.gestion_stock.services.FournisseurService;
import com.gestion_stock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
  @Autowired
  private FournisseurRepository fournisseurRepository;
  private CommandeFournisseurRepository commandeFournisseurRepository;


  @Override
  public FournisseurDto save(FournisseurDto dto) {
    List<String> errors = FournisseurValidator.validate(dto);
    if (!errors.isEmpty()) {
      throw new InvalidEntityException("Le fournisseur n'est pas valide",errors);
    }

    return FournisseurDto.fromEntity(
        fournisseurRepository.save(
            FournisseurDto.toEntity(dto)
        )
    );
  }

  @Override
  public FournisseurDto findById(Integer id) {
    if (id == null) {
      return null;
    }
    return fournisseurRepository.findById(id)
        .map(FournisseurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD" ));
  }

  @Override
  public List<FournisseurDto> findAll() {
    return fournisseurRepository.findAll().stream()
        .map(FournisseurDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      return;
    }
//    List<CommandeClient> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
//    if (!commandeFournisseur.isEmpty()) {
//      throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
//          ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
//    }
    fournisseurRepository.deleteById(id);
  }
}
