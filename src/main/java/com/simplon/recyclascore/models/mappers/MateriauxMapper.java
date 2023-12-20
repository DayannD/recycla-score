package com.simplon.recyclascore.models.mappers;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.models.Materiaux;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MateriauxMapper {

  Materiaux toEntity(MateriauxDTO materiauxDTO);

  MateriauxDTO toDTO(Materiaux materiaux);
}
