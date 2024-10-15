package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
    public Connection getConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/abrigochuva", "postgres", "123");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Erro em " + e.getMessage());
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            System.out.println("Erro em " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
