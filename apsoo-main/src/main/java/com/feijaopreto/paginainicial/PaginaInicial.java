package com.feijaopreto.paginainicial;

import com.feijaopreto.cadastroclientes.CadastroClienteApplication;
import com.feijaopreto.paginainicial.controllers.PaginaInicialController;
import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaInicial extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PaginaInicial.class.getResource("views/pag-inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Página Inicial");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
