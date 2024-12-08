package com.br.csi.gda.model.vitima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VitimaRepository extends JpaRepository<Vitima, Long> {
    Vitima findVitimaByUuid(UUID uuid);
    void deleteVitimaByUuid(UUID uuid);

    @Query(value = "SELECT * FROM usuario, vitima, desastre, usuario_desastre WHERE vitima.id_usuario = usuario.id_usuario AND usuario_desastre.id_desastre = desastre.id_desastre AND usuario_desastre.id_usuario = usuario.id_usuario AND desastre.uuid_desastre = :id", nativeQuery = true)
    List<Vitima> findVitimasByUuid_desastre(@Param("id") UUID id);


    //funciona no sql mas não aqui, não sei o que fazer
    @Query(value = "SELECT usuario.nome, usuario.cpf, usuario.idade, usuario.data_cad, vitima.ultimo_end, vitima.descricao FROM usuario, vitima, abrigo WHERE vitima.id_usuario = usuario.id_usuario AND abrigo.id_abrigo = usuario.id_abrigo AND abrigo.uuid_abrigo = :id", nativeQuery = true)
    List<Vitima> findVitimasByAbrigo(@Param("id") UUID id);
}
