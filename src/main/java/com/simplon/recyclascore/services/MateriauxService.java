package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.models.Materiaux;
import com.simplon.recyclascore.models.mappers.MateriauxMapper;
import com.simplon.recyclascore.repositories.IMateriauxRepository;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
