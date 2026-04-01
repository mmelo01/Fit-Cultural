package com.fitcultural.backend.mappers;

import com.fitcultural.backend.models.UserEntity;
import com.fitcultural.backend.dto.auth.RegisterRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
//Usamos a biblioteca Mapstruct para mapear DTO'S e entities sem precisar fazer manualmente.
//(Lembrando que os campos tem quer ter os mesmos nomes e tipos!!!)
public interface UserMapper {
    UserEntity to(RegisterRequestDTO registerRequestDTO);
}
