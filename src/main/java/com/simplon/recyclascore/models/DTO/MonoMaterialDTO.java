package com.simplon.recyclascore.models.DTO;

import com.simplon.recyclascore.models.Enum.EnumMaterial;

public record MonoMaterialDTO(
  String name,
  EnumMaterial material,
  String recyclability
) {
}
