package com.example.vendaemrestaurante.dao;

import com.example.vendaemrestaurante.database.connection.Conexao;
import com.example.vendaemrestaurante.Produto;

import java.sql.*;
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
    public Produto recuperar(Integer produtoID) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;

        try {
            conexao = Conexao.abreConexao();
            stmt = conexao.prepareStatement("SELECT * FROM Produto WHERE id = ?");
            stmt.setInt(1, produtoID);
            resultado = stmt.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String codigo = resultado.getString("codigo");
                String descricao = resultado.getString("descricao");
                String categoria = resultado.getString("categoria");
                double valor = resultado.getDouble("valor");
                Date dataValidade = resultado.getDate("dataValidade");

                Produto produto = new Produto(id, descricao, codigo, valor, categoria, dataValidade);
                return produto;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

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
                Produto produto = new Produto(resultado.getInt("id"), resultado.getString("descricao"), resultado.getString("codigo"), resultado.getDouble("valor"), resultado.getString("categoria"), resultado.getDate("dataValidade"));

                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return produtos;
    }

    public Produto buscarProdutoPorNome(String nomeProduto) {
        Connection conexao = null;
        Produto produto = null;

        try {
            conexao = Conexao.abreConexao();
            String query = "SELECT * FROM Produto WHERE descricao = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nomeProduto);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                produto = new Produto(
                        resultado.getInt("id"),
                        resultado.getString("descricao"),
                        resultado.getString("codigo"),
                        resultado.getDouble("valor"),
                        resultado.getString("categoria"),
                        resultado.getDate("dataValidade")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por nome: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return produto;
    }


    @Override
    public void remover(Integer produtoID) {
        // Implementar
    }
}
