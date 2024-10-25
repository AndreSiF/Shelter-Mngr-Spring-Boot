package com.br.csi.gda.model.vitima;

import com.br.csi.gda.model.usuario.Permissao;
import com.br.csi.gda.model.usuario.Usuario;
import jakarta.persistence.*;
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
    private int id;

    @UuidGenerator
    @Column(name = "uuid_vitima")
    private UUID uuid;

    @NonNull
    private String ultimoEnd;

    @NonNull
    private boolean presente;

    @NonNull
    @Embedded
    private Riscos riscos;

    public Vitima(Usuario usuario, Riscos riscos, String ultimoEnd, boolean presente) {
        super(usuario.getId(), usuario.getUuid(), usuario.getNome(), usuario.getCpf(), usuario.getData_cad(), usuario.getIdade(), usuario.getPermissao());
        this.ultimoEnd = ultimoEnd;
        this.presente = presente;
        this.riscos = riscos;
    }

    public Vitima(String nome, String cpf, String data, int idade, Permissao permissao, String ultimoEnd, boolean presente) {
        super(nome, cpf, data, idade, permissao);
        this.ultimoEnd = ultimoEnd;
        this.presente = presente;
    }
}
