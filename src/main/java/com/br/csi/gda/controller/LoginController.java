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
    public ResponseEntity salvar(@RequestBody @Valid Login usuario, UriComponentsBuilder uriBuilder){
        this.service.cadastrar(usuario);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar usuário", description = "Lista o usuário cadastrado com o id especificado do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public DadosUsuario findById(@PathVariable Long id){
        return this.service.findUsuario(id);
    }

    @GetMapping("/listar")
    public List<DadosUsuario> listar(){
        return this.service.findAllUsuarios();
    }

}
