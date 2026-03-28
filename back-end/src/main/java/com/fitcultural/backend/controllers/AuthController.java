package com.fitcultural.backend.controllers;

import com.fitcultural.backend.dto.auth.LoginRequestDTO;
import com.fitcultural.backend.dto.token.TokenResponseDTO;
import com.fitcultural.backend.services.UserService;
import com.fitcultural.backend.dto.auth.RegisterRequestDTO;
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
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Tag(name = "Login")
    @Operation(description = "Criação do cadastro do cliente, visando a segurança.")
    @ApiResponse(responseCode = "201")
    @PostMapping("/auth/sign")
    public ResponseEntity<String> registerClient(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        userService.signClient(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }


    //TODO Eu (gabriel) estava fazendo esse endpoint, não conseguirei terminar agora, então irei continuar depois, mas esta quase pronto.
    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        TokenResponseDTO tokenResponseDTO = userService.loginClient(loginRequestDTO);
        return null;
    }

}
