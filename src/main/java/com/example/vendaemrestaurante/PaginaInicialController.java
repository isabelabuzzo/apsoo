package com.example.vendaemrestaurante;

import com.example.vendaemrestaurante.dao.ProdutoDAO;
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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import java.net.URL;
import java.util.ResourceBundle;

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

        // Escreve metodos de pagamento na ComboBox
        ObservableList<String> options = FXCollections.observableArrayList(
                "Dinheiro",
                "Cartao",
                "Franquia"
        );

        selecPagamento.setItems(options);

        valorTotal.setText("R$"+totalAtual);

        //lista de produtos
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.recuperarTodos();
        produtosObservableList = FXCollections.observableList(produtos);
        listaProdutos.getItems().clear();
        listaProdutos.setItems(produtosObservableList);

        // relogio
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            horario.setText(sdf.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }


    @FXML
    void setMenuAlimentacao(ActionEvent actionEvent) {
        // Filtra os produtos do tipo "A"
        ObservableList<Produto> produtosTipoA = produtosObservableList.filtered(produto -> produto.getTipo().equals("Tipo A"));

        listaProdutos.setItems(produtosTipoA);
    }

    @FXML
    void setMenuBebidas(ActionEvent actionEvent) {
        // Filtra os produtos do tipo "B"
        ObservableList<Produto> produtosTipoB = produtosObservableList.filtered(produto -> produto.getTipo().equals("Tipo B"));

        listaProdutos.setItems(produtosTipoB); // Define a lista de produtos do tipo "B" como o conteúdo da lista
    }


    @FXML
    void setMenuDoces(ActionEvent actionEvent) {
        // Filtra os produtos do tipo "C"
        ObservableList<Produto> produtosTipoC = produtosObservableList.filtered(produto -> produto.getTipo().equals("Tipo C"));

        listaProdutos.setItems(produtosTipoC); // Define a lista de produtos do tipo "C" como o conteúdo da lista

    }

    @FXML
    void botaoAdicionarProdutoClicado() {
        String codigo = codigoDoProduto.getText();

        // Encontre o produto correspondente na lista de produtos
        Produto produtoSelecionado = null;
        for (Produto produto : produtosObservableList) {
            if (produto.getCodigo().equals(codigo)) {
                produtoSelecionado = produto;
                break;
            }
        }

        // Verifique se o produto foi encontrado
        if (produtoSelecionado != null) {
            double valorProduto = produtoSelecionado.getPreco();
            totalAtual += valorProduto;

            java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#0.00");
            String valorFormatado = decimalFormat.format(totalAtual);

            valorTotal.setText("R$" + valorFormatado);

            // Adicione o nome do produto à lista de produtos do pedido
            String nomeProduto = produtoSelecionado.getNome();
            ObservableList<String> produtosSelecionados = (ObservableList<String>)listaDeProdutos.getItems();
            produtosSelecionados.add(nomeProduto);
        }
    }

    @FXML
    public void botaoNovoClienteClicado(MouseEvent mouseEvent) {

        //Não sera feito cadastro

    }

    @FXML
    public void botaoFinalizarPedidoClicado(ActionEvent actionEvent) {

        // Registrar o pedido no BD

        valorTotal.setText("R$"+0);
        ObservableList<String> produtosSelecionados = (ObservableList<String>) listaDeProdutos.getItems();
        produtosSelecionados.clear();

    }
}