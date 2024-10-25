package com.br.csi.gda.model.voluntario;

import com.br.csi.gda.model.usuario.Permissao;
import com.br.csi.gda.model.usuario.Usuario;
import jakarta.persistence.*;
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
    private int id;

    @UuidGenerator
    @Column(name = "uuid_volunt")
    private UUID uuid;

    @NonNull
    private String endereco;

    public Voluntario(Usuario usuario, String endereco) {
        super(usuario.getId(), usuario.getUuid(), usuario.getNome(), usuario.getCpf(), usuario.getData_cad(), usuario.getIdade(), usuario.getPermissao());
        this.endereco = endereco;
    }

    public Voluntario(int id, String nome, String cpf, String data_cad, int idade, String endereco, Permissao permissao) {
        super(nome, cpf, data_cad, idade, permissao);
        this.endereco = endereco;
    }
}
