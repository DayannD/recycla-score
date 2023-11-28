package com.simplon.recyclascore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @Column(name = "ID_Produit", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "Nom_Produit")
    private String nomProduit;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "`Score_Recyclabilité`")
    private Float scoreRecyclabilité;

    @Column(name = "Poids")
    private Float poids;

    @Column(name = "Volume")
    private Float volume;

    @Size(max = 255)
    @Column(name = "URL_Image")
    private String urlImage;
}