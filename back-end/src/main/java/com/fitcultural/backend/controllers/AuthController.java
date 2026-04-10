package com.fitcultural.backend.controllers;

import com.fitcultural.backend.dto.auth.LoginRequestDTO;
import com.fitcultural.backend.dto.token.TokenResponseDTO;
import com.fitcultural.backend.services.UserService;
import com.fitcultural.backend.dto.auth.RegisterRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
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
@Tag(name = "Autenticação", description = "Endpoints para login e registro de usuários")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Tag(name = "Autenticação")
    @Operation(summary = "Realiza o cadastro", description = "Criação do cadastro do cliente, visando a segurança.")
    @ApiResponse(responseCode = "201")
    @PostMapping("/auth/sign")
    //Mandamos um @RequestBody com os dados necessários para o cadastro, e retornamos uma mensagem para o front
    public ResponseEntity<String> registerClient(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        userService.signClient(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @Tag(name = "Autenticação")
    @Operation(summary = "Realiza o login", description = "Recebe e-mail e senha e retorna um token JWT")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Credenciais inválidas")
    @PostMapping("/login")
    //Mandamos um @RequestBody com os dados necessários para o login, e retornamos o Token para o front.
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        TokenResponseDTO tokenResponseDTO = userService.loginClient(loginRequestDTO);
        return ResponseEntity.ok(tokenResponseDTO);
    }
}