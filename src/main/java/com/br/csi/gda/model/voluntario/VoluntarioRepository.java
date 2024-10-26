package com.br.csi.gda.model.voluntario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Integer> {
    Voluntario findVoluntarioByUuid(UUID uuid);
    void deleteVoluntarioByUuid(UUID uuid);
}
