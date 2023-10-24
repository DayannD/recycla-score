package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.MonoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMonoMaterial extends JpaRepository<MonoMaterial, Long> {
}
