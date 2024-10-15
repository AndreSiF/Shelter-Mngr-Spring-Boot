package br.csi.dao;

import br.csi.model.*;

import java.sql.*;
import java.util.ArrayList;

public class VoluntarioDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Voluntario> getVoluntarios(){
//        FUNÇÃO QUE RETONA TODOS OS VOLUNTÁRIOS
        ArrayList<Voluntario> voluntarios = new ArrayList<>();
        try(Connection cbd = new ConexaoBD().getConexao()){
            this.sql = "SELECT * FROM usuario us, voluntario vo, usuario_perm up, perm pe WHERE vo.id_usuario = us.id_usuario AND us.id_usuario = up.id_usuario AND up.id_perm = pe.id_perm;";
            this.statement = cbd.createStatement();
            this.resultSet = this.statement.executeQuery(sql);
            while(this.resultSet.next()){
                Permissao permissao = new Permissao(this.resultSet.getInt("id_perm"), this.resultSet.getString("nome_perm"));
                Usuario u = new Usuario(this.resultSet.getInt("id_usuario"), this.resultSet.getString("nome"), this.resultSet.getString("cpf"), this.resultSet.getString("data_cad"), this.resultSet.getInt("idade"), permissao);
                Voluntario v = new Voluntario(u, this.resultSet.getString("endereco"));
                voluntarios.add(v);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            this.status = "error";
        }
        return voluntarios;
    }

    public String cadastrar(Voluntario voluntario) {
        try(Connection cbd = new ConexaoBD().getConexao()){
            cbd.setAutoCommit(false);
//            CADASTRANDO USUARIO
            voluntario.setId(new UsuarioDAO().cadastrar(voluntario));
//            PEGADO O ID DO USUARIO
            this.sql = "SELECT * FROM usuario WHERE cpf = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, voluntario.getCpf());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();
//            CHAVE ESTRANGEIRA GUARDADA NO ID DO VOLUNTARIO
            voluntario.setId(this.resultSet.getInt("id_usuario"));

//            INSERINDO O VOLUNTARIO
            this.sql = "INSERT INTO voluntario(id_usuario, endereco) VALUES(?, ?)";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, voluntario.getId());
            this.preparedStatement.setString(2, voluntario.getEndereco());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OK";
                System.out.println("Voluntário foi cadastrado... ");
            }

//            INSERINDO A PERMISSAO DO USUARIO COMO VOLUNTARIO
            this.sql = "INSERT INTO usuario_perm(id_usuario, id_perm) VALUES (?, ?)";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, voluntario.getId());
            this.preparedStatement.setInt(2, voluntario.getPermissao().getId_perm());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OK";
                System.out.println("Permissoes do voluntário foi cadastrada... ");
            }
            cbd.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            this.status = "error";
        }
        return this.status;
    }

    public String excluir(String id) {
        int idInt = Integer.parseInt(id);
        int linhasAfetadas;
        try (Connection cbd = new ConexaoBD().getConexao()) {
            cbd.setAutoCommit(false);

//            DELETANDO O VOLUNTARIO
            this.sql = "DELETE FROM voluntario WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idInt);
            linhasAfetadas = this.preparedStatement.executeUpdate();

            if(linhasAfetadas > 0){
                this.status = "DEL";
                System.out.println("Voluntário removido...");
            }

//            DELETANDO PERMISSOES DO USUARIO
            this.sql = "DELETE FROM usuario_perm WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idInt);
            linhasAfetadas = this.preparedStatement.executeUpdate();

            if(linhasAfetadas > 0){
                this.status = "DEL";
                System.out.println("Permissões Removidas...");
            }

//            REMOVENDO USUARIO
            this.sql = "DELETE FROM usuario WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idInt);
            linhasAfetadas = this.preparedStatement.executeUpdate();

            if(linhasAfetadas > 0){
                this.status = "DEL";
                System.out.println("Usuário removido...");
            }
            cbd.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            this.status = "error";
        }
        return this.status;
    }
}
