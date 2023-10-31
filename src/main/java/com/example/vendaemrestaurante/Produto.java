package com.example.vendaemrestaurante;

import java.sql.Date;

public class Produto {

    private int id;
    private String codigo;
    private String descricao;
    private String categoria;
    private double valor;
    private Date dataValidade;

    public Produto(int id, String descricao, String codigo, double valor, String categoria, Date dataValidade) {
        setId(id);
        setDescricao(descricao);
        setCodigo(codigo);
        setValor(valor);
        setCategoria(categoria);
        setDataValidade(dataValidade);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getDataValidade() { return dataValidade; }

    public void setDataValidade(Date dataValidade) { this.dataValidade = dataValidade; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                descricao +" | " +
                "cod:" + codigo +" | "  +
                "R$" + valor;

    }
}