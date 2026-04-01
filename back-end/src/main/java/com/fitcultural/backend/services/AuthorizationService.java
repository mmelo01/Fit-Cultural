package com.fitcultural.backend.services;

import com.fitcultural.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Diz ao Spring que esta é uma classe de serviço que ele deve gerir
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // O "email" que o Spring envia aqui é o e-mail que o usuário digitou no login
        UserDetails user = repository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        return user;
    }
}