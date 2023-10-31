package com.example.vendaemrestaurante.dao;

import com.example.vendaemrestaurante.database.connection.Conexao;
import com.example.vendaemrestaurante.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO implements DAO<Venda, Integer> {
    @Override
    public Venda inserir(Venda venda) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;

        try {
            conexao = Conexao.abreConexao();

            String sql = "INSERT INTO Venda (codigo, valor, data, horario, pagamento) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, venda.getCodigo());
            stmt.setDouble(2, venda.getValor());
            stmt.setDate(3, venda.getData());
            stmt.setTime(4, venda.getHorario());
            stmt.setString(5, venda.getPagamento());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("A inserção falhou, nenhum registro foi inserido.");
            }

            resultado = stmt.getGeneratedKeys();

            if (resultado.next()) {
                int idGerado = resultado.getInt(1);
                venda.setId(idGerado);
            } else {
                throw new SQLException("A inserção falhou, nenhum ID foi gerado.");
            }

            return venda;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a venda.", e);
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public void atualizar(Venda venda) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.abreConexao();

            String sql = "UPDATE Venda SET codigo = ?, valor = ?, data = ?, horario = ? WHERE id = ?";
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, venda.getCodigo());
            stmt.setDouble(2, venda.getValor());
            stmt.setDate(3, venda.getData());
            stmt.setTime(4, venda.getHorario());
            stmt.setInt(5, venda.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("A atualização falhou, nenhum registro foi atualizado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a venda.", e);
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public Venda recuperar(Integer vendaID) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        Venda venda = null;

        try {
            conexao = Conexao.abreConexao();

            String sql = "SELECT * FROM Venda WHERE id = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, vendaID);

            resultado = stmt.executeQuery();

            if (resultado.next()) {

                venda = new Venda(
                        resultado.getString("codigo"),
                        resultado.getDouble("valor"),
                        resultado.getDate("data"),
                        resultado.getTime("horario"),
                        resultado.getString("pagamento")
                );
                venda.setId(vendaID);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar a venda.", e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return venda;
    }


    @Override
    public List<Venda> recuperarTodos() {
        Connection conexao = null;
        List<Venda> vendas = new ArrayList<>();

        try {
            conexao = Conexao.abreConexao();
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM Venda");

            while (resultado.next()) {
                Venda venda = new Venda(resultado.getString("codigo"), resultado.getDouble("valor"), resultado.getDate("data"), resultado.getTime("horario"), resultado.getString("pagamento"));

                vendas.add(venda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return vendas;
    }

    @Override
    public void remover(Integer vendaID) {
        // Implementar
    }
}

