module ch.ictbz.kontakte.kontakte {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.ictbz.kontakte.kontakte to javafx.fxml;
    exports ch.ictbz.kontakte.kontakte;
}