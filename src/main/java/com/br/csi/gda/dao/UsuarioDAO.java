package com.br.csi.gda.dao;

import com.br.csi.gda.model.Usuario;

import java.sql.*;

public class UsuarioDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public int cadastrar(Usuario usuario) {
        try (Connection cbd = new ConexaoBD().getConexao()) {
//            CADASTRANDO USUARIO
            this.sql = "INSERT INTO usuario(nome, cpf, data_cad, idade) VALUES(?, ?, CURRENT_DATE, ?)";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, usuario.getNome());
            this.preparedStatement.setString(2, usuario.getCpf());
            this.preparedStatement.setInt(3, usuario.getIdade());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(1) > 0) {
                usuario.setId(this.resultSet.getInt(1));
                System.out.println(usuario.getId());
                System.out.println("Usuário foi inserido... ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario.getId();
    }
}
