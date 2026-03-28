    package com.fitcultural.backend.services;

    import com.fitcultural.backend.dto.auth.LoginRequestDTO;
    import com.fitcultural.backend.dto.token.TokenResponseDTO;
    import com.fitcultural.backend.mappers.UserMapper;
    import com.fitcultural.backend.models.UserEntity;
    import com.fitcultural.backend.repositories.UserRepository;
    import com.fitcultural.backend.dto.auth.RegisterRequestDTO;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    public class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final UserMapper mapper;

        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper mapper) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.mapper = mapper;
        }

        public void signClient(RegisterRequestDTO registerRequestDTO) {
            UserEntity userEntity = mapper.to(registerRequestDTO);
            String hashPassword = passwordEncoder.encode(registerRequestDTO.password());
            userEntity.setPassword(hashPassword);
            userRepository.save(userEntity);
        }

        public TokenResponseDTO loginClient(LoginRequestDTO loginRequestDTO) {
            //TODO Implementar o TOKEN JWT aqui e verificar output
            return null;
        }
    }
