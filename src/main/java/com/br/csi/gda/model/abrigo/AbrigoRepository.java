package com.br.csi.gda.model.abrigo;

import com.br.csi.gda.model.vitima.Vitima;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbrigoRepository extends JpaRepository <Abrigo, Integer> {
    Abrigo findAbrigoByUuid(UUID uuid);
    void deleteAbrigoByUuid(UUID uuid);
}
