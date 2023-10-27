package com.feijaopreto.paginainicial.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

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

}
