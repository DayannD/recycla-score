package com.simplon.recyclascore.models.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MateriauxDTO(
  @NotNull @NotBlank @Size(max = 255) String nomMateriau,
  @NotNull @NotBlank @Size(max = 100) String typeRecyclage,
  @NotNull @Min(value = 0) Float coutRecyclage,
  @NotNull @Min(value = 0) Float energieRecyclage
) {
}
