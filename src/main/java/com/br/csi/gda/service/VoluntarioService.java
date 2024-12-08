package com.br.csi.gda.service;

import com.br.csi.gda.model.vitima.Vitima;
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

    public Voluntario getVoluntario(Long id){
        return this.repository.findById(id).get();
    }

    public List<Voluntario> getVoluntarioByDesastre(String uuid){
        return this.repository.findVoluntarioByUuid_desastre(UUID.fromString(uuid));
    }

    public List<Voluntario> getVoluntariosByAbrigo(String uuid){
        return this.repository.findVoluntarioByAbrigo(UUID.fromString(uuid));
    }

    public void atualizar(Voluntario voluntario){
        Voluntario v = this.repository.findVoluntarioByUuid(voluntario.getUuid());
        v.setNome(voluntario.getNome());
        v.setCpf(voluntario.getCpf());
        v.setEndereco(voluntario.getEndereco());
        v.setIdade(voluntario.getIdade());
        v.setPerm(voluntario.getPerm());
        v.setAbrigo(voluntario.getAbrigo());
        this.repository.save(v);
    }

    public Voluntario getVoluntario(String uuid){
        return this.repository.findVoluntarioByUuid(UUID.fromString(uuid));
    }

    public void deletarUUID(String uuid){
        this.repository.deleteVoluntarioByUuid(UUID.fromString(uuid));
    }
}
