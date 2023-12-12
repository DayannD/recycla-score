package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.Enum.EnumMaterial;
import com.simplon.recyclascore.models.MonoMaterial;
import com.simplon.recyclascore.services.IServices.IMonoMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monomaterial")
public class MonoMaterialController {

    private final IMonoMaterialService monoMaterialService;

    @PostMapping("")
    public String addMonoMaterial(@Valid @RequestBody MonoMaterial monoMaterial) {
        monoMaterialService.addMonoMaterial(monoMaterial);
        return "ok";
    }

    @GetMapping("/{material}")
    public Iterable<MonoMaterial> getAllMonoMaterialsByMarerial(@PathVariable String material) {
        return monoMaterialService.getAllMonoMaterialsByMarerial(EnumMaterial.valueOf(material));
    }

    @GetMapping("")
    public Iterable<MonoMaterial> getAllMonoMaterials() {
        return monoMaterialService.getAllMonoMaterials();
    }

    @GetMapping("/{id}")
    public MonoMaterial getMonoMaterialById(@PathVariable Long id) {
        return monoMaterialService.getMonoMaterialById(id);
    }

    @PutMapping("")
    public String updateMonoMaterial(@RequestBody MonoMaterial monoMaterial) {
        monoMaterialService.updateMonoMaterial(monoMaterial);
        return "ok";
    }

    @DeleteMapping("/{id}")
    public String deleteMonoMaterial(@PathVariable Long id) {
        MonoMaterial monoMaterial = monoMaterialService.getMonoMaterialById(id);
        monoMaterialService.deleteMonoMaterial(monoMaterial);
        return "ok";
    }
}
