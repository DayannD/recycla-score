package com.simplon.recyclascore.models.mappers;

import com.simplon.recyclascore.models.DTO.ProduitsDTO;
import com.simplon.recyclascore.models.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

  List<ProduitsDTO> toDTO(List<Produit> produit);

  Produit toEntity(ProduitsDTO produitsDTO);

  @Mapping(target = "urlImage", source = "url")
  Produit toEntity(ProduitsDTO produitsDTO, String url);
}
