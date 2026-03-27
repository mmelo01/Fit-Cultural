package com.fitcultural.backend.mappers;

import com.fitcultural.backend.models.LoginEntity;
import dto.LoginRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginMapper {
    LoginEntity to(LoginRequestDTO loginRequestDTO);
}
