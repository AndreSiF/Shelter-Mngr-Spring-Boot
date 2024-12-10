package com.br.csi.gda.model.desastre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface DesastreRepository extends JpaRepository<Desastre, Long> {
    public Desastre findDesastreByUuid(UUID uuid_desastre);
    public void deleteDesastreByUuid(UUID uuid_desastre);
}
