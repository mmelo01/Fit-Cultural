package com.fitcultural.backend.service;

import com.fitcultural.backend.mappers.UserMapper;
import com.fitcultural.backend.models.UserEntity;
import com.fitcultural.backend.repositories.UserRepository;
import com.fitcultural.backend.services.UserService;
import com.fitcultural.backend.dto.auth.RegisterRequestDTO;
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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Deve cadastrar um cliente com sucesso encodando a senha")
    void shouldSignInClient() {
        // 1. Simulação do JSON
        RegisterRequestDTO request = new RegisterRequestDTO(
                "gabriel",
                "Senha@123",
                "gabriel@email.com",
                LocalDate.of(2007, 12, 13)
        );

        UserEntity entity = new UserEntity(); // Cria uma entidade vazia para simular o mapeamento

        when(mapper.to(request)).thenReturn(entity);
        when(passwordEncoder.encode(request.password())).thenReturn("hash_gerado");

        // 2. Method principal, passando o "json" que fizemos la encima para simular
        userService.signClient(request);

        // 3. verificar se esta tudo conforme esperávamos
        verify(passwordEncoder, times(1)).encode(request.password());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

}
