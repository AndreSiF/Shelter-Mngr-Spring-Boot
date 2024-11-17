package com.br.csi.gda.model.vitima;

import com.br.csi.gda.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Schema(description = "ID da vítima", example = "1")
    private int id;

    @NonNull
    @NotBlank
    @Column(name = "ultimo_end")
    @Schema(description = "Último endereço que a vítima morou", example = "Rua São José, 231")
    private String ultimoEnd;

    @NonNull
    @Schema(description = "Vítima presente ou não no abrigo", example = "true")
    private boolean presente;

    @NonNull
    @Schema(description = "Descrição do estado da vítima quando chegou no abrigo", example = "Saudável, não precisa de cuidados médicos")
    private String descricao;
}
