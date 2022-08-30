package com.marqueserick.jdbc;

import com.marqueserick.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaDelete {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.criaConnection();

//        Statement st = con.createStatement();
//        st.execute("delete from produto where id>2");
        String sql = "DELETE FROM PRODUTO WHERE ID > ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1,2);
        st.execute();
        Integer rowsUpdated = st.getUpdateCount();
        System.out.printf(respostaAtualizacao(rowsUpdated), rowsUpdated);
        con.close();
    }

    private static String respostaAtualizacao(Integer rowsUpdated) {
        return "Fo"+ ((rowsUpdated!=1) ? "ram" : "i")
                +" modificada"+((rowsUpdated!=1) ? "s" : "")
                +" %d linha" + ((rowsUpdated!=1) ? "s" : "");
    }
}
