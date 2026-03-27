package com.fitcultural.backend.repositories;

import com.fitcultural.backend.models.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
}
