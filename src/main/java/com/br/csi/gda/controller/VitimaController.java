package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.vitima.Vitima;
import com.br.csi.gda.service.VitimaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vitima")
@Tag(name = "Vítimas", description = "Path relacionado ao gerenciamento de vítimas")
public class VitimaController {

    private final VitimaService service;
    public VitimaController(VitimaService service){
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar vitimas", description = "Lista todas as vitimas cadastradas no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vitimas listadas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class)))
    })
    public List<Vitima> listar(){
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar vítima", description = "Lista a vítima cadastrada com o id especificado do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vítima encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class))),
            @ApiResponse(responseCode = "404", description = "Vítima não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class)))
    })
    public Vitima vitima(@PathVariable Integer id){
        return this.service.getVitima(id);
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Listar vítima por UUID", description = "Lista a vítima cadastrada com o UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vítima encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class))),
            @ApiResponse(responseCode = "404", description = "Vítima não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um UUID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class)))
    })
    public Vitima vitima(@PathVariable String uuid){
        return this.service.getVitima(uuid);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Cadastrar vítima", description = "Cadastra uma vítima no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vítima cadastrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class))),
            @ApiResponse(responseCode = "404", description = "Valores inválidos, vítima não cadastrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class)))
    })
    public ResponseEntity salvar(@RequestBody @Valid Vitima vitima, UriComponentsBuilder uriBuilder){
        this.service.salvar(vitima);
        URI uri = uriBuilder.path("/vitima/uuid/{uuid}").buildAndExpand(vitima.getUuid()).toUri();
        return ResponseEntity.created(uri).body(vitima);
    }

    @PutMapping
    @Operation(summary = "Atualizar vítima", description = "Atualiza informações da vítima")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vítima atualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class)))
    })
    public ResponseEntity atualizar(@RequestBody @Valid Vitima vitima){
        this.service.atualizar(vitima);
        return ResponseEntity.ok(vitima);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    @Operation(summary = "Deletar vítima", description = "Deleta a vítima pelo UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vítima deletada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vitima.class))),
    })
    public ResponseEntity deletarUUID(@PathVariable String uuid){
        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
