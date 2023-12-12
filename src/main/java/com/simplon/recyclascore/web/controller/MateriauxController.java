package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.Materiaux;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return materiauxService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materiaux> getMateriauxById(@PathVariable Long id) {
        return materiauxService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Materiaux> createMateriaux(@Valid @RequestBody Materiaux materiaux) {
        return ResponseEntity.ok(materiauxService.save(materiaux));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriaux(@PathVariable Long id) {
        materiauxService.delete(id);
        return ResponseEntity.ok().build();
    }
}