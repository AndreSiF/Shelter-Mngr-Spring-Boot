package com.br.csi.gda.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceJWT {
    public String createToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256("ninguemsabe");
            return JWT.create().withIssuer("API Gerenciar Abrigos")
                    .withSubject(user.getUsername())
                    .withClaim("ROLE", user.getAuthorities().stream().toList().get(0).toString())
                    .withExpiresAt((dataExpiracao())).sign(algorithm);
        }
        catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar JWT token");
        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("ninguemsabe");
            return JWT.require(algorithm)
                    .withIssuer("API Gerenciar Abrigos").build().verify(token).getSubject();
        }
        catch (JWTVerificationException e){
            throw new RuntimeException("Token inválio ou expirado");
        }
    }
}
