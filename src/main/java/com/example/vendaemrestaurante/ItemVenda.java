package com.example.vendaemrestaurante;
import com.example.vendaemrestaurante.dao.ProdutoDAO;
import com.example.vendaemrestaurante.dao.VendaDAO;

public class ItemVenda {
    private double precoUnitario;
    private int quantidade;
    private double subtotal;
    private Produto produto;

    private Venda venda;
    public ItemVenda(double precoUnitario, int quantidade, double subtotal, int id_produto, int id_venda) {
        setPrecoUnitario(precoUnitario);
        setQuantidade(quantidade);
        setSubtotal(subtotal);
        setProduto(buscarProdutoPorId(id_produto));
        setVenda(buscarVendaPorId(id_venda));
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto buscarProdutoPorId(int id) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.recuperar(id);
    }

    public Venda buscarVendaPorId(int id){
        VendaDAO vendaDAO = new VendaDAO();
        return vendaDAO.recuperar(id);
    }


}
