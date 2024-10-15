package br.csi.dao;

import br.csi.model.Permissao;
import br.csi.model.Riscos;
import br.csi.model.Usuario;
import br.csi.model.Vitima;

import java.sql.*;
import java.util.ArrayList;

public class VitimaDAO{
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Vitima> getVitimas(){
//        FUNÇÃO QUE RETORNA TODAS AS VÍTIMAS
        ArrayList<Vitima> vitimas = new ArrayList<>();
        try(Connection cbd = new ConexaoBD().getConexao()){
            this.sql = "SELECT * FROM usuario us, vitima vi, usuario_perm up, perm pe, riscos ri WHERE vi.id_usuario = us.id_usuario AND us.id_usuario = up.id_usuario AND up.id_perm = pe.id_perm AND ri.id_vitima = vi.id_vitima;";
            this.statement = cbd.createStatement();
            this.resultSet = this.statement.executeQuery(sql);
            while(this.resultSet.next()){
                Riscos riscos = new Riscos(this.resultSet.getBoolean("frio"), this.resultSet.getBoolean("nutricao"), this.resultSet.getBoolean("desidrat"), this.resultSet.getBoolean("machucado"));
                Permissao permissao = new Permissao(this.resultSet.getInt("id_perm"), this.resultSet.getString("nome_perm"));
                Usuario u = new Usuario(this.resultSet.getInt("id_usuario"), this.resultSet.getString("nome"), this.resultSet.getString("cpf"), this.resultSet.getString("data_cad"), this.resultSet.getInt("idade"), permissao);
                Vitima v = new Vitima(u, riscos, this.resultSet.getString("ultimo_end"), this.resultSet.getBoolean("presente"));
                vitimas.add(v);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            this.status = "error";
        }
        return vitimas;
    }

    public String cadastrar(Vitima vitima) {
        try(Connection cbd = new ConexaoBD().getConexao()){
            cbd.setAutoCommit(false);
//            CADASTRANDO USUARIO
            vitima.setId(new UsuarioDAO().cadastrar(vitima));
//            INSERINDO A VITIMA
            this.sql = "INSERT INTO vitima(id_usuario, ultimo_end, presente) VALUES(?, ?, ?)";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, vitima.getId());
            this.preparedStatement.setString(2, vitima.getUltimoEnd());
            this.preparedStatement.setBoolean(3, vitima.isPresente());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OK";
                System.out.println("Vitima foi cadastrada... ");
            }

//            INSERINDO A PERMISSAO DO USUARIO COMO VITIMA
            this.sql = "INSERT INTO usuario_perm(id_usuario, id_perm) VALUES (?, ?)";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, vitima.getId());
            this.preparedStatement.setInt(2, vitima.getPermissao().getId_perm());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OK";
                System.out.println("Permissoes da vitima foi cadastrada... ");
            }

//            PEGANDO O ID DA VITIMA
            this.sql = "SELECT * FROM VITIMA WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, vitima.getId());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();
            vitima.setId(this.resultSet.getInt("id_vitima"));

//            INSERINDO O ESTADO DA VITIMA CADASTRADA
            this.sql = "INSERT INTO riscos(id_vitima, frio, nutricao, desidrat, machucado) VALUES(?, ?, ?, ?, ?)";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, vitima.getId());
            this.preparedStatement.setBoolean(2, vitima.getRiscos().isFrio());
            this.preparedStatement.setBoolean(3, vitima.getRiscos().isNutricao());
            this.preparedStatement.setBoolean(4, vitima.getRiscos().isDesidrat());
            this.preparedStatement.setBoolean(5, vitima.getRiscos().isMachucado());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OK";
                System.out.println("Estado da vitima foi cadastrado... ");
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
        try (Connection cbd = new ConexaoBD().getConexao()) {
            cbd.setAutoCommit(false);
//            PEGANDO ID DA VITIMA
            this.sql = "SELECT * FROM vitima WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idInt);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getResultSet();
            this.resultSet.next();
            int idVitima = this.resultSet.getInt("id_vitima");

//            DELETANDO OS RISCOS DA VITIMA
            this.sql = "DELETE FROM riscos WHERE id_vitima = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idVitima);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "DEL";
                System.out.println("Riscos removidos...");
            }

//            DELETANDO A VITIMA
            this.sql = "DELETE FROM vitima WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idInt);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "DEL";
                System.out.println("Vitima removida...");
            }

//            DELETANDO PERMISSOES DO USUARIO
            this.sql = "DELETE FROM usuario_perm WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idInt);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "DEL";
                System.out.println("Permissões Removidas...");
            }

//            REMOVENDO USUARIO
            this.sql = "DELETE FROM usuario WHERE id_usuario = ?";
            this.preparedStatement = cbd.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, idInt);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
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
