package com.simplon.recyclascore.models.dto;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequestDTO(
  @NotNull String token
) {
}
