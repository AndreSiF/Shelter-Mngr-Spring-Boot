package com.br.csi.gda.model.voluntario;

import com.br.csi.gda.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "voluntario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voluntario extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_volunt")
    private int id;

    @UuidGenerator
    @Column(name = "uuid_volunt")
    private UUID uuid_volunt;

    @NonNull
    private String endereco;
}
