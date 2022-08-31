package com.marqueserick.jdbc;

import com.marqueserick.jdbc.dao.ProdutoDAO;
import com.marqueserick.jdbc.factory.ConnectionFactory;
import com.marqueserick.jdbc.modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsertSelectProduto {
    public static void main(String[] args) throws SQLException {
        Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

        try(Connection con = new ConnectionFactory().criaConnection()){
            ProdutoDAO produtoDao = new ProdutoDAO(con);
            produtoDao.salvar(comoda);
            List<Produto> produtos = produtoDao.listar();
            produtos.stream().forEach(System.out::println);
        }
    }
}
