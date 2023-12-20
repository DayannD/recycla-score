package com.simplon.recyclascore.models.DTO;

public record ProduitsDTO(
String nomProduit,
String description,
Float scoreRecyclabilite,
Float poids,
Float volume,
String urlImage
) {
}
