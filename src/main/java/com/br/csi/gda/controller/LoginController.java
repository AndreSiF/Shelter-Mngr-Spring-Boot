package com.br.csi.gda.controller;

import com.br.csi.gda.model.usuario.DadosUsuario;
import com.br.csi.gda.model.usuario.Login;
import com.br.csi.gda.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class LoginController {
    private final LoginService service;
    public LoginController(LoginService service){
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Login usuario, UriComponentsBuilder uriBuilder){
        this.service.cadastrar(usuario);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping("/{id}")
    public DadosUsuario findById(@PathVariable Long id){
        return this.service.findUsuario(id);
    }

    @GetMapping("/listar")
    public List<DadosUsuario> listar(){
        return this.service.findAllUsuarios();
    }

}
