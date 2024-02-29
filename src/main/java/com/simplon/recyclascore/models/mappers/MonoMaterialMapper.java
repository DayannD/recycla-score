package com.simplon.recyclascore.models.mappers;

import com.simplon.recyclascore.models.dto.MonoMaterialDTO;
import com.simplon.recyclascore.models.MonoMaterial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonoMaterialMapper {

  MonoMaterial toEntity(MonoMaterialDTO monoMaterialDTO);

  MonoMaterialDTO toDTO(MonoMaterial monoMaterial);
}
