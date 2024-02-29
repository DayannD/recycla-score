package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.dto.MateriauxDTO;
import com.simplon.recyclascore.models.Materiaux;

import java.util.List;

public interface IMateriauxService {
    List<Materiaux> findAll();
    Materiaux findById(int id);
    void save(MateriauxDTO materiauxDTO);

    void updateMateriau(MateriauxDTO materiauxDTO);

    void deleteById(int id);
}
