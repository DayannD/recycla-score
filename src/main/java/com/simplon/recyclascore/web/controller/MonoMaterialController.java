package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.MonoMaterialDTO;
import com.simplon.recyclascore.models.Enum.EnumMaterial;
import com.simplon.recyclascore.models.MonoMaterial;
import com.simplon.recyclascore.services.IServices.IMonoMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monomaterial")
public class MonoMaterialController {

    private final IMonoMaterialService monoMaterialService;



    /**
     * @param material
     * @return Iterable<MonoMaterial>
     * @GetMapping("/{material}") : récupère tous les monomaterials en fonction du material
     */
    @GetMapping("/material/{material}")
    public Iterable<MonoMaterial> getAllMonoMaterialsByMarerial(@PathVariable String material) {
        return this.monoMaterialService.getAllMonoMaterialsByMaterial(EnumMaterial.valueOf(material));
    }

    /**
     * @return Iterable<MonoMaterial>
     * @GetMapping("") : récupère tous les monomaterials
     */
    @GetMapping("")
    public Iterable<MonoMaterial> getAllMonoMaterials() {
        return monoMaterialService.getAllMonoMaterials();
    }

    /**
     * @param id
     * @return MonoMaterial
     * @GetMapping("/{id}") : récupère un monomaterial en fonction de son id
     */
    @GetMapping("/{id}")
    public MonoMaterial getMonoMaterialById(@PathVariable int id) {
        return this.monoMaterialService.getMonoMaterialById(id);
    }

    /**
     * @param monoMaterial
     * @return String
     * @PutMapping("") : modifie un monomaterial
     */
    @PutMapping("")
    public String updateMonoMaterial(@RequestBody MonoMaterial monoMaterial) {
        this.monoMaterialService.updateMonoMaterial(monoMaterial);
        return "ok";
    }

    /**
     * @param id
     * @return String
     * @DeleteMapping("/{id}") : supprime un monomaterial en fonction de son id
     */
    @DeleteMapping("/{id}")
    public String deleteMonoMaterial(@PathVariable int id) {
        MonoMaterial monoMaterial = this.monoMaterialService.getMonoMaterialById(id);
        this.monoMaterialService.deleteMonoMaterial(monoMaterial);
        return "ok";
    }
}
