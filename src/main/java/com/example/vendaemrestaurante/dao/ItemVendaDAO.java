package com.example.vendaemrestaurante.dao;

import com.example.vendaemrestaurante.ItemVenda;
import com.example.vendaemrestaurante.database.connection.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaDAO implements DAO<ItemVenda, Integer> {
    @Override
    public ItemVenda inserir(ItemVenda itemVenda) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.abreConexao();
            stmt = conexao.prepareStatement("INSERT INTO ItemVenda (precoUnitario, quantidade, subtotal, venda_id, produto_id) VALUES (?, ?, ?, ?, ?)");
            stmt.setDouble(1, itemVenda.getPrecoUnitario());
            stmt.setInt(2, itemVenda.getQuantidade());
            stmt.setDouble(3, itemVenda.getSubtotal());
            stmt.setDouble(4, itemVenda.getVenda().getId());
            stmt.setInt(5, itemVenda.getProduto().getId());
            stmt.executeUpdate();

            return itemVenda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public void atualizar(ItemVenda itemVenda) {
        // Implementar
    }

    @Override
    public ItemVenda recuperar(Integer itemVendaID) {
     return null;
    }

    @Override
    public List<ItemVenda> recuperarTodos() {
        Connection conexao = null;
        List<ItemVenda> itensVenda = new ArrayList<>();

        try {
            conexao = Conexao.abreConexao();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM ItemVenda");
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ItemVenda itemVenda = new ItemVenda(resultado.getDouble("precoUnitario"), resultado.getInt("quantidade"), resultado.getDouble("subtotal"), resultado.getInt("id_produto"), resultado.getInt("id_venda"));
                itensVenda.add(itemVenda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return itensVenda;
    }

    @Override
    public void remover(Integer itemVendaID) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.abreConexao();
            stmt = conexao.prepareStatement("DELETE FROM ItemVenda WHERE id = ?");
            stmt.setInt(1, itemVendaID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
}

