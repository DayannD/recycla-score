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
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @Column(name = "ID_Utilisateur", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "Nom_Utilisateur")
    private String nomUtilisateur;

    @Size(max = 255)
    @Column(name = "Mot_de_Passe")
    private String motDePasse;

    @Size(max = 50)
    @Column(name = "`Role`", length = 50)
    private String role;

}