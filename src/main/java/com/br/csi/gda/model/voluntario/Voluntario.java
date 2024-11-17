package com.br.csi.gda.model.voluntario;

import com.br.csi.gda.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Schema(description = "ID do voluntário", example = "1")
    private int id;

    @NonNull
    @NotBlank
    @Schema(description = "Endereço em que o voluntário mora", example = "Rua José Barin, 2354")
    private String endereco;
}
