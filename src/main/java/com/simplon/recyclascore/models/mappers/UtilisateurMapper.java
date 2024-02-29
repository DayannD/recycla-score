package com.simplon.recyclascore.models.mappers;

import com.simplon.recyclascore.models.dto.UtilisateurDTO;
import com.simplon.recyclascore.models.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UtilisateurMapper {

  @Mapping(target = "nomUtilisateur", source = "nomUtilisateur")
  Utilisateur toEntity(UtilisateurDTO utilisateurDTO);

  @Mapping(target = "nomUtilisateur", source = "nomUtilisateur")
  UtilisateurDTO toDTO(Utilisateur utilisateur);
}
