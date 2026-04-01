package com.fitcultural.backend.service;

import com.fitcultural.backend.dto.auth.LoginRequestDTO;
import com.fitcultural.backend.dto.token.TokenResponseDTO;
import com.fitcultural.backend.infra.security.TokenService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper mapper;

    @Mock
    private AuthenticationManager authenticationManager; // Adicione esta linha!

    @Mock
    private TokenService tokenService;

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

    @Test
    @DisplayName("Deve retornar o Token quando o login for feito com sucesso.")
    @WithMockUser
    void shouldLoginClientWithToken(){
        LoginRequestDTO request = new LoginRequestDTO("gabriel@email.com", "Senha@123");
        UserEntity user = new UserEntity();
        user.setEmail("gabriel@email.com");
        user.setPassword("SenhaCriptografada");

        var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, authorities);

        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(tokenService.generateToken(user)).thenReturn("token-fake-123");

        TokenResponseDTO response = userService.loginClient(request);

        assertNotNull(response);
        assertEquals("token-fake-123", response.token());

        // Verifica se o AuthenticationManager foi chamado com os dados certos
        verify(authenticationManager, times(1)).authenticate(any());
        // Verifica se o TokenService gerou o token para o usuário que o Manager retornou
        verify(tokenService, times(1)).generateToken(user);

        }

}
