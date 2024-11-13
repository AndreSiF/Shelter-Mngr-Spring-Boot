package com.br.csi.gda.controller;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.voluntario.Voluntario;
import com.br.csi.gda.service.VoluntarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {
    private final VoluntarioService service;
    public VoluntarioController(VoluntarioService service){
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Voluntario> listar(){
        return this.service.listar();
    }

    @GetMapping("/{id}")
    public Voluntario voluntario(@PathVariable Integer id){
        return this.service.getVoluntario(id);
    }

    @GetMapping("/uuid/{uuid}")
    public Voluntario voluntario(@PathVariable String uuid){
        return this.service.getVoluntario(uuid);
    }

    @PostMapping("/get-json")
    public void printJSon(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Voluntario voluntario, UriComponentsBuilder uriBuilder){
        this.service.salvar(voluntario);
        URI uri = uriBuilder.path("/voluntario/{uuid}").buildAndExpand(voluntario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(voluntario);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid Voluntario voluntario){
        this.service.atualizar(voluntario);
        return ResponseEntity.ok(voluntario);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity deletar(@PathVariable Integer id){
//        this.service.excluir(id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity deletarUUID(@PathVariable String uuid){
        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
