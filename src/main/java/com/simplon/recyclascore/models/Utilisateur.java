package com.simplon.recyclascore.models;

import com.simplon.recyclascore.models.Enum.EnumMaterial;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Utilisateurs")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private String role;
}