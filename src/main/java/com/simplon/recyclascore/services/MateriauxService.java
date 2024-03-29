package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.dto.MateriauxDTO;
import com.simplon.recyclascore.models.Materiaux;
import com.simplon.recyclascore.models.mappers.MateriauxMapper;
import com.simplon.recyclascore.repositories.IMateriauxRepository;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MateriauxService implements IMateriauxService {

    private final IMateriauxRepository materiauxRepository;
    private final MateriauxMapper materiauxMapper;

    @Override
    public List<Materiaux> findAll() {
        return materiauxRepository.findAll();
    }

    @Override
    public Materiaux findById(int id) {
        return materiauxRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MateriauxDTO materiauxDTO) {
        this.materiauxRepository.save(this.materiauxMapper.toEntity(materiauxDTO));
    }

    @Override
    public void updateMateriau(MateriauxDTO materiauxDTO) {
        if (materiauxDTO.id() <= 0){
            throw new IllegalArgumentException("The id is not valid");
        }

        this.materiauxRepository.save(this.materiauxMapper.toEntity(materiauxDTO));
    }

    @Override
    public void deleteById(int id) {
        try {
            this.materiauxRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The id is not valid");
        }
    }
}
