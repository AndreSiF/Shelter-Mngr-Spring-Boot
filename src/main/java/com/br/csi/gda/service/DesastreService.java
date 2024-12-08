package com.br.csi.gda.service;

import com.br.csi.gda.model.desastre.Desastre;
import com.br.csi.gda.model.desastre.DesastreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DesastreService {
    private final DesastreRepository repository;
    public DesastreService(DesastreRepository repository){
        this.repository = repository;
    }

    public void salvar(Desastre desastre){
        this.repository.save(desastre);
    }

    public List<Desastre> listar(){
        return this.repository.findAll();
    }

    public Desastre getDesastre(Long id){
        return this.repository.findById(id).get();
    }

    public void atualizar(Desastre desastre){
        Desastre d = this.repository.findDesastreByUuid(desastre.getUuid());
        d.setNome(desastre.getNome());
        d.setData(desastre.getData());
        d.setDescricao(desastre.getDescricao());
        this.repository.save(d);
    }

    public Desastre getDesastre(String uuid){
        return this.repository.findDesastreByUuid(UUID.fromString(uuid));
    }

    public void deletarUUID(String uuid){
        this.repository.deleteDesastreByUuid(UUID.fromString(uuid));
    }
}
