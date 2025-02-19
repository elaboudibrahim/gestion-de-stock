package com.gestion_stock.dto;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.gestion_stock.model.CommandeClient;
import com.gestion_stock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeClientDto {

  private Integer id;

  private String code;

  private Instant dateCommande;

  private EtatCommande etatCommande;

  private ClientDto client;

  private Integer idEntreprise;

  private List<LigneCommandeClientDto> ligneCommandeClients;

  public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
    if (commandeClient == null) {
      return null;
    }
    return CommandeClientDto.builder()
        .id(commandeClient.getId())
        .code(commandeClient.getCode())
        .dateCommande(commandeClient.getDateCommande())
        .etatCommande(commandeClient.getEtatCommande())
        .client(ClientDto.fromEntity(commandeClient.getClient()))
        .idEntreprise(commandeClient.getIdEntreprise())
        .build();

  }

  public static CommandeClient toEntity(CommandeClientDto dto) {
    if (dto == null) {
      return null;
    }
    CommandeClient commandeClient = new CommandeClient();
    commandeClient.setId(dto.getId());
    commandeClient.setCode(dto.getCode());
    commandeClient.setClient(ClientDto.toEntity(dto.getClient()));
    commandeClient.setDateCommande(dto.getDateCommande());
    commandeClient.setEtatCommande(dto.getEtatCommande());
    commandeClient.setIdEntreprise(dto.getIdEntreprise());
    return commandeClient;
  }

  public boolean isCommandeLivree() {
    return EtatCommande.LIVREE.equals(this.etatCommande);
  }
}
