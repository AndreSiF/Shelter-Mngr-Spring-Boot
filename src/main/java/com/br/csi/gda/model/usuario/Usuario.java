package com.br.csi.gda.model.usuario;

import com.br.csi.gda.model.abrigo.Abrigo;
import com.br.csi.gda.model.desastre.Desastre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
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
    private Long id;

    @UuidGenerator
    @Column(name = "uuid_usuario")
    @Schema(description = "UUID do usuário", example = "b9202cea-d97f-4954-b15e-e468ca366002")
    private UUID uuid;

    @NonNull
    @NotBlank
    @Schema(description = "Nome do usuário", example = "Daniel da Silva Moura")
    private String nome;

    @NonNull
    @NotBlank
    @Size(min = 14, max = 14, message = "CPF inválido.")
    @Schema(description = "CPF do usuário", example = "123.456.789.10")
    private String cpf;

    @NonNull
    @NotBlank
    @Schema(description = "Data de cadastro do usuário", example = "11/05/2022")
    private String data_cad;

    @NonNull
    @Schema(description = "Idade do usuário", example = "24")
    private int idade;

    @ManyToOne
    @JoinColumn(name = "id_abrigo")
    private Abrigo abrigo;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "usuario_desastre", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_desastre"))
    private List<Desastre> desastresU;
}
