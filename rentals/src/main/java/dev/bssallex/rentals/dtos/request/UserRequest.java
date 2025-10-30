package dev.bssallex.rentals.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Nome não pode ser vazio")
        String name,
        @Email(message = "Email não pode ser vazio")
        String email) {
}
