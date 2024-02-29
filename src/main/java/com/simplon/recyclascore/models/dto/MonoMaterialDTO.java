package com.simplon.recyclascore.models.dto;

import com.simplon.recyclascore.models.Enum.EnumMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MonoMaterialDTO(
  Long id,
  @NotNull @NotBlank String name,
  @NotNull EnumMaterial material,
  @NotNull @NotBlank String recyclability
) {
}
