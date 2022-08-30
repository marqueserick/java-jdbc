import java.sql.*;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {

		Connection con = ConnectionFactory.criaConnection();
		System.out.println("Fechando a conexão com a base de dados...");
		con.close();
	}

}
