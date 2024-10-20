package com.br.csi.gda.model.usuario;

import jakarta.persistence.Embeddable;
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
    private String nome;
}
