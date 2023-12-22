package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.models.DTO.ProduitsDTO;
import com.simplon.recyclascore.models.Enum.EnumTag;

import java.util.List;
import java.util.Optional;

public interface IProduitService {
  Optional<InfosProduitDTO> findByName(String name);

  List<ProduitsDTO> getALlProduits(EnumTag tag);

  void setTagProduits();
}
