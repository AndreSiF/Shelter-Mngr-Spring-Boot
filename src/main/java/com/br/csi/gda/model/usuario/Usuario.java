package com.br.csi.gda.model.usuario;

import com.br.csi.gda.model.abrigo.Abrigo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String nome;

    @NonNull
    @NotBlank
    @Size(min = 14, max = 14, message = "CPF inválido.")
    private String cpf;

    @NonNull
    @NotBlank
    private String data_cad;

    @NonNull
    private int idade;

    @NonNull
    private int perm;

    @ManyToOne
    @JoinColumn(name = "id_abrigo")
    private Abrigo abrigo;
}
