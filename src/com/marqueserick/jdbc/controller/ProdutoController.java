package com.marqueserick.jdbc.controller;

import com.marqueserick.jdbc.dao.ProdutoDAO;
import com.marqueserick.jdbc.factory.ConnectionFactory;
import com.marqueserick.jdbc.modelo.Produto;

import java.sql.Connection;
import java.util.List;

public class ProdutoController {
    private final ProdutoDAO produtoDao;

    public ProdutoController() {
        Connection con = new ConnectionFactory().criaConnection();
        produtoDao = new ProdutoDAO(con);

    }

    public void deletar(Integer id) {
        produtoDao.deletar(id);
    }

    public void salvar(Produto produto) {
        produtoDao.salvar(produto);
    }

    public List<Produto> listar() {
        return produtoDao.listar();
    }

    public void alterar(String nome, String descricao, Integer id) {
        produtoDao.alterar(nome, descricao, id);
    }
}
