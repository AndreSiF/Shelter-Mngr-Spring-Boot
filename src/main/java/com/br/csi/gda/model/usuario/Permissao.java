package com.br.csi.gda.model.usuario;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permissao {
    @NonNull
    private int id_perm;

    @NonNull
    private String nomePerm;
}
