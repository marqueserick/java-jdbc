package com.marqueserick.jdbc.dao;

import com.marqueserick.jdbc.modelo.Categoria;
import com.marqueserick.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {


    private final Connection con;

    public CategoriaDAO(Connection connection){
        this.con = connection;
    }

    public List<Categoria> listar() {
        try {
            List<Categoria> categorias = new ArrayList();
            String sql = "SELECT * FROM CATEGORIA";

            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.execute();
                try (ResultSet rs = st.getResultSet()) {
                    while (rs.next()) {
                        Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));
                        categorias.add(categoria);
                    }
                }
            }
            return categorias;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }

    public List<Categoria> listarProdutos() throws SQLException {
        List<Categoria> categorias = new ArrayList();
        Categoria ultima = null;
        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN " +
                "PRODUTO P ON C.ID = P.CATEGORIA_ID";

        try(PreparedStatement st = con.prepareStatement(sql)){
            st.execute();
            try(ResultSet rs = st.getResultSet()){
                while(rs.next()){
                    String nomeCategoria = rs.getString(2);
                    if(ultima == null || !ultima.getNome().equals(nomeCategoria)){
                        Categoria categoria = new Categoria(rs.getInt(1), nomeCategoria);
                        ultima = categoria;
                        categorias.add(categoria);
                    }
                    Produto produto = new Produto(rs.getInt(3),rs.getString(4),rs.getString(5));
                    ultima.adicionarProduto(produto);

                }
            }
        }

        return categorias;
    }
}
