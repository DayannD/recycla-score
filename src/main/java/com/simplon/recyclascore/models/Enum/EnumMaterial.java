package com.simplon.recyclascore.models.Enum;

import lombok.Getter;

@Getter
public enum EnumMaterial {
    ACIER_INOX("acier inoxydable"),
    POLY_EXPAN("polystyrène expansé");

    private final String description;

    EnumMaterial(String description) {
        this.description = description;
    }

}
