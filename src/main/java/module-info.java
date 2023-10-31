module com.example.vendaemrestaurante {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.datatransfer;
    requires java.desktop;
    requires java.sql;

    opens com.example.vendaemrestaurante to javafx.fxml;
    exports com.example.vendaemrestaurante;
}