package com.simplon.recyclascore.models.mappers;

import com.simplon.recyclascore.models.dto.ProduitOutDTO;
import com.simplon.recyclascore.models.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitOutMapper {

  @Mapping(target = "file", source = "file")
  ProduitOutDTO toDTO(Produit produit, byte[] file);
}
