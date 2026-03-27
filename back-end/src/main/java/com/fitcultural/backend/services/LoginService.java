    package com.fitcultural.backend.services;

    import com.fitcultural.backend.mappers.LoginMapper;
    import com.fitcultural.backend.models.LoginEntity;
    import com.fitcultural.backend.repositories.LoginRepository;
    import dto.LoginRequestDTO;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    public class LoginService {

        private final LoginRepository loginRepository;
        private final PasswordEncoder passwordEncoder;
        private final LoginMapper mapper;

        public LoginService(LoginRepository loginRepository, PasswordEncoder passwordEncoder, LoginMapper mapper) {
            this.loginRepository = loginRepository;
            this.passwordEncoder = passwordEncoder;
            this.mapper = mapper;
        }

        public void signClient(LoginRequestDTO loginRequestDTO) {
            LoginEntity loginEntity = mapper.to(loginRequestDTO);
            String hashPassword = passwordEncoder.encode(loginRequestDTO.password());
            loginEntity.setPassword(hashPassword);
            loginRepository.save(loginEntity);
        }
    }
