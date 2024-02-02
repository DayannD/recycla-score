package com.simplon.recyclascore.models.DTO;

import com.simplon.recyclascore.models.Enum.EnumTag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProduitOutDTO(
  @NotNull @NotBlank String nomProduit,
  @NotNull @NotBlank String description,
  @Min(0) Float scoreRecyclabilite,
  @Min(1) Float poids,
  @Min(1) Float volume,
  @NotNull byte[] file,
  @NotNull List<EnumTag> tags
) {
}
