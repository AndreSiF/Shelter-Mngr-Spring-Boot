package com.br.csi.gda.model.usuario;

public record DadosUsuario(Long id, String login, String permissao) {
    public DadosUsuario(Login usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getPermissao());
    }
}
