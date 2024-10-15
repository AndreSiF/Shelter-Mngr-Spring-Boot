package com.br.csi.gda.model;

import jakarta.persistence.Embeddable;
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
