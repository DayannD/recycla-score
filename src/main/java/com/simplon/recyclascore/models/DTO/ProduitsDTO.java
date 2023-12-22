package com.simplon.recyclascore.models.DTO;

import com.simplon.recyclascore.models.Enum.EnumTag;

import java.util.List;

public record ProduitsDTO(
String nomProduit,
String description,
Float scoreRecyclabilite,
Float poids,
Float volume,
String urlImage,
List<EnumTag> tags
) {
}
