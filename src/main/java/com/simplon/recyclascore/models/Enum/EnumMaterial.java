package com.simplon.recyclascore.models.Enum;

import lombok.Getter;

@Getter
public enum EnumMaterial {
    ACIER_INOX("acier inoxydable"),
    POLY_EXPAN("polystyrène expansé"),
    BOIS("bois"),
    VERRE("verre"),
    PLASTIQUE("plastique"),
    METAL("métal"),
    PAPIER("papier"),
    TEXTILE("textile"),
    ALUMINIUM("aluminium"),
    CARTON("carton");;

    private final String description;

    EnumMaterial(String description) {
        this.description = description;
    }

}
