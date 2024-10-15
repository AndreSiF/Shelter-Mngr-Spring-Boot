package br.csi.dao;

import br.csi.model.Permissao;
import br.csi.model.Usuario;

import java.sql.*;

public class PermissaoDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;
    public void cadastrar(Usuario permissao) {
        try (Connection cbd = new ConexaoBD().getConexao()) {
            this.sql = "INSERT INTO usuario_perm(id_usuario, id_perm) VALUES (?, ?)";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, permissao.getId());
            this.preparedStatement.setInt(2, permissao.getPermissao().getId_perm());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                System.out.println("Permissoes da vitima foi cadastrada... ");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

