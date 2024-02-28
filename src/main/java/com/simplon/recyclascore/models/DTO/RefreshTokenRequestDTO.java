package com.simplon.recyclascore.models.DTO;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequestDTO(
  @NotNull String token
) {
}
