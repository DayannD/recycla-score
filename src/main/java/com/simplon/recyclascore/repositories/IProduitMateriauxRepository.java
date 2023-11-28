package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.ProduitMateriaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduitMateriauxRepository extends JpaRepository<ProduitMateriaux, Long> {
}
