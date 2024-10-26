package com.br.csi.gda.controller;

import com.br.csi.gda.model.vitima.Vitima;
import com.br.csi.gda.service.VitimaService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/get-json")
    public void printJSon(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    public void salvar(@RequestBody Vitima vitima){
        this.service.salvar(vitima);
    }

    @PutMapping
    public void atualizar(@RequestBody Vitima vitima){
        this.service.atualizar(vitima);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        this.service.excluir(id);
    }
}
