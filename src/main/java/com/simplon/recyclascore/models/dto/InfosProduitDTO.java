package com.simplon.recyclascore.models.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record InfosProduitDTO(
  String nomProduit,
  String description,
  Float scoreRecyclabilite,
  Float poids,
  Float volume,
  byte[] file,
  List<MateriauQuantiteDTO> materiaux
) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InfosProduitDTO that = (InfosProduitDTO) o;
    return Objects.equals(nomProduit, that.nomProduit) &&
      Objects.equals(description, that.description) &&
      Objects.equals(scoreRecyclabilite, that.scoreRecyclabilite) &&
      Objects.equals(poids, that.poids) &&
      Objects.equals(volume, that.volume) &&
      Arrays.equals(file, that.file) &&
      Objects.equals(materiaux, that.materiaux);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(nomProduit, description, scoreRecyclabilite, poids, volume, materiaux);
    result = 31 * result + Arrays.hashCode(file);
    return result;
  }

  @Override
  public String toString() {
    return "InfosProduitDTO{" +
      "nomProduit='" + nomProduit + '\'' +
      ", description='" + description + '\'' +
      ", scoreRecyclabilite=" + scoreRecyclabilite +
      ", poids=" + poids +
      ", volume=" + volume +
      ", file=" + Arrays.toString(file) +
      ", materiaux=" + materiaux +
      '}';
  }
}
