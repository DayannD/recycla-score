package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.models.Materiaux;

import java.util.List;
import java.util.Optional;

public interface IMateriauxService {
    List<Materiaux> findAll();
    Optional<Materiaux> findById(Long id);
    Materiaux save(Materiaux materiaux);
    void delete(Long id);
}
