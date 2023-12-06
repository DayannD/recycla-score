package com.simplon.recyclascore.models;

import com.simplon.recyclascore.models.Enum.EnumMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Monomaterial", schema = "recycla-score", catalog = "")
public class MonoMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EnumMaterial material;

    private String recyclability;
}


