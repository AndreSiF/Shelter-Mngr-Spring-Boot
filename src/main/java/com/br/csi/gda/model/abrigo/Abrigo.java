package com.br.csi.gda.model.abrigo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "abrigo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abrigo")
    private int id;

    @UuidGenerator
    @Column(name = "uuid_abrigo")
    private UUID uuid_abrigo;

    @NonNull
    @NotBlank
    private String nome;

    @NonNull
    @NotBlank
    private String endereco;

    @NonNull
    @NotBlank
    private int vagas;

    @NonNull
    @NotBlank
    private int cadastros;
}
