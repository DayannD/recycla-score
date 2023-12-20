package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.ProduitMateriaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProduitMateriauxRepository extends JpaRepository<ProduitMateriaux, Integer> {

  List<ProduitMateriaux> findByIdProduit_Id(int materiauxId);
}
