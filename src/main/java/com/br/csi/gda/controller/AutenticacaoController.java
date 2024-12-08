package com.br.csi.gda.controller;

import com.br.csi.gda.infra.security.TokenServiceJWT;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    private final AuthenticationManager manager;
    private final TokenServiceJWT JWTService;
    public AutenticacaoController(AuthenticationManager manager, TokenServiceJWT JWTService){
        this.manager = manager;
        this.JWTService = JWTService;
    }

    @PostMapping
    public ResponseEntity fazerLogin(@RequestBody @Valid DadosAutenticacao dados){
        try{
            Authentication autenticado = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            Authentication at = manager.authenticate(autenticado);

            User user = (User) at.getPrincipal();
            String token = this.JWTService.createToken(user);

            return ResponseEntity.ok().body(new DadosTokenJWT(token));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private record DadosTokenJWT(String token){}
    private record DadosAutenticacao(String login, String senha){}
}
