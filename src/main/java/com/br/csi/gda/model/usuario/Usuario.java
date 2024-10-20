package com.br.csi.gda.model.usuario;

import com.br.csi.gda.model.usuario.Permissao;
import jakarta.persistence.*;
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
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @UuidGenerator
    private UUID uuid;

    @NonNull
    private String nome;

    @NonNull
    private String cpf;

    @NonNull
    private String data_cad;

    @NonNull
    private int idade;

    @Embedded
    @NonNull
    private Permissao permissao;

//    public Usuario(String nome, String cpf, int idade, Permissao permissao) {
//        this.nome = nome;
//        this.cpf = cpf;
//        this.idade = idade;
//        this.permissao = permissao;
//    }
}
