package com.springboot.project.nisumprojectrestapp.service.impl;


import com.springboot.project.nisumprojectrestapp.entity.UserEntity;
import com.springboot.project.nisumprojectrestapp.exceptions.UserBadRequestException;
import com.springboot.project.nisumprojectrestapp.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthUserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Optional<UserEntity> user = userRepository.getUserEntityByEmail(email);


        if (user.isPresent()) {
            authorities = user.get().getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .peek( authority -> log.info("Role: " + authority.getAuthority()))
                    .collect(Collectors.toList());
        } else {
            log.info("Error en el login:no existe el usuario '" + email +"' en el sistema");
            throw new UserBadRequestException("Error en el login:no existe el usuario '" + email +"' en el sistema");
        }

        return new User(user.get().getEmail(),
                user.get().getPassword(),
                user.get().getIsActive(),
                true, true, true, authorities);

    }

}
