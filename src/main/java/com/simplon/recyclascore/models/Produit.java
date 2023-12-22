package com.simplon.recyclascore.models;

import com.simplon.recyclascore.models.Enum.EnumTag;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Produits")
public class Produit {
    @Id
    @Column(name = "ID_Produit", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @Column(name = "Nom_Produit")
    private String nomProduit;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "`Score_Recyclabilite`")
    private Float scoreRecyclabilite;

    @Column(name = "Poids")
    private Float poids;

    @Column(name = "Volume")
    private Float volume;

    @Size(max = 255)
    @Column(name = "URL_Image")
    private String urlImage;

    @ElementCollection(targetClass = EnumTag.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "produit_tags")
    @Column(name = "tag")
    private List<EnumTag> tags;
}