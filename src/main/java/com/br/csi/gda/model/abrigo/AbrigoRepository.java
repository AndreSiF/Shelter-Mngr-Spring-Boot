package com.br.csi.gda.model.abrigo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AbrigoRepository extends JpaRepository <Abrigo, Long> {
    Abrigo findAbrigoByUuid(UUID uuid);
    void deleteAbrigoByUuid(UUID uuid);

    @Query(value = "SELECT a.uuid_abrigo AS uuid, a.nome_abrigo AS nome, a.endereco AS endereco, a.vagas AS vagas, a.cadastros AS cadastros FROM desastre d, abrigo a, abrigo_desastre ad WHERE ad.id_abrigo = a.id_abrigo AND ad.id_desastre = d.id_desastre AND ad.uuid_abrigo = :id", nativeQuery = true)
    List<Abrigo> findAbrigosByDesastre(@Param("id") UUID id);

    @Query(value = "SELECT DISTINCT a.uuid_abrigo AS uuid, a.nome_abrigo AS nome, a.endereco AS endereco, a.vagas AS vagas, a.cadastros AS cadastros FROM usuario u, abrigo a WHERE a.id_abrigo = u.id_abrigo AND u.uuid_abrigo = :id", nativeQuery = true)
    Abrigo findAbrigoByUsuario(@Param("id") UUID id);
}
