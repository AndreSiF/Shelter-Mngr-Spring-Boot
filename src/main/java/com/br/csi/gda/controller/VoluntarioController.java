package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.vitima.Vitima;
import com.br.csi.gda.model.voluntario.Voluntario;
import com.br.csi.gda.service.VoluntarioService;
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
@RequestMapping("/voluntario")
@Tag(name = "Voluntários", description = "Path relacionado ao gerenciamento de voluntários")
public class VoluntarioController {
    private final VoluntarioService service;
    public VoluntarioController(VoluntarioService service){
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Voluntários", description = "Lista todos os voluntários cadastrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntários listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class)))
    })
    public List<Voluntario> listar(){
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar voluntário", description = "Lista o voluntário cadastrado com o id especificado do link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntário encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class))),
            @ApiResponse(responseCode = "404", description = "Voluntário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class)))
    })
    public Voluntario voluntario(@PathVariable Integer id){
        return this.service.getVoluntario(id);
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Listar voluntário por UUID", description = "Lista o voluntário cadastrado com o UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntário encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class))),
            @ApiResponse(responseCode = "404", description = "Voluntário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class))),
            @ApiResponse(responseCode = "400", description = "Valor não é um UUID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class)))
    })
    public Voluntario voluntario(@PathVariable String uuid){
        return this.service.getVoluntario(uuid);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Cadastrar voluntário", description = "Cadastra um voluntário no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntário cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class))),
            @ApiResponse(responseCode = "404", description = "Valores inválidos, voluntário não cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class)))
    })
    public ResponseEntity salvar(@RequestBody @Valid Voluntario voluntario, UriComponentsBuilder uriBuilder){
        this.service.salvar(voluntario);
        URI uri = uriBuilder.path("/voluntario/uuid/{uuid}").buildAndExpand(voluntario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(voluntario);
    }

    @PutMapping
    @Operation(summary = "Atualizar voluntário", description = "Atualiza informaçõe do voluntário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntário atualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class)))
    })
    public ResponseEntity atualizar(@RequestBody @Valid Voluntario voluntario){
        this.service.atualizar(voluntario);
        return ResponseEntity.ok(voluntario);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    @Operation(summary = "Deletar voluntário", description = "Deleta o voluntário pelo UUID especificado no link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Voluntário deletado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voluntario.class))),
    })
    public ResponseEntity deletarUUID(@PathVariable String uuid){
        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
