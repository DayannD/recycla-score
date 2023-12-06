package com.simplon.recyclascore.models.Enum;

public enum EnumMaterial {
    ACIER_INOX("acier inoxydable"),
    POLY_EXPAN("polystyrène expansé");

    private final String description;

    EnumMaterial(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
