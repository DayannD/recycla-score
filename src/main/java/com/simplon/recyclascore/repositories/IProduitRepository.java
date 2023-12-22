package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Enum.EnumTag;
import com.simplon.recyclascore.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProduitRepository extends JpaRepository<Produit, Integer> {
  Optional<Produit> findByNomProduit(String nomProduit);

  @Query("SELECT p FROM Produit p JOIN p.tags t WHERE t = :tag")
  List<Produit> findAllByTag(EnumTag tag);
}
