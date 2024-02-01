package com.simplon.recyclascore.models.DTO;

import java.util.List;

public record InfosProduitDTO(
  String nomProduit,
  String description,
  Float scoreRecyclabilite,
  Float poids,
  Float volume,
  byte[] file,
  List<MateriauQuantiteDTO> materiaux
) {
}
