package com.marqueserick.jdbc.dao;

import com.marqueserick.jdbc.modelo.Categoria;
import com.marqueserick.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final Connection con;

    public ProdutoDAO(Connection connection){
        this.con = connection;
    }

    public void salvar(Produto produto) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvarComCategoria(Produto produto)  {
        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";

        try (PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, produto.getNome());
            st.setString(2, produto.getDescricao());
            st.setInt(3, produto.getCategoriaId());

            st.execute();

            try (ResultSet rs = st.getGeneratedKeys()) {
                while (rs.next()) {
                    produto.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listar() {

            String sql = "SELECT * FROM PRODUTO";
            List<Produto> produtos = new ArrayList<>();

            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.execute();
                try (ResultSet result = st.getResultSet()) {
                    while (result.next()) {
                        Produto produto = new Produto(result.getInt("id"),
                                result.getString("nome"),
                                result.getString("descricao"));

                        produtos.add(produto);
                    }
                }
            return produtos;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listar(Categoria categoria) throws SQLException {
        String sql = "SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1,categoria.getId());
            st.execute();
            try (ResultSet result = st.getResultSet()) {
                while (result.next()) {
                    Produto produto = new Produto(result.getInt("id"),
                            result.getString("nome"),
                            result.getString("descricao"));

                    produtos.add(produto);
                }
            }
            return produtos;
        }
    }

    public void deletar(Integer id)  {
        String sql = "DELETE FROM PRODUTO WHERE ID = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String descricao, Integer id)  {
        String sql = "UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, nome.toUpperCase());
            st.setString(2, descricao.toUpperCase());
            st.setInt(3, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement st) throws SQLException {
        try (ResultSet rs = st.getResultSet()) {
            while (rs.next()) {
                Produto produto = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3));
                produtos.add(produto);
            }
        }
    }
}
