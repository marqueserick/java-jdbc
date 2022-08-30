package com.marqueserick.jdbc;


import com.marqueserick.jdbc.factory.ConnectionFactory;

import java.sql.SQLException;

public class TestaConnectionPool {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();

        for(int i = 0; i < 20 ; i++){
            factory.criaConnection();
            System.out.println("Conexão de número: "+i);

        }
    }
}
