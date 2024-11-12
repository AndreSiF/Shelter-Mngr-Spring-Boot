package com.br.csi.gda.model.abrigo;

import com.br.csi.gda.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
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
    private UUID uuid;

    @NonNull
    @NotBlank
    @Column(name = "nome_abrigo")
    private String nome;

    @NonNull
    @NotBlank
    private String endereco;

    @NonNull
    private int vagas;

    @NonNull
    private int cadastros;

    @OneToMany(mappedBy = "abrigo")
    @JsonIgnore
    private List<Usuario> usuarios;
}
