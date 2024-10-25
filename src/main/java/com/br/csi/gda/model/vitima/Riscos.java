package com.br.csi.gda.model.vitima;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Riscos {
    @NonNull
    private boolean frio;

    @NonNull
    private boolean nutricao;

    @NonNull
    private boolean desidrat;

    @NonNull
    private boolean machucado;
}