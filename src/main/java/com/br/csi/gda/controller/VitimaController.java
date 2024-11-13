package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.vitima.Vitima;
import com.br.csi.gda.service.VitimaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vitima")
public class VitimaController {

    private final VitimaService service;
    public VitimaController(VitimaService service){
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Vitima> listar(){
        return this.service.listar();
    }

    @GetMapping("/{id}")
    public Vitima vitima(@PathVariable Integer id){
        return this.service.getVitima(id);
    }

    @GetMapping("/uuid/{uuid}")
    public Vitima vitima(@PathVariable String uuid){
        return this.service.getVitima(uuid);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Vitima vitima, UriComponentsBuilder uriBuilder){
        this.service.salvar(vitima);
        URI uri = uriBuilder.path("/voluntario/{uuid}").buildAndExpand(vitima.getUuid()).toUri();
        return ResponseEntity.created(uri).body(vitima);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid Vitima vitima){
        this.service.atualizar(vitima);
        return ResponseEntity.ok(vitima);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity deletarUUID(@PathVariable String uuid){
        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
