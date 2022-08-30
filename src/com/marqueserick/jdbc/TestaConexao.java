package com.marqueserick.jdbc;

import com.marqueserick.jdbc.factory.ConnectionFactory;

import java.sql.*;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.criaConnection();
		System.out.println("Fechando a conexão com a base de dados...");
		con.close();
	}

}
