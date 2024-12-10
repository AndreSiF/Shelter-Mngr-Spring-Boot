package com.br.csi.gda.infra.security;

import com.br.csi.gda.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {
    private final AutenticacaoService service;
    private final TokenServiceJWT JWTService;

    public AutenticacaoFilter(AutenticacaoService service, TokenServiceJWT JWTService){
        this.service = service;
        this.JWTService = JWTService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        System.out.println("Filtro para autenticação do token");
        String tokenJWT = recuperarToken(request);
        System.out.println("Token: " + tokenJWT);
        if(tokenJWT != null){
            String subject = this.JWTService.getSubject(tokenJWT);
            System.out.println("Login da requisição. " + subject);

            UserDetails userDetails = this.service.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            return token.replace("Bearer", "").trim();
        }
        return null;
    }
}
