package com.simplon.recyclascore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`Materiaux`")
public class Materiaux {
    @Id
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