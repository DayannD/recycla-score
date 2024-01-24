package com.simplon.recyclascore.models.DTO;

import com.simplon.recyclascore.models.Enum.EnumMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MonoMaterialDTO(
  @NotNull @NotBlank String name,
  @NotNull EnumMaterial material,
  @NotNull @NotBlank String recyclability
) {
}
