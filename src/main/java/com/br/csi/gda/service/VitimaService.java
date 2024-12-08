package com.br.csi.gda.service;

import com.br.csi.gda.model.vitima.Vitima;
import com.br.csi.gda.model.vitima.VitimaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VitimaService {
    private final VitimaRepository repository;
    public VitimaService(VitimaRepository repository){
        this.repository = repository;
    }

    public void salvar(Vitima vitima){
        this.repository.save(vitima);
    }

    public List<Vitima> listar(){
        return this.repository.findAll();
    }

    public Vitima getVitima(Long id){
        return this.repository.findById(id).get();
    }

    public List<Vitima> getVitimaByDesastre(String uuid){
        return this.repository.findVitimaByUuid_desastre(UUID.fromString(uuid));
    }

    public List<Vitima> getVitimasByAbrigo(String uuid){
        return this.repository.findVitimaByAbrigo(UUID.fromString(uuid));
    }

    public void atualizar(Vitima vitima){
        Vitima v = this.repository.findVitimaByUuid(vitima.getUuid());
        v.setNome(vitima.getNome());
        v.setCpf(vitima.getCpf());
        v.setDescricao(vitima.getDescricao());
        v.setPresente(vitima.isPresente());
        v.setUltimoEnd(vitima.getUltimoEnd());
        v.setIdade(vitima.getIdade());
        v.setPerm(vitima.getPerm());
        v.setAbrigo(vitima.getAbrigo());
        this.repository.save(v);
    }

    public Vitima getVitima(String uuid){
        return this.repository.findVitimaByUuid(UUID.fromString(uuid));
    }

    public void deletarUUID(String uuid){
        this.repository.deleteVitimaByUuid(UUID.fromString(uuid));
    }
}
