    package com.fitcultural.backend.services;

    import com.fitcultural.backend.dto.auth.LoginRequestDTO;
    import com.fitcultural.backend.dto.token.TokenResponseDTO;
    import com.fitcultural.backend.infra.security.TokenService;
    import com.fitcultural.backend.mappers.UserMapper;
    import com.fitcultural.backend.models.UserEntity;
    import com.fitcultural.backend.repositories.UserRepository;
    import com.fitcultural.backend.dto.auth.RegisterRequestDTO;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.Objects;

    @Service
    public class UserService {

        @Autowired
        private final UserRepository userRepository;
        @Autowired
        private final PasswordEncoder passwordEncoder;
        @Autowired
        private final UserMapper mapper;
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private TokenService tokenService;

        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper mapper, AuthenticationManager authenticationManager, TokenService tokenService) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.mapper = mapper;
            this.authenticationManager = authenticationManager;
            this.tokenService = tokenService;
        }

        //Implementamos uma criptografia no password, após a transformação se torna um código hash.
        public void signClient(RegisterRequestDTO registerRequestDTO) {
            UserEntity userEntity = mapper.to(registerRequestDTO);
            String hashPassword = passwordEncoder.encode(registerRequestDTO.password());
            userEntity.setPassword(hashPassword);
            userRepository.save(userEntity);
        }

        //Implementamos uma geração de Token, que garante a segurança do sistema.
        public TokenResponseDTO loginClient(LoginRequestDTO loginRequestDTO) {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
            Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generateToken((UserEntity) Objects.requireNonNull(authenticate.getPrincipal()));
            return new TokenResponseDTO(token);
        }
    }
