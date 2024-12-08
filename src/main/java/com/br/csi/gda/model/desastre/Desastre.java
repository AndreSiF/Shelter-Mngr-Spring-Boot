package com.br.csi.gda.model.desastre;

import com.br.csi.gda.model.abrigo.Abrigo;
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
@Table(name = "desastre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Desastre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desastre")
    private Long id;

    @UuidGenerator
    @Column(name = "uuid_desastre")
    @Schema(description = "UUID do desastre", example = "b9202cea-d97f-4954-b15e-e468ca366002")
    private UUID uuid;

    @NonNull
    @NotBlank
    @Schema(description = "Nome do desastre", example = "Enchente de 2024")
    private String nome;

    @NonNull
    @NotBlank
    @Schema(description = "Data do acontecimento do desastre", example = "11/05/2022")
    private String data;

    @NonNull
    @NotBlank
    @Schema(description = "Informações do desastre", example = "Excesso de chuvas causou destruição de moradias em grande escala...")
    private String descricao;

    @ManyToMany(mappedBy = "desastresA")
    @JsonIgnore
    private List<Abrigo> abrigos;

    @ManyToMany(mappedBy = "desastresU")
    @JsonIgnore
    private List<Usuario> usuarios;
}
