package com.fitcultural.backend.service;

import com.fitcultural.backend.mappers.LoginMapper;
import com.fitcultural.backend.models.LoginEntity;
import com.fitcultural.backend.repositories.LoginRepository;
import com.fitcultural.backend.services.LoginService;
import dto.LoginRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private LoginMapper mapper;

    @InjectMocks
    private LoginService loginService;

    @Test
    @DisplayName("Deve cadastrar um cliente com sucesso encodando a senha")
    void shouldSignInClient() {
        // 1. Simulação do JSON
        LoginRequestDTO request = new LoginRequestDTO(
                "gabriel",
                "Senha@123",
                "gabriel@email.com",
                LocalDate.of(2007, 12, 13)
        );

        LoginEntity entity = new LoginEntity(); // Cria uma entidade vazia para simular o mapeamento

        when(mapper.to(request)).thenReturn(entity);
        when(passwordEncoder.encode(request.password())).thenReturn("hash_gerado");

        // 2. Method principal, passando o "json" que fizemos la encima para simular
        loginService.signClient(request);

        // 3. verificar se esta tudo conforme esperávamos
        verify(passwordEncoder, times(1)).encode(request.password());
        verify(loginRepository, times(1)).save(any(LoginEntity.class));
    }

}
