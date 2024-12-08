package com.br.csi.gda.model.abrigo;

import com.br.csi.gda.model.desastre.Desastre;
import com.br.csi.gda.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "ID do abrigo", example = "1")
    private Long id;

    @UuidGenerator
    @Column(name = "uuid_abrigo")
    @Schema(description = "UUID do abrigo", example = "b9202cea-d97f-4954-b15e-e468ca366002")
    private UUID uuid;

    @NonNull
    @NotBlank
    @Column(name = "nome_abrigo")
    @Schema(description = "Nome do abrigo", example = "Hospital Caridade")
    private String nome;

    @NonNull
    @NotBlank
    @Schema(description = "Endereço completo do abrigo", example = "Rua Daniel Marquês, 2064")
    private String endereco;

    @NonNull
    @Schema(description = "Número de vagas disponíveis no abrigo", example = "100")
    private int vagas;

    @NonNull
    @Schema(description = "Número de cadastros no abrigo", example = "10")
    private int cadastros;

    @OneToMany(mappedBy = "abrigo")
    @JsonIgnore
    private List<Usuario> usuarios;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "abrigo_desastre",
            joinColumns = @JoinColumn(name = "id_abrigo"),
            inverseJoinColumns = @JoinColumn(name = "id_desastre")
    )
    private List<Desastre> desastresA;
}
