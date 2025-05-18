package com.example.PI_student_finance.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String senha
) {}
