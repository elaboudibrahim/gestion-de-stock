package com.gestion_stock.config;

import com.gestion_stock.services.JwtUtil;
import com.gestion_stock.services.auth.ApplicationUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class ApplicationRequestFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ApplicationUserDetailsService applicationUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final  String authHeader=request.getHeader("Authorization");

        String username=null;
        String jwt=null;
        if(StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")){
            jwt=authHeader.substring(7);
            username=jwtUtil.extractUsername(authHeader);
        }
        if(StringUtils.hasLength(username) && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= applicationUserDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt,userDetails)){

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,
                    null,userDetails.getAuthorities());
            //ajouter des détails supplémentaires sur la requête d'authentification
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

    }
}
