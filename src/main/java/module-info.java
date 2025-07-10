module com.example.fxabgabe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.fxabgabe to javafx.fxml;
    exports com.example.fxabgabe;
    exports com.example.fxabgabe.MVC;
    opens com.example.fxabgabe.MVC to javafx.fxml;
    exports com.example.fxabgabe.Model;
    opens com.example.fxabgabe.Model to javafx.fxml;
}