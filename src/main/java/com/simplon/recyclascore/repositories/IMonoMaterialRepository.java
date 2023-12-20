package com.simplon.recyclascore.repositories;

import com.simplon.recyclascore.models.Enum.EnumMaterial;
import com.simplon.recyclascore.models.MonoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMonoMaterialRepository extends JpaRepository<MonoMaterial, Integer> {
    Iterable<MonoMaterial> findAllByMaterial(EnumMaterial material);
}
