module com.example.projectdas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.controlsfx.controls;

    opens com.example.projectdas to javafx.fxml;
    exports com.example.projectdas;
    exports com.example.hellofx;
    opens com.example.hellofx to javafx.fxml;
}