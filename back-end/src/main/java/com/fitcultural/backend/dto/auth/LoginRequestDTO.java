package com.fitcultural.backend.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

//Request quer dizer 'Requisição', ou seja, esse código é o que solicita ao front-end os dados que necessitamos para executar a nossa funcionalidade.
public record LoginRequestDTO(@Schema(description = "Senha com validação")
                              @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
                              String password,
                              @Schema(description = "Email")
                              @Email String email) {
}
