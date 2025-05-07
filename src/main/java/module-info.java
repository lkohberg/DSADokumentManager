module htl.steyr.dsadokumentmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens htl.steyr.dsadokumentmanager to javafx.fxml;
    exports htl.steyr.dsadokumentmanager;
}