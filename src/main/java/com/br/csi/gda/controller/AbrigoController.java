package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.abrigo.AbrigoRepository;
import com.br.csi.gda.service.AbrigoService;
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
import java.util.UUID;

@RestController
@RequestMapping("/abrigo")
@Tag(name = "Abrigos", description = "Path relacionado ao gerenciamento de abrigos")
public class AbrigoController {
    private final AbrigoService service;
    public AbrigoController(AbrigoService service){
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar abrigos", description = "Lista todos os abrigos cadastrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigos listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public List<Abrigo> listar(){
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar abrigo", description = "Lista o abrigo cadastrado com o id especificado do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public Abrigo abrigo(@PathVariable Long id){
        return this.service.getAbrigo(id);
    }

    @GetMapping("/pessoa/{uuid}")
    @Operation(summary = "Listar abrigo baseado no usuario", description = "Lista o abrigo cadastrado baseado na pessoa especificada do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um uuid", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public Abrigo abrigoPorUsuario(@PathVariable String uuid){
        return this.service.getAbrigoByUsuario(uuid);
    }

    @GetMapping("/desastre/{uuid}")
    @Operation(summary = "Listar abrigo baseado no desastre", description = "Lista o abrigo cadastrado baseado no desastre especificado do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public List<Abrigo> abrigoPorDesastre(@PathVariable String uuid){
        return this.service.getAbrigosByDesastre(uuid);
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Listar abrigo por UUID", description = "Lista o abrigo cadastrado com o UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um UUID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))
    })
    public Abrigo abrigo(@PathVariable String uuid){
        return this.service.getAbrigo(uuid);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Cadastrar abrigo", description = "Cadastra um abrigo no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "404", description = "Valores inválidos, abrigo não cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class)))

    })
    public ResponseEntity salvar(@RequestBody @Valid Abrigo abrigo, UriComponentsBuilder uriBuilder){
        this.service.salvar(abrigo);
        URI uri = uriBuilder.path("/abrigo/uuid/{uuid_abrigo}").buildAndExpand(abrigo.getUuid()).toUri();
        return ResponseEntity.created(uri).body(abrigo);
    }

    @PutMapping
    @Operation(summary = "Atualizar abrigo", description = "Atualiza informações do abrigo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo atualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
    })
    public ResponseEntity atualizar(@RequestBody @Valid Abrigo abrigo){
        this.service.atualizar(abrigo);
        return ResponseEntity.ok(abrigo);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    @Operation(summary = "Deletar abrigo", description = "Deleta o abrigo pelo UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Abrigo deletado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
            @ApiResponse(responseCode = "403", description = "Permissões insuficientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Abrigo.class))),
    })
    public ResponseEntity deletarUUID(@PathVariable String uuid){
        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
