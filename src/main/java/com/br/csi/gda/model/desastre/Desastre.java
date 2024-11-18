package com.br.csi.gda.model.desastre;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public class Desastre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desastre")
    private int id;

    @UuidGenerator
    @Column(name = "uuid_desastre")
    @Schema(description = "UUID do desastre", example = "b9202cea-d97f-4954-b15e-e468ca366002")
    private UUID uuid_desastre;

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
}
