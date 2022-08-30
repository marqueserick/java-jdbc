import java.sql.*;

public class TestaInsertComParametro {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();

        try(Connection con = factory.criaConnection()){
            con.setAutoCommit(false);

            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?,?)";

            try (PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                adicionarVariavel("TELEVISÃO", "SMARTTV 50 POLEGADAS ANDROID TV", st);
                adicionarVariavel("TABLET", "XIAOMI MI PAD 5 PRO", st);

                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                con.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement st) throws SQLException {
        st.setString(1, nome);
        st.setString(2, descricao);
        //if(nome.equals("TABLET")) throw new RuntimeException("Não pode adicionar tablet");

        st.execute();

        try(ResultSet rs = st.getGeneratedKeys()) {
            while (rs.next()) {
                Integer id = rs.getInt(1);
                System.out.printf("O ID criado foi %d\n", id);
            }
        }
    }
}
