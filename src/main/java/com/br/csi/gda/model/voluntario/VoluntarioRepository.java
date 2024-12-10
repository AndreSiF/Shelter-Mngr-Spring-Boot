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

    @Query(value = "SELECT * FROM usuario, voluntario, desastre, usuario_desastre WHERE voluntario.id_usuario = usuario.id_usuario AND usuario_desastre.id_desastre = desastre.id_desastre AND usuario_desastre.id_usuario = usuario.id_usuario AND desastre.uuid_desastre = :id", nativeQuery = true)
    List<Voluntario> findVoluntarioByUuid_desastre(@Param("id") UUID id);

    @Query(value = "SELECT usuario.id_usuario, usuario.uuid_usuario, usuario.nome, usuario.cpf, usuario.idade, usuario.data_cad, voluntario.endereco_volunt, abrigo.id_abrigo, abrigo.uuid_abrigo, abrigo.nome_abrigo, abrigo.endereco FROM usuario, voluntario, abrigo WHERE voluntario.id_usuario = usuario.id_usuario AND abrigo.id_abrigo = usuario.id_abrigo AND abrigo.uuid_abrigo = :id", nativeQuery = true)
    List<Voluntario> findVoluntarioByAbrigo(@Param("id") UUID id);
}
