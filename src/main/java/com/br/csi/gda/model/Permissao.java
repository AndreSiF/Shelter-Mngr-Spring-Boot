package com.br.csi.gda.model;

public class Permissao {
    private int id_perm;
    private String nome;

    public Permissao(int id_perm, String nome) {
        this.id_perm = id_perm;
        this.nome = nome;
    }

    public int getId_perm() {
        return id_perm;
    }

    public void setId_perm(int id_perm) {
        this.id_perm = id_perm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
