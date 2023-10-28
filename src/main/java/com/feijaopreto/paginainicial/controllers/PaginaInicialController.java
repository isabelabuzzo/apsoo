package com.feijaopreto.paginainicial.controllers;

import com.feijaopreto.cadastroclientes.database.dao.EstadoDAO;
import com.feijaopreto.cadastroclientes.models.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.awt.Desktop;
import java.io.File;

import com.feijaopreto.paginainicial.models.Pedido;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaginaInicialController {

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
    private ListView<?> listaProdutos;

    @FXML
    private Label nomeFuncionario;

    @FXML
    private Label nomeRestaurante;

    @FXML
    private ScrollPane rootPane;

    @FXML
    private ComboBox<?> selecCodCliente;

    @FXML
    private ComboBox<?> selecPagamento;



    @FXML
    public void initialize() {
        // relogio
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            horario.setText(sdf.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();




    }


    @FXML
    void setMenuAlimento(ActionEvent event) {

        botaoAliment.setOnAction(e -> {
            listaProdutos.getItems().clear(); // limpa antes de listar
            listaProdutos.setItems(alimentos); // alimentos precisa vir do banco de dados
        });

    }


    @FXML
    void setMenuBebida(ActionEvent event) {

        botaoBebida.setOnAction(e -> {
            listaProdutos.getItems().clear(); // limpa antes de listar
            listaProdutos.setItems(bebidas); // bebidas precisa vir do banco de dados
        });
    }


    @FXML
    void setMenuDoce(ActionEvent event) {

        botaoBebida.setOnAction(e -> {
            listaProdutos.getItems().clear(); // limpa antes de listar
            listaProdutos.setItems(doces); // doces precisa vir do banco de dados
        });
    }

    @FXML
    void BotaoFinalizarPedido(ActionEvent event) {

        cadastrarPedido()

    }

    @FXML
    void novoCliente() {

        botaoNovoCliente.setOnAction(e -> {
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
        });
    }





}