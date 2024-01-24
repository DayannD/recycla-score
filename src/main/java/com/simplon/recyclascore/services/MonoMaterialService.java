package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.DTO.MonoMaterialDTO;
import com.simplon.recyclascore.models.Enum.EnumMaterial;
import com.simplon.recyclascore.models.MonoMaterial;
import com.simplon.recyclascore.models.mappers.MonoMaterialMapper;
import com.simplon.recyclascore.repositories.IMonoMaterialRepository;
import com.simplon.recyclascore.services.IServices.IMonoMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MonoMaterialService implements IMonoMaterialService {

    private final IMonoMaterialRepository monoMaterialRepository;
    private final MonoMaterialMapper monoMaterialMapper;

    @Override
    public void addMonoMaterial(MonoMaterialDTO monoMaterialDTO) {
        monoMaterialRepository.save(this.monoMaterialMapper.toEntity(monoMaterialDTO));
    }

    @Override
    public void updateMonoMaterial(MonoMaterial monoMaterial) {
        monoMaterialRepository.save(monoMaterial);
    }

    @Override
    public void deleteMonoMaterial(MonoMaterial monoMaterial) {
        monoMaterialRepository.delete(monoMaterial);
    }

    @Override
    public MonoMaterial getMonoMaterialById(int id) {
        return monoMaterialRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<MonoMaterial> getAllMonoMaterials() {
        return monoMaterialRepository.findAll();
    }

    @Override
    public Iterable<MonoMaterial> getAllMonoMaterialsByMaterial(EnumMaterial material) {
        return monoMaterialRepository.findAllByMaterial(material);
    }
}
