package com.gestion_stock.config;

import com.gestion_stock.services.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    @Autowired
    ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
    ApplicationRequestFilter applicationRequestFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests((request)->request.requestMatchers("auth").permitAll());
        http.authorizeHttpRequests((request)->request.requestMatchers("/gestionstock/all").authenticated());
        http.authorizeHttpRequests((request)->request.anyRequest().authenticated());

        http.userDetailsService(applicationUserDetailsService);
        http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
    //    http.formLogin(withDefaults());
      //  http.formLogin(f->f.disable());
//       http.httpBasic(withDefaults());
        return http.build();
    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//       return auth.userDetailsService(applicationUserDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .and().build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
