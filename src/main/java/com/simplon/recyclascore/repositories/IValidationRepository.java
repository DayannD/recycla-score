package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IValidationRepository extends JpaRepository<Validation, Long> {
  Optional<Validation> findByCode(String code);
}
