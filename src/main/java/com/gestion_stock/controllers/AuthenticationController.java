package com.gestion_stock.controllers;

import com.gestion_stock.dto.auth.AuthenticationRequestDto;
import com.gestion_stock.dto.auth.AuthenticationResponseDto;
import com.gestion_stock.model.Utilisateur;
import com.gestion_stock.services.JwtUtil;
import com.gestion_stock.services.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("auth")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto utilisateur){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getLogin(), utilisateur.getPassword()));
        UserDetails userDetails=applicationUserDetailsService.loadUserByUsername("lol");
        String jwt=jwtUtil.generateToken(userDetails);
       // final UserDetails userDetails=applicationUserDetailsService.loadUserByUsername(utilisateur.getLogin());
        return ResponseEntity.ok(AuthenticationResponseDto.builder().accessToken(jwt).build());
    }

    @GetMapping("auth")
    public ResponseEntity<String> authenticate(@RequestParam("login") String login,@RequestParam("password") String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login,password));

//        final UserDetails userDetails=applicationUserDetailsService.loadUserByUsername(utilisateur.getLogin());
        return ResponseEntity.ok("AuthenticationResponse.builder().accessToken('').build()");
    }


}
