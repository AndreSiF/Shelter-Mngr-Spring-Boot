package com.br.csi.gda.service;

import com.br.csi.gda.model.voluntario.Voluntario;
import com.br.csi.gda.model.voluntario.VoluntarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VoluntarioService {
    private final VoluntarioRepository repository;
    public VoluntarioService(VoluntarioRepository repository){
        this.repository = repository;
    }

    public void salvar(Voluntario voluntario){
        this.repository.save(voluntario);
    }

    public List<Voluntario> listar(){
        return this.repository.findAll();
    }

    public Voluntario getVoluntario(int id){
        return this.repository.findById(id).get();
    }

    public void excluir(int id){
        this.repository.deleteById(id);
    }

    public void atualizar(Voluntario voluntario){
        Voluntario v = this.repository.findVoluntarioByUuid(voluntario.getUuid());
        v.setNome(voluntario.getNome());
        v.setCpf(voluntario.getCpf());
        v.setEndereco(voluntario.getEndereco());
        v.setIdade(voluntario.getIdade());
        v.setPerm(voluntario.getPerm());
        this.repository.save(v);
    }

    public Voluntario getVoluntario(String uuid){
        return this.repository.findVoluntarioByUuid(UUID.fromString(uuid));
    }

    public void deletar(String uuid){
        this.repository.deleteVoluntarioByUuid(UUID.fromString(uuid));
    }
}
