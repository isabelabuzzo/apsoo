package com.example.vendaemrestaurante;

//import com.feijaopreto.cadastroclientes.database.dao.EstadoDAO;
//import com.feijaopreto.cadastroclientes.models.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.net.URL;
import java.util.ResourceBundle;

public class PaginaInicialController implements Initializable {

    @FXML
    private Button botaoAliment;

    @FXML
    private Button botaoBebida;

    @FXML
    private Button botaoDoce;

    @FXML
    private Button botaoFinalizarPedido;

    @FXML
    private Button botaoNovoCliente;

    @FXML
    private Label horario;

    @FXML
    private ListView<Produto> listaProdutos;

    @FXML
    private Label nomeFuncionario;

    @FXML
    private Label nomeRestaurante;

    @FXML
    private ScrollPane rootPane;

    @FXML
    private ComboBox<?> selecCodCliente;

    @FXML
    private ComboBox<String> selecPagamento;

    @FXML
    private TextField codigoDoProduto;

    @FXML
    private Button botaoAdicionaProduto;

    @FXML
    private ListView<?> listaDeProdutos;

    @FXML
    private Label valorTotal;


    double totalAtual = 0;

    private ObservableList<Produto> produtosObservableList;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // escreve metodos de pagamento na combo box
        ObservableList<String> options = FXCollections.observableArrayList(
                "Dinheiro",
                "Cartao",
                "Franquia"
        );

        selecPagamento.setItems(options);

        // valor total eh zero quando inicia
        valorTotal.setText("R$"+0);

        //lista de testes
        ArrayList<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Produto 1", "12345", 10.0, "Tipo A"));
        produtos.add(new Produto("Produto 11", "11115", 12.0, "Tipo A"));
        produtos.add(new Produto("Produto 2", "67890", 20.0, "Tipo B"));
        produtos.add(new Produto("Produto 3", "54321", 30.0, "Tipo C"));

        ObservableList<Produto> produtosObservableList = FXCollections.observableList(produtos);



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
        //lista de testes
        ArrayList<Produto> produtos = new ArrayList<>(); // puxar com o banco de dados

        produtos.add(new Produto("Produto 1", "12345", 10.0, "Tipo A"));
        produtos.add(new Produto("Produto 11", "11115", 12.0, "Tipo A"));
        produtos.add(new Produto("Produto 2", "67890", 20.0, "Tipo B"));
        produtos.add(new Produto("Produto 3", "54321", 30.0, "Tipo C"));

        ObservableList<Produto> produtosObservableList = FXCollections.observableList(produtos);

        listaProdutos.setItems(produtosObservableList);

        // Filtra os produtos do tipo "A"
        ObservableList<Produto> produtosTipoA = produtosObservableList.filtered(produto -> produto.getTipo().equals("Tipo A"));

        listaProdutos.setItems(produtosTipoA); // Define a lista de produtos do tipo "A" as o conteúdo da lista
    }

    @FXML
    void setMenuBebidas(ActionEvent actionEvent) {
        //lista de testes
        ArrayList<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Produto 1", "12345", 10.0, "Tipo A"));
        produtos.add(new Produto("Produto 11", "11115", 12.0, "Tipo A"));
        produtos.add(new Produto("Produto 2", "67890", 20.0, "Tipo B"));
        produtos.add(new Produto("Produto 3", "54321", 30.0, "Tipo C"));

        ObservableList<Produto> produtosObservableList = FXCollections.observableList(produtos);

        // Filtra os produtos do tipo "B"
        ObservableList<Produto> produtosTipoB = produtosObservableList.filtered(produto -> produto.getTipo().equals("Tipo B"));

        listaProdutos.setItems(produtosTipoB); // Define a lista de produtos do tipo "B" como o conteúdo da lista
    }


    @FXML
    void setMenuDoces(ActionEvent actionEvent) {

        //lista de testes
        ArrayList<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Produto 1", "12345", 10.0, "Tipo A"));
        produtos.add(new Produto("Produto 11", "11115", 12.0, "Tipo A"));
        produtos.add(new Produto("Produto 2", "67890", 20.0, "Tipo B"));
        produtos.add(new Produto("Produto 3", "54321", 30.0, "Tipo C"));

        ObservableList<Produto> produtosObservableList = FXCollections.observableList(produtos);

        // Filtra os produtos do tipo "C"
        ObservableList<Produto> produtosTipoC = produtosObservableList.filtered(produto -> produto.getTipo().equals("Tipo C"));

        listaProdutos.setItems(produtosTipoC); // Define a lista de produtos do tipo "C" como o conteúdo da lista

    }

    @FXML
    void novoCliente() {

        try {
            File file = new File("..\\cadastroclientes\\CadastroClienteApplication.java");
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.err.println("File does not exist.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }


    @FXML
    void botaoAdicionarProdutoClicado(){

        double valorProduto = 0;
        String nomeProduto;

        //lista de testes
        ArrayList<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Produto 1", "12345", 10.0, "Tipo A"));
        produtos.add(new Produto("Produto 11", "11115", 12.0, "Tipo A"));
        produtos.add(new Produto("Produto 2", "67890", 20.0, "Tipo B"));
        produtos.add(new Produto("Produto 3", "54321", 30.0, "Tipo C"));

        ObservableList<Produto> produtosObservableList = FXCollections.observableList(produtos);

        String codigo = codigoDoProduto.getText();

        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigo)) {
                valorProduto = produto.getPreco();
                break;
            }
        }

        totalAtual = totalAtual + valorProduto;

        valorTotal.setText("R$"+totalAtual);


        // escreve na lista de produtos do pedido
        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigo)) {
                nomeProduto= produto.getNome();
                break;
            }
        }


        // Initialize the ObservableList for the ListView
        ObservableList<String> produtosSelecionados = (ObservableList<String>) listaDeProdutos.getItems();

        // Find the selected product by its codigo
        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigo)) {
                nomeProduto = produto.getNome();
                produtosSelecionados.add(nomeProduto); // Add the product name to the ListView
                break;
            }
        }


    }

    public void botaoNovoClienteClique(MouseEvent mouseEvent) {

        //nao sera feito cadastro

    }



    public void botaoFinalizarPedidoClicado(ActionEvent actionEvent) {

        // primeiro precisa registrar o pedido no BD

        //
        valorTotal.setText("R$"+0);
        ObservableList<String> produtosSelecionados = (ObservableList<String>) listaDeProdutos.getItems();
        produtosSelecionados.clear(); // Clear the ObservableList


    }
}