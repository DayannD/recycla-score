package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.Materiaux;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/materiaux")
public class MateriauxController {

    private final IMateriauxService materiauxService;

    @GetMapping
    public List<Materiaux> getAllMateriaux() {
        return this.materiauxService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materiaux> getMateriauxById(@PathVariable int id) {
        return this.materiauxService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriaux(@PathVariable int id) {
        this.materiauxService.delete(id);
        return ResponseEntity.ok().build();
    }
}