package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.models.DTO.ProduitOutDTO;
import com.simplon.recyclascore.models.DTO.ProduitsDTO;
import com.simplon.recyclascore.models.Enum.EnumTag;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProduitService {
  Optional<InfosProduitDTO> findByName(String name);

  List<ProduitOutDTO> getAllProduits(EnumTag tag);

  List<ProduitOutDTO> getAllProduits();

  void setTagProduits();

  void save(ProduitsDTO produitsDTO) throws IOException;

  Optional<InfosProduitDTO> findById(int id);

  String[] getAlltags();
}
