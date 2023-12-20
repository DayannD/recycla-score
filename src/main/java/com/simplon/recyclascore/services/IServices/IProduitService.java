package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;

import java.util.Optional;

public interface IProduitService {
  Optional<InfosProduitDTO> findByName(String name);

}
