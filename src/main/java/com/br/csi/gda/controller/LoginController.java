package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.usuario.DadosUsuario;
import com.br.csi.gda.model.usuario.Login;
import com.br.csi.gda.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cadastrar login", description = "Cadastra um login no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Valores inválidos, login não cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public ResponseEntity salvar(@RequestBody @Valid Login usuario, UriComponentsBuilder uriBuilder){
        this.service.cadastrar(usuario);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar login", description = "Lista o login cadastrado com o id especificado do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "login encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "login não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public DadosUsuario findById(@PathVariable Long id){
        return this.service.findUsuario(id);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar logins", description = "Lista todos os logins cadastrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logins listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public List<DadosUsuario> listar(){
        return this.service.findAllUsuarios();
    }

}
