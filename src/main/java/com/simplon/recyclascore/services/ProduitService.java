package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.models.DTO.MateriauQuantiteDTO;
import com.simplon.recyclascore.models.DTO.ProduitOutDTO;
import com.simplon.recyclascore.models.DTO.ProduitsDTO;
import com.simplon.recyclascore.models.Enum.EnumTag;
import com.simplon.recyclascore.models.Produit;
import com.simplon.recyclascore.models.ProduitMateriaux;
import com.simplon.recyclascore.models.mappers.InfosMateriauMapper;
import com.simplon.recyclascore.models.mappers.MateriauxMapper;
import com.simplon.recyclascore.models.mappers.ProduitMapper;
import com.simplon.recyclascore.models.mappers.ProduitOutMapper;
import com.simplon.recyclascore.repositories.IMateriauxRepository;
import com.simplon.recyclascore.repositories.IProduitMateriauxRepository;
import com.simplon.recyclascore.repositories.IProduitRepository;
import com.simplon.recyclascore.services.IServices.IProduitService;
import com.simplon.recyclascore.services.aws.AwsService;
import com.simplon.recyclascore.utils.Utils;
import io.jsonwebtoken.lang.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProduitService implements IProduitService {
  private final IProduitRepository produitRepository;
  private final IProduitMateriauxRepository produitMateriauxRepository;
  private final IMateriauxRepository materiauxRepository;
  private final InfosMateriauMapper infosMateriauMapper;
  private final MateriauxMapper materiauxMapper;
  private final ProduitMapper produitMapper;
  private final AwsService awsService;
  private final ProduitOutMapper produitOutMapper;

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
      this.infosMateriauMapper.toInfosProduitDTO(produit.get(), materiauEtQuantites, this.awsService.downloadFile(produit.get().getUrlImage()))
    );
  }

  public List<ProduitOutDTO> getAllProduits() {
    List<Produit> produits = this.produitRepository.findAll();
    List<ProduitOutDTO> produitsOutDTO = new ArrayList<>();
    for (Produit produit : produits) {
      produitsOutDTO.add(this.produitOutMapper.toDTO(produit, this.awsService.downloadFile(produit.getUrlImage())));
    }
    return produitsOutDTO;
  }


  @Override
  public List<ProduitOutDTO> getAllProduits(EnumTag tag) {
    List<Produit> produits = this.produitRepository.findAllByTag(tag);
    List<ProduitOutDTO> produitsOutDTO = new ArrayList<>();
    for (Produit produit : produits) {
      produitsOutDTO.add(this.produitOutMapper.toDTO(produit, this.awsService.downloadFile(produit.getUrlImage())));
    }
    return produitsOutDTO;
  }

  @Override
  public void setTagProduits() {
    List<Produit> produits = this.produitRepository.findAll();
    for (Produit produit : produits) {
      List<EnumTag> tags = new ArrayList<>();
      tags.add(EnumTag.ECOLOGY);
      tags.add(this.getTag());
      produit.setTags(tags);
      this.produitRepository.save(produit);
    }
  }

  private EnumTag getTag() {
    EnumTag tag = EnumTag.values()[(int) (Math.random() * EnumTag.values().length)];
    if (tag == EnumTag.ECOLOGY) {
      return this.getTag();
    }
    return tag;
  }

  @Override
  public void save(ProduitsDTO produitsDTO) throws IOException {
    File file;
    String fileName;
    file = Utils.convertMultiPartToFile(produitsDTO.file());
    fileName = Utils.getUniqueName(Objects.requireNonNull(produitsDTO.file().getOriginalFilename()));

    try {
      this.awsService.uploadFile(file, fileName);
    } catch (Exception e) {
      e.printStackTrace();
    }


    this.produitRepository.save(this.produitMapper.toEntity(produitsDTO, fileName));
  }

  @Override
  public String[] getAlltags() {
    String[] tags = new String[EnumTag.values().length];
    for (int i = 0; i < EnumTag.values().length; i++) {
      tags[i] = EnumTag.values()[i].toString();
    }

    return tags;
  }

  @Override
  public Optional<InfosProduitDTO> findById(int id) {
    Optional<Produit> produit = this.produitRepository.findById(id);
    if (produit.isEmpty()) {
      return Optional.empty();
    }
    List<ProduitMateriaux> listeProduitMateriaux = this.produitMateriauxRepository.findByIdProduit_Id(produit.get().getId());
    List<MateriauQuantiteDTO> materiauEtQuantites = listeProduitMateriaux.stream()
      .map(pm -> new MateriauQuantiteDTO(
        this.materiauxMapper.toDTO(this.materiauxRepository.findById(pm.getIdMateriau().getId()).get()),
        pm.getQuantite()
      )).toList();
    return Optional.of(
      this.infosMateriauMapper.toInfosProduitDTO(produit.get(), materiauEtQuantites, this.awsService.downloadFile(produit.get().getUrlImage()))
    );
  }
}
