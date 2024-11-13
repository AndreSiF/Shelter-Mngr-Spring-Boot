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

    public Vitima getVitima(int id){
        return this.repository.findById(id).get();
    }

//    public void excluir(int id){
//        this.repository.deleteById(id);
//    }

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

    //    public void atualizar(Vitima vitima){
//        Vitima v = this.repository.getReferenceById(vitima.getId());
//        v.setNome(vitima.getNome());
//        v.setCpf(vitima.getCpf());
//        v.setRiscos(vitima.getRiscos());
//        v.setPresente(vitima.isPresente());
//        v.setUltimoEnd(vitima.getUltimoEnd());
//        v.setIdade(vitima.getIdade());
//        v.setPermissao(vitima.getPermissao());
//        this.repository.save(v);
//    }
}
