package com.simplon.recyclascore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Materiaux`")
public class Materiaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_Materiau`", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "`Nom_Materiau`")
    private String nomMateriau;

    @Size(max = 100)
    @Column(name = "Type_Recyclage", length = 100)
    private String typeRecyclage;

    @Column(name = "`Cout_Recyclage`")
    private Float coutRecyclage;

    @Column(name = "Energie_Recyclage")
    private Float energieRecyclage;
}