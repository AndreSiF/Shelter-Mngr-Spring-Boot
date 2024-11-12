package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.abrigo.AbrigoRepository;
import com.br.csi.gda.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/abrigo")
public class AbrigoController {
    private final AbrigoService service;
    public AbrigoController(AbrigoService service){
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Abrigo> listar(){
        return this.service.listar();
    }

    @GetMapping("/{id}")
    public Abrigo abrigo(@PathVariable Integer id){
        return this.service.getAbrigo(id);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Abrigo abrigo, UriComponentsBuilder uriBuilder){
        this.service.salvar(abrigo);
        URI uri = uriBuilder.path("/abrigo/{uuid_abrigo}").buildAndExpand(abrigo.getUuid()).toUri();
        return ResponseEntity.created(uri).body(abrigo);
    }

    @GetMapping("/uuid/{uuid}")
    public Abrigo abrigo(@PathVariable String uuid){
        return this.service.getAbrigo(uuid);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid Abrigo abrigo){
        this.service.atualizar(abrigo);
        return ResponseEntity.ok(abrigo);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity deletarUUID(@PathVariable String uuid){
        System.out.println(uuid);
        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
