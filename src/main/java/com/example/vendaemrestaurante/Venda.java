package com.example.vendaemrestaurante;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private String codigo;
    private double valor;
    private Date data;
    private Time horario;

    private String pagamento;

    private List<ItemVenda> listaItemVenda = new ArrayList<>();

    public Venda(String codigo, double valor, Date data, Time horario, String pagamento, List<ItemVenda> listaItemVenda) {
        setCodigo(codigo);
        setValor(valor);
        setData(data);
        setHorario(horario);
        setPagamento(pagamento);
        setListaItemVenda(listaItemVenda);
    }

    public Venda(String codigo, double valor, Date data, Time horario, String pagamento) {
        setCodigo(codigo);
        setValor(valor);
        setData(data);
        setHorario(horario);
        setPagamento(pagamento);
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public List<ItemVenda> getListaItemVenda() {
        return listaItemVenda;
    }

    public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
        this.listaItemVenda = listaItemVenda;
    }
}
