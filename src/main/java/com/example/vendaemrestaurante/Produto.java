package com.example.vendaemrestaurante;

public class Produto {

    private String tipo;
    private String nome;
    private String codigo;
    private double preco;

    public Produto(String nome, String codigo, double preco, String tipo) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String nome) {
        this.tipo = nome;
    }

    @Override
    public String toString() {
        return
                nome +" | " +
                "cod:" + codigo +" | "  +
                "R$" + preco;

    }
}