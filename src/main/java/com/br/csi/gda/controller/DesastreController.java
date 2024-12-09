package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.desastre.Desastre;
import com.br.csi.gda.service.DesastreService;
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
@RequestMapping("/desastre")
public class DesastreController {
    private final DesastreService service;
    public DesastreController(DesastreService service){
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar desastres", description = "Lista todos os desastres cadastrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desastres listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public List<Desastre> listar(){
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar desastre", description = "Lista o desastre cadastrado com o id especificado do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desastre encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Desastre não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public Desastre desastre(@PathVariable Long id){
        return this.service.getDesastre(id);
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Listar desastre por UUID", description = "Lista o desastre cadastrado com o UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desastre encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Desastre não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um UUID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public Desastre desastre(@PathVariable String uuid){
        return this.service.getDesastre(uuid);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Cadastrar desastre", description = "Cadastra um desastre no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desastre cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Valores inválidos, abrigo não cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public ResponseEntity salvar(@RequestBody @Valid Desastre desastre, UriComponentsBuilder uriBuilder){
        this.service.salvar(desastre);
        URI uri = uriBuilder.path("/desastre/uuid/{uuid_desastre}").buildAndExpand(desastre.getUuid()).toUri();
        return ResponseEntity.created(uri).body(desastre);
    }

    @PutMapping
    @Operation(summary = "Atualizar desastre", description = "Atualiza informações do desastre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desastre atualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
    })
    public ResponseEntity atualizar(@RequestBody @Valid Desastre desastre){
        this.service.atualizar(desastre);
        return ResponseEntity.ok(desastre);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    @Operation(summary = "Deletar desastre", description = "Deleta o desastre pelo UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Desastre deletado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
    })
    public ResponseEntity deletarUUID(@PathVariable String uuid){
        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
