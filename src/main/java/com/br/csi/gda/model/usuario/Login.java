package com.br.csi.gda.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "login")
@Table(name = "login")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    @Schema(description = "Email para login do usuário", example = "umemail@kmail.com")
    private String login;

    @NotNull
    @Schema(description = "Senha o usuário", example = "12456789")
    private String senha;

    @Schema(description = "Permissão do usuário", example = "ROLE_VITIMA")
    @NotNull
    private String permissao;
}
