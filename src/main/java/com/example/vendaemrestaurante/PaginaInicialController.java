package com.example.vendaemrestaurante;

import com.example.vendaemrestaurante.dao.ItemVendaDAO;
import com.example.vendaemrestaurante.dao.ProdutoDAO;
import com.example.vendaemrestaurante.dao.VendaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.net.NetworkInterface;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import java.net.URL;

public class PaginaInicialController implements Initializable {

    @FXML
    private Label horario;

    @FXML
    private ListView<Produto> listaProdutos;

    @FXML
    private ComboBox<String> selecPagamento;

    @FXML
    private TextField codigoDoProduto;

    @FXML
    private ListView<?> listaDeProdutos;

    @FXML
    private Label valorTotal;

    double totalAtual = 0;

    ObservableList<Produto> produtosObservableList;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Métodos de pagamento
        ObservableList<String> options = FXCollections.observableArrayList(
                "Dinheiro",
                "Cartao",
                "Franquia"
        );

        selecPagamento.setItems(options);

        valorTotal.setText("R$"+totalAtual);

        // Lista de produtos
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.recuperarTodos();
        produtosObservableList = FXCollections.observableList(produtos);
        listaProdutos.getItems().clear();
        listaProdutos.setItems(produtosObservableList);

        // Relogio
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //    horario.setText(sdf.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    @FXML
    void setMenuAlimentacao(ActionEvent actionEvent) {
        // Filtra os produtos do tipo "A"
        ObservableList<Produto> produtosTipoA = produtosObservableList.filtered(produto -> produto.getCategoria().equals("Tipo A"));

        listaProdutos.setItems(produtosTipoA);
    }

    @FXML
    void setMenuBebidas(ActionEvent actionEvent) {
        // Filtra os produtos do tipo "B"
        ObservableList<Produto> produtosTipoB = produtosObservableList.filtered(produto -> produto.getCategoria().equals("Tipo B"));

        listaProdutos.setItems(produtosTipoB); // Define a lista de produtos do tipo "B" como o conteúdo da lista
    }

    @FXML
    void setMenuDoces(ActionEvent actionEvent) {
        // Filtra os produtos do tipo "C"
        ObservableList<Produto> produtosTipoC = produtosObservableList.filtered(produto -> produto.getCategoria().equals("Tipo C"));

        listaProdutos.setItems(produtosTipoC); // Define a lista de produtos do tipo "C" como o conteúdo da lista

    }

    @FXML
    void botaoAdicionarProdutoClicado() {
        String codigo = codigoDoProduto.getText();

        // Encontra o produto correspondente na lista de produtos
        Produto produtoSelecionado = null;
        for (Produto produto : produtosObservableList) {
            if (produto.getCodigo().equals(codigo)) {
                produtoSelecionado = produto;
                break;
            }
        }

        // Verifica se o produto foi encontrado
        if (produtoSelecionado != null) {
            double valorProduto = produtoSelecionado.getValor();
            totalAtual += valorProduto;

            java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#0.00");
            String valorFormatado = decimalFormat.format(totalAtual);

            valorTotal.setText("R$" + valorFormatado);

            // Adiciona o nome do produto à lista de produtos do pedido
            String nomeProduto = produtoSelecionado.getDescricao();
            ObservableList<String> produtosSelecionados = (ObservableList<String>)listaDeProdutos.getItems();
            produtosSelecionados.add(nomeProduto);
        }


    }

    @FXML
    public void botaoNovoClienteClicado(MouseEvent mouseEvent) {

        // Não será feito cadastro na primeira iteração

    }

    @FXML
    public void botaoFinalizarPedidoClicado(ActionEvent actionEvent) {
        List<String> produtoNomes = (List<String>)listaDeProdutos.getItems();
        List<ItemVenda> itensVenda = new ArrayList<>();
        double valorTotalVenda = 0.0;

        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000); // Gera um número aleatório entre 0 e 999
        String codigoVenda = String.valueOf(numeroAleatorio);

        Venda venda = new Venda(codigoVenda, valorTotalVenda, new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), selecPagamento.getValue(), itensVenda);
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.inserir(venda);

        // Pega os produtos pelo nome no banco de dados
        ProdutoDAO produtoDAO = new ProdutoDAO();

        for (String nomeProduto : produtoNomes) {
            Produto produtoNoBanco = produtoDAO.buscarProdutoPorNome(nomeProduto);

            if (produtoNoBanco != null) {
                int idProduto = produtoNoBanco.getId();
                int quantidade = 1;

                // Calcula preço unitário e subtotal
                double precoUnitario = produtoNoBanco.getValor();
                double subtotal = precoUnitario * quantidade;

                // Salva o produto com suas informações
                itensVenda.add(new ItemVenda(precoUnitario, quantidade, subtotal, idProduto, venda.getId()));

                valorTotalVenda += subtotal;
            }
        }

        // Atualiza a Venda no BD com os subtotais todos somados
        venda.setValor(valorTotalVenda);
        venda.setListaItemVenda(itensVenda);

        vendaDAO.atualizar(venda);

        // Criar todos os ItemVenda no BD com todos os valores que foram salvos anteriormente
        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

        for (ItemVenda itemVenda : itensVenda) {
            itemVendaDAO.inserir(itemVenda);
        }

        // Limpa os valores na tela
        valorTotal.setText("R$0.00");
        totalAtual = 0.00;
        valorTotalVenda = 0.00;

        // Limpa a lista de produtos selecionados
        produtoNomes.clear();
    }



}

