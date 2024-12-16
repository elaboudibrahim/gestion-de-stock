package com.gestion_stock.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AuthenticationResponseDto {
    String accessToken;
}
