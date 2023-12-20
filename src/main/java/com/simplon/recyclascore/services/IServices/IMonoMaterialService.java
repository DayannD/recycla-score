package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.DTO.MonoMaterialDTO;
import com.simplon.recyclascore.models.Enum.EnumMaterial;
import com.simplon.recyclascore.models.MonoMaterial;

public interface IMonoMaterialService {
    void addMonoMaterial(MonoMaterialDTO monoMaterialDTO);

    void updateMonoMaterial(MonoMaterial monoMaterial);

    void deleteMonoMaterial(MonoMaterial monoMaterial);

    MonoMaterial getMonoMaterialById(int id);

    Iterable<MonoMaterial> getAllMonoMaterials();

    Iterable<MonoMaterial> getAllMonoMaterialsByMarerial(EnumMaterial material);
}
