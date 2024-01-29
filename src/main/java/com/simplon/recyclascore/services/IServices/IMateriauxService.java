package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.models.Materiaux;

import java.util.List;
import java.util.Optional;

public interface IMateriauxService {
    List<Materiaux> findAll();
    Materiaux findById(int id);
    void save(MateriauxDTO materiauxDTO);
}
