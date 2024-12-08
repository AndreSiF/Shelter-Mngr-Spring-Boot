package com.br.csi.gda.model.vitima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VitimaRepository extends JpaRepository<Vitima, Long> {
    Vitima findVitimaByUuid(UUID uuid);
    void deleteVitimaByUuid(UUID uuid);

    @Query(value = "SELECT DISTINCT u.uuid_usuario, u.nome, u.cpf, u.data_cad, u.idade, u.perm, u.id_abrigo, v.ultimo_end, v.presente, v.descricao FROM usuario u, vitima v, desastre d WHERE v.id_usuario = u.id_usuario AND u.id_desastre = d.id_desastre AND d.uuid_desastre = :id", nativeQuery = true)
    List<Vitima> findVitimaByUuid_desastre(@Param("id") UUID id);

    @Query(value = "SELECT u.uuid_usuario, u.nome, u.cpf, u.data_cad, u.idade, u.perm, u.id_abrigo, v.ultimo_end, v.presente, v.descricao FROM usuario u, vitima v, abrigo a WHERE v.id_usuario = u.id_usuario AND a.id_abrigo = u.id_abrigo AND a.uuid_abrigo = :id", nativeQuery = true)
    List<Vitima> findVitimaByAbrigo(@Param("id") UUID id);
}
