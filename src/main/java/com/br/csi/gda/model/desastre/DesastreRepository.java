package com.br.csi.gda.model.desastre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface DesastreRepository extends JpaRepository<Desastre, Long> {
    Desastre findDesastreByUuid(UUID uuid_desastre);
    void deleteDesastreByUuid(UUID uuid_desastre);

    @Query(value = "SELECT DISTINCT d.uuid_desastre, d.nome, d.data, d.descricao FROM desastre d, usuario u WHERE u.id_desastre = d.id_desastre AND u.uuid_usuario = :id", nativeQuery = true)
    Desastre findDesastreByUuid_usuario(@Param("id") UUID id);
}
