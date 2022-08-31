package com.marqueserick.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    Integer id;
    String nome;
    List<Produto> produtos = new ArrayList<>();

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void adicionarProduto(Produto produto){
        this.produtos.add(produto);
    }
    public List<Produto> getProdutos(){
        return produtos;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
