package com.marqueserick.jdbc;

import com.marqueserick.jdbc.dao.CategoriaDAO;
import com.marqueserick.jdbc.factory.ConnectionFactory;
import com.marqueserick.jdbc.modelo.Categoria;
import com.marqueserick.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemCategoria {
    public static void main(String[] args) throws SQLException {

        try(Connection con = new ConnectionFactory().criaConnection()) {
            CategoriaDAO categoriaDao = new CategoriaDAO(con);
            List<Categoria> categorias = categoriaDao.listarProdutos();
            categorias.stream().map(Categoria::getNome).forEach(System.out::println);
            categorias.stream().forEach(c -> {
                for (Produto produto : c.getProdutos()){
                    System.out.printf("Produto: %s - Categoria: %s\n",produto.getDescricao(),c.getNome());
                }
            });
        }
    }
}
