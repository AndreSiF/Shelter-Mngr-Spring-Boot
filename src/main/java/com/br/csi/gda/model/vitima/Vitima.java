package com.br.csi.gda.model.vitima;

import com.br.csi.gda.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "vitima")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vitima extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vitima")
    private int id;

    @UuidGenerator
    @Column(name = "uuid_vitima")
    private UUID uuid;

    @NonNull
    @Column(name = "ultimo_end")
    private String ultimoEnd;

    @NonNull
    private boolean presente;

    @NonNull
    private String descricao;
}
