package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.models.DTO.MateriauQuantiteDTO;
import com.simplon.recyclascore.models.Produit;
import com.simplon.recyclascore.models.ProduitMateriaux;
import com.simplon.recyclascore.models.mappers.InfosMateriauMapper;
import com.simplon.recyclascore.models.mappers.MateriauxMapper;
import com.simplon.recyclascore.repositories.IMateriauxRepository;
import com.simplon.recyclascore.repositories.IProduitMateriauxRepository;
import com.simplon.recyclascore.repositories.IProduitRepository;
import com.simplon.recyclascore.services.IServices.IProduitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProduitService implements IProduitService {
  private final IProduitRepository produitRepository;
  private final IProduitMateriauxRepository produitMateriauxRepository;
  private final IMateriauxRepository materiauxRepository;
  private final InfosMateriauMapper infosMateriauMapper;
  private final MateriauxMapper materiauxMapper;

  @Override
  public Optional<InfosProduitDTO> findByName(String nomProduit) {
    Optional<Produit> produit = this.produitRepository.findByNomProduit(nomProduit);

    List<ProduitMateriaux> listeProduitMateriaux = this.produitMateriauxRepository.findByIdProduit_Id(produit.get().getId());

    List<MateriauQuantiteDTO> materiauEtQuantites = listeProduitMateriaux.stream()
      .map(pm -> new MateriauQuantiteDTO(
        this.materiauxMapper.toDTO(this.materiauxRepository.findById(pm.getIdMateriau().getId()).get()),
        pm.getQuantite()
      )).toList();

    return Optional.of(
      this.infosMateriauMapper.toInfosProduitDTO(produit.get(), materiauEtQuantites)
    );
  }
}
