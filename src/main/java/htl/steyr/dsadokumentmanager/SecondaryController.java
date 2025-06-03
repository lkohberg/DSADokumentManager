package htl.steyr.dsadokumentmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class SecondaryController {
@FXML
    public Button NextButton;
    @FXML
    private TextField textField;

    @FXML
    private TextArea textArea;

    public void onLoadButtonClicked(ActionEvent actionEvent) throws Exception {
        try {
            PDFService pdfService = PDFService.getInstance();
            pdfService.verarbeitePDF(textField.getText());

            // Beispiel für Zugriff auf die Daten
            Map<String, String> attribute = pdfService.getCharakterAttribute();

            // Anzeigen der Werte in der TextArea
            textArea.setText("Charakter erfolgreich geladen!");

        } catch (IOException e) {
            textArea.setText("Fehler beim Laden der PDF: " + e.getMessage());
        }




        /**

         Test Area:

         textArea.clear();
         textArea.appendText(Mut + "\n");
         textArea.appendText(Klugheit+ "\n");
         textArea.appendText(Intuition+ "\n");
         textArea.appendText(Charisma+ "\n");
         textArea.appendText(Fingerfertigkeit+ "\n");
         textArea.appendText(Gewandheit+ "\n");
         textArea.appendText(Konstitution+ "\n");
         textArea.appendText(Körperkraft+ "\n");
         textArea.appendText(Lebensenergie+ "\n");
         textArea.appendText(Ausdauer+ "\n");
         textArea.appendText(Magieresistenz+ "\n");
         textArea.appendText(Wundschwelle+ "\n");

         */


    }

    @FXML
    public void onNextButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DSADokumentenController.class.getResource("DSADokumenten-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CharakterManager");
        stage.setScene(scene);
        stage.show();
    }



    public void pathTextField(ActionEvent actionEvent) {
        // You can handle events here if needed
    }



}