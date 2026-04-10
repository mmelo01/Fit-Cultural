package com.fitcultural.backend.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

//Request quer dizer 'Requisição', ou seja, esse código é o que solicita ao front-end os dados que necessitamos para executar a nossa funcionalidade.
public record RegisterRequestDTO(
        @Schema(description = "Nome de usuário") @NotBlank String username,
        @Schema(description = "Senha com validação")@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
        String password,
        @Schema(description = "Email")@Email String email,
        @Schema(description = "Data de nascimento no padrão dd-MM-yyyy")@JsonFormat(pattern = "dd-MM-yyyy") LocalDate birthDate
        ) {
}