module com.example.contactorganizer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.contactorganizer to javafx.fxml;
    exports com.example.contactorganizer;
}