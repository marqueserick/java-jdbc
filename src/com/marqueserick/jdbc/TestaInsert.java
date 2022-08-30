package com.marqueserick.jdbc;

import com.marqueserick.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsert {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.criaConnection();

        Statement st = con.createStatement();
        st.execute("insert into produto (nome, descricao) values ('MOUSE','MOUSE SEM FIO')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = st.getGeneratedKeys();
        while (rs.next()){
            Integer id = rs.getInt(1);
            System.out.printf("O ID criado foi %d", id);
        }

        con.close();
    }
}
