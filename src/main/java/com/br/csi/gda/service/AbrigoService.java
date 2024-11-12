package com.br.csi.gda.service;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.abrigo.AbrigoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AbrigoService {
    private final AbrigoRepository repository;
    public AbrigoService(AbrigoRepository repository){
        this.repository = repository;
    }

    public void salvar(Abrigo abrigo){
        this.repository.save(abrigo);
    }

    public List<Abrigo> listar(){
        return this.repository.findAll();
    }

    public Abrigo getAbrigo(int id){
        return this.repository.findById(id).get();
    }

    public void excluir(int id){
        this.repository.deleteById(id);
    }

    public void atualizar(Abrigo abrigo){
        Abrigo a = this.repository.findAbrigoByUuid(abrigo.getUuid());
        a.setNome(abrigo.getNome());
        a.setEndereco(abrigo.getEndereco());
        a.setCadastros(abrigo.getCadastros());
        a.setVagas(abrigo.getVagas());
        this.repository.save(a);
    }

    public Abrigo getAbrigo(String uuid){
        return this.repository.findAbrigoByUuid(UUID.fromString(uuid));
    }

    public void deletarUUID(String uuid){
        this.repository.deleteAbrigoByUuid(UUID.fromString(uuid));
    }
}
