package com.br.csi.gda.model;

public class Voluntario extends Usuario{
    private String endereco;

    public Voluntario(Usuario usuario, String endereco) {
        super(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getData_cad(), usuario.getIdade(), usuario.getPermissao());
        this.endereco = endereco;
    }

    public Voluntario(int id, String nome, String cpf, String data_cad, int idade, String endereco, Permissao permissao) {
        super(id, nome, cpf, data_cad, idade, permissao);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
