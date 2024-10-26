package com.br.csi.gda.model.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @UuidGenerator
    @Column(name = "uuid_usuario")
    private UUID uuid;

    @NonNull
    private String nome;

    @NonNull
    private String cpf;

    @NonNull
    private String data_cad;

    @NonNull
    private int idade;

    @NonNull
    private int perm;
}
