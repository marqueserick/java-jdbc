package com.marqueserick.jdbc.controller;

import com.marqueserick.jdbc.dao.CategoriaDAO;
import com.marqueserick.jdbc.factory.ConnectionFactory;
import com.marqueserick.jdbc.modelo.Categoria;

import java.sql.Connection;
import java.util.List;

public class CategoriaController {
    private final CategoriaDAO categoriaDao;

    public CategoriaController(){
        Connection con = new ConnectionFactory().criaConnection();
        categoriaDao = new CategoriaDAO(con);
    }
    public List<Categoria> listar() {
        return categoriaDao.listar();
    }
}
