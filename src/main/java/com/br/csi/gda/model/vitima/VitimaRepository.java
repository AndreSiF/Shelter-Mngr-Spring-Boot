package com.br.csi.gda.model.vitima;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VitimaRepository extends JpaRepository<Vitima, Integer> {
    Vitima findVitimaByUuid(UUID uuid);
    void deleteVitimaByUuid(UUID uuid);
}
