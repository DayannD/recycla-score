package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IValidationRepository extends JpaRepository<Validation, Long> {
}
