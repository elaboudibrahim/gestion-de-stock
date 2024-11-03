package com.gestion_stock.services.auth;

import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.model.Utilisateur;
import com.gestion_stock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Utilisateur utilisateur=utilisateurRepository.findUtilisateurByEmail(email).orElseThrow(()->
//                new InvalidEntityException("Aucun utilisateur convenable avec cet email"));

//        return new User("lol",passwordEncoder.encode("lol"),);
        return User.withUsername("lol").password(passwordEncoder.encode("lol")).roles("admin").build();
    }
}
