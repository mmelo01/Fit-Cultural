package com.fitcultural.backend.controllers;

import com.fitcultural.backend.services.LoginService;
import dto.LoginRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Tag(name = "Login")
    @Operation(description = "Criação do cadastro do cliente, visando a segurança.")
    @ApiResponse(responseCode = "201")
    @PostMapping("/auth/sign")
    public ResponseEntity<String> signClient(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        loginService.signClient(loginRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}
