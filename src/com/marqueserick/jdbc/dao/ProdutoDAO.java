package com.marqueserick.jdbc.dao;

import com.marqueserick.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final Connection con;

    public ProdutoDAO(Connection connection){
        this.con = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?,?)";

        try(PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setString(1, produto.getNome().toUpperCase());
            st.setString(2, produto.getDescricao().toUpperCase());

            st.execute();

            try(ResultSet result = st.getGeneratedKeys()){
                while(result.next()){
                    produto.setId(result.getInt(1));
                }
            }
        }

        //return produto;

    }

    public List<Produto> listar() throws SQLException {
        String sql = "SELECT * FROM PRODUTO";
        List<Produto> produtos = new ArrayList<>();

        try(PreparedStatement st = con.prepareStatement(sql)){
            st.execute();
            try(ResultSet result = st.getResultSet()){
                while (result.next()){
                    Produto produto = new Produto(result.getInt("id"),
                                    result.getString("nome"),
                                    result.getString("descricao"));

                    produtos.add(produto);
                }
            }
        }


        return produtos;
    }
}
