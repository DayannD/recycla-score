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
@Table(name = "`matériaux`")
public class Materiaux {
    @Id
    @Column(name = "`ID_Matériau`", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "`Nom_Matériau`")
    private String nomMatériau;

    @Size(max = 100)
    @Column(name = "Type_Recyclage", length = 100)
    private String typeRecyclage;

    @Column(name = "`Coût_Recyclage`")
    private Float coûtRecyclage;

    @Column(name = "Energie_Recyclage")
    private Float energieRecyclage;

}