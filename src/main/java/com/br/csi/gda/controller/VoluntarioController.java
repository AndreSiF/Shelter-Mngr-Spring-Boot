package com.br.csi.gda.controller;

import com.br.csi.gda.model.voluntario.Voluntario;
import com.br.csi.gda.service.VoluntarioService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/get-json")
    public void printJSon(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    public void salvar(@RequestBody Voluntario voluntario){
        this.service.salvar(voluntario);
    }

    @PutMapping
    public void atualizar(@RequestBody Voluntario voluntario){
        this.service.atualizar(voluntario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        this.service.excluir(id);
    }
}
