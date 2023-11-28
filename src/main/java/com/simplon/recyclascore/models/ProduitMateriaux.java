package com.simplon.recyclascore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`produit_matériaux`")
public class ProduitMateriaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Produit")
    private Produit idProduit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`ID_Matériau`")
    private Materiaux idMatériau;

    @Column(name = "`Quantité`")
    private Float quantité;
}