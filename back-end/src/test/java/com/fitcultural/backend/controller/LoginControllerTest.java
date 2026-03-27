package com.fitcultural.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitcultural.backend.controllers.LoginController;
import com.fitcultural.backend.services.LoginService;
import dto.LoginRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false) // Abre a porta da segurança para o teste
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Novo no Spring Boot 3.4
    private LoginService loginService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Test
    @DisplayName("Deve retornar 400 quando a senha não segue o Regex")
    void shouldReturnWrongPassword() throws Exception {
        LoginRequestDTO requestInvalido = new LoginRequestDTO(
                "gabriel",
                "123", // Senha curta demais
                "email@valido.com",
                LocalDate.now()
        );

        mockMvc.perform(post("/api/auth/sign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestInvalido)))
                .andExpect(status().isBadRequest());
    }
}