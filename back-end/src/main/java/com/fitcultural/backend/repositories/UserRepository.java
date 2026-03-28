package com.fitcultural.backend.repositories;

import com.fitcultural.backend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
