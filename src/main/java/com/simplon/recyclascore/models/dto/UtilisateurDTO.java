package com.simplon.recyclascore.models.dto;

import com.simplon.recyclascore.models.Role;

public record UtilisateurDTO(
String nomUtilisateur,
String motDePasse,
String email,
Role role,
String actif
) {
}
