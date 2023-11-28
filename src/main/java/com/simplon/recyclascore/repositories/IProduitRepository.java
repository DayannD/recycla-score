package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduitRepository extends JpaRepository<Produit, Long> {
}
