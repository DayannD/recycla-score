package com.simplon.recyclascore.models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record MailDTO(
@NotNull @Email String from,
@NotNull String name,
@NotNull String email,
@NotNull String object,
@NotNull String message
) {
}
