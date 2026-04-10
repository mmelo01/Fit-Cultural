package com.fitcultural.backend.mappers;

import com.fitcultural.backend.dto.auth.RegisterRequestDTO;
import com.fitcultural.backend.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity to(RegisterRequestDTO dto) {
        if (dto == null) return null;
        return new UserEntity(dto.username(), dto.password(), dto.email(), dto.birthDate());
    }
}
