package com.gestion_stock.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.gestion_stock.dto.ClientDto;
import com.gestion_stock.exception.EntityNotFoundException;
import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.repository.ClientRepository;
import com.gestion_stock.repository.CommandeClientRepository;
import com.gestion_stock.services.ClientService;
import com.gestion_stock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private CommandeClientRepository commandeClientRepository;



  @Override
  public ClientDto save(ClientDto dto) {
    List<String> errors = ClientValidator.validate(dto);
    if (!errors.isEmpty()) {
      throw new InvalidEntityException("Le client n'est pas valide", errors);
    }

    return ClientDto.fromEntity(
        clientRepository.save(
            ClientDto.toEntity(dto)
        )
    );
  }

  @Override
  public ClientDto findById(Integer id) {
    if (id == null) {
      log.error("Client ID is null");
      return null;
    }
    return clientRepository.findById(id)
        .map(ClientDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD")
        );
  }

  @Override
  public List<ClientDto> findAll() {
    return clientRepository.findAll().stream()
        .map(ClientDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      return;
    }
//    List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
//    if (!commandeClients.isEmpty()) {
//      throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
//          ErrorCodes.CLIENT_ALREADY_IN_USE);
//    }
    clientRepository.deleteById(id);
  }
}
