package com.gestion_stock.dto.auth;

import com.gestion_stock.dto.UtilisateurDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder
@AllArgsConstructor@NoArgsConstructor
public class AuthenticationRequestDto {
    String login;
    String password;
}

