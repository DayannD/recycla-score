package com.simplon.recyclascore.services;

import com.simplon.recyclascore.models.Materiaux;
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

    @Override
    public List<Materiaux> findAll() {
        return materiauxRepository.findAll();
    }

    @Override
    public Optional<Materiaux> findById(Long id) {
        return materiauxRepository.findById(id);
    }

    @Override
    public Materiaux save(Materiaux materiaux) {
        return materiauxRepository.save(materiaux);
    }

    @Override
    public void delete(Long id) {
        materiauxRepository.deleteById(id);
    }
}
