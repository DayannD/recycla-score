package com.simplon.recyclascore.models.dto;

import com.simplon.recyclascore.models.Enum.EnumTag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record ProduitOutDTO(
  int id,
  @NotNull @NotBlank String nomProduit,
  @NotNull @NotBlank String description,
  @Min(0) Float scoreRecyclabilite,
  @Min(1) Float poids,
  @Min(1) Float volume,
  @NotNull byte[] file,
  @NotNull List<EnumTag> tags
) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProduitOutDTO that = (ProduitOutDTO) o;
    return id == that.id &&
      Objects.equals(nomProduit, that.nomProduit) &&
      Objects.equals(description, that.description) &&
      Objects.equals(scoreRecyclabilite, that.scoreRecyclabilite) &&
      Objects.equals(poids, that.poids) &&
      Objects.equals(volume, that.volume) &&
      Arrays.equals(file, that.file) &&
      Objects.equals(tags, that.tags);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, nomProduit, description, scoreRecyclabilite, poids, volume, tags);
    result = 31 * result + Arrays.hashCode(file);
    return result;
  }

  @Override
  public String toString() {
    return "ProduitOutDTO{" +
      "id=" + id +
      ", nomProduit='" + nomProduit + '\'' +
      ", description='" + description + '\'' +
      ", scoreRecyclabilite=" + scoreRecyclabilite +
      ", poids=" + poids +
      ", volume=" + volume +
      ", file=" + Arrays.toString(file) +
      ", tags=" + tags +
      '}';
  }
}
