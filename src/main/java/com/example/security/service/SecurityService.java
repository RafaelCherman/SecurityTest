package com.example.security.service;

import com.example.security.config.SecurityConfig;
import com.example.security.entity.SecurityEntity;
import com.example.security.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService /*implements UserDetailsService*/ {

    @Autowired
    private SecurityRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        return user;
    }*/

    public SecurityEntity create(SecurityEntity user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole().toUpperCase());
        System.out.println(user);
        return repository.save(user);
    }

    public SecurityEntity edit(SecurityEntity userFront, Long id)
    {
        SecurityEntity user = repository.findById(id).orElse(null);
        user.setUsername(userFront.getUsername());
        user.setPassword(passwordEncoder.encode(userFront.getPassword()));
        return repository.save(user);
    }

    public SecurityEntity findById(Long id)
    {
        SecurityEntity user = repository.findById(id).orElse(null);
        return user;
    }
    
}
