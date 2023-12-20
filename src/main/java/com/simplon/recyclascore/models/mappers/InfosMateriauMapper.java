package com.simplon.recyclascore.models.mappers;


import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.models.DTO.MateriauQuantiteDTO;

import com.simplon.recyclascore.models.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MateriauxMapper.class})
public interface InfosMateriauMapper {

  @Mapping(target = "nomProduit", source = "produit.nomProduit")
  @Mapping(target = "description", source = "produit.description")
  @Mapping(target = "scoreRecyclabilite", source = "produit.scoreRecyclabilite")
  @Mapping(target = "poids", source = "produit.poids")
  @Mapping(target = "volume", source = "produit.volume")
  @Mapping(target = "urlImage", source = "produit.urlImage")
  @Mapping(target = "materiaux", source = "materiaux")
  InfosProduitDTO toInfosProduitDTO(Produit produit, List<MateriauQuantiteDTO> materiaux);
}

