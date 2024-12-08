package com.br.csi.gda.model.voluntario;

import com.br.csi.gda.model.vitima.Vitima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    public Voluntario findVoluntarioByUuid(UUID uuid);
    public void deleteVoluntarioByUuid(UUID uuid);

    @Query(value = "SELECT DISTINCT u.uuid_usuario, u.nome, u.cpf, u.data_cad, u.idade, u.id_abrigo, v.endereco FROM usuario u, voluntario v, desastre d WHERE v.id_usuario = u.id_usuario AND u.id_desastre = d.id_desastre AND d.uuid_desastre = :id", nativeQuery = true)
    List<Voluntario> findVoluntarioByUuid_desastre(@Param("id") UUID id);

    @Query(value = "SELECT u.uuid_usuario, u.nome, u.cpf, u.data_cad, u.idade, u.id_abrigo, v.endereco FROM usuario u, voluntario v, abrigo a WHERE v.id_usuario = u.id_usuario AND a.id_abrigo = u.id_abrigo AND a.uuid_abrigo = :id", nativeQuery = true)
    List<Voluntario> findVoluntarioByAbrigo(@Param("id") UUID id);
}
