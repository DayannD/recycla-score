package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProduitRepository extends JpaRepository<Produit, Integer> {
  Optional<Produit> findByNomProduit(String nomProduit);
}
