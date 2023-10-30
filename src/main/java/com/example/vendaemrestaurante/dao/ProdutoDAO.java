package com.example.vendaemrestaurante.dao;

import com.example.vendaemrestaurante.database.connection.Conexao;
import com.example.vendaemrestaurante.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements DAO<Produto, Integer>{
    @Override
    public Produto inserir(Produto produto) {
        return null;
    }

    @Override
    public void atualizar(Produto produto) {

    }

    @Override
    public Produto recuperar(Integer produto) {
        return null;
    }

    @Override
    public List<Produto> recuperarTodos() {
        Connection conexao = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            conexao = Conexao.abreConexao();
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM Produto");

            while(resultado.next()) {
                Produto produto = new Produto(resultado.getString("nome"), resultado.getString("id"), resultado.getDouble("preco"), resultado.getString("tipo"));

                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return produtos;
    }

    @Override
    public void remover(Integer produtoID) {

    }
}
