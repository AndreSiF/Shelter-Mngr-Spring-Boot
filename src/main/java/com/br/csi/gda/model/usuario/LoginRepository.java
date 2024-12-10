package com.br.csi.gda.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    public Login findByLogin (String login);
}
