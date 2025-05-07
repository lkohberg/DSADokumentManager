module htl.steyr.dsadokumentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;


    opens htl.steyr.dsadokumentmanager to javafx.fxml;
    exports htl.steyr.dsadokumentmanager;
}