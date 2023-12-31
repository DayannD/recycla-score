package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Materiaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMateriauxRepository extends JpaRepository<Materiaux, Integer> {
}
