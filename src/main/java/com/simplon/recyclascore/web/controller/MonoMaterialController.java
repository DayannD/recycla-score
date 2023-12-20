package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.MonoMaterialDTO;
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
    public String addMonoMaterial(@Valid @RequestBody MonoMaterialDTO monoMaterialDTO) {
        this.monoMaterialService.addMonoMaterial(monoMaterialDTO);
        return "ok";
    }

    @GetMapping("/{material}")
    public Iterable<MonoMaterial> getAllMonoMaterialsByMarerial(@PathVariable String material) {
        return this.monoMaterialService.getAllMonoMaterialsByMarerial(EnumMaterial.valueOf(material));
    }

    @GetMapping("")
    public Iterable<MonoMaterial> getAllMonoMaterials() {
        return monoMaterialService.getAllMonoMaterials();
    }

    @GetMapping("/{id}")
    public MonoMaterial getMonoMaterialById(@PathVariable int id) {
        return this.monoMaterialService.getMonoMaterialById(id);
    }

    @PutMapping("")
    public String updateMonoMaterial(@RequestBody MonoMaterial monoMaterial) {
        this.monoMaterialService.updateMonoMaterial(monoMaterial);
        return "ok";
    }

    @DeleteMapping("/{id}")
    public String deleteMonoMaterial(@PathVariable int id) {
        MonoMaterial monoMaterial = this.monoMaterialService.getMonoMaterialById(id);
        this.monoMaterialService.deleteMonoMaterial(monoMaterial);
        return "ok";
    }
}
