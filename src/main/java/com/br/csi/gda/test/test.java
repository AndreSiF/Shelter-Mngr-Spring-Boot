package com.br.csi.gda.test;

import com.br.csi.gda.dao.UsuarioDAO;
import com.br.csi.gda.model.Permissao;
import com.br.csi.gda.model.Usuario;

public class test {
    public static void main(String[] args) {
        Permissao permissao = new Permissao(3, "VOLUNTARIO");
        Usuario usuario = new Usuario("Kaue", "173.486.789-10", 43, permissao);
        new UsuarioDAO().cadastrar(usuario);
    }
}