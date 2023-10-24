package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.EnumMatererial.EnumMaterial;
import com.simplon.recyclascore.models.MonoMaterial;

public interface IMonoMaterialService {
    void addMonoMaterial(MonoMaterial monoMaterial);

    void updateMonoMaterial(MonoMaterial monoMaterial);

    void deleteMonoMaterial(MonoMaterial monoMaterial);

    MonoMaterial getMonoMaterialById(Long id);

    Iterable<MonoMaterial> getAllMonoMaterials();

    Iterable<MonoMaterial> getAllMonoMaterialsByMarerial(EnumMaterial material);
}
