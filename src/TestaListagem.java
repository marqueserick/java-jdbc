import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.criaConnection();

//        Statement st = con.createStatement();
//        st.execute("select * from produto");
        String sql = "SELECT * FROM PRODUTO";
        PreparedStatement st = con.prepareStatement(sql);

        st.execute();
        ResultSet retorno = st.getResultSet();

        while(retorno.next()){
            Integer id = retorno.getInt("id");
            String nome = retorno.getString("nome");
            String descricao = retorno.getString("descricao");

            System.out.printf("Produto: \nid: %d - %s - Descrição: %s \n\n", id, nome, descricao);

        }
        con.close();
    }
}
