package htl.steyr.dsadokumentmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.lang.reflect.Array;

public class HelloController {

    @FXML
    private TextField textField;

    @FXML
    private TextArea textArea;

    public void onLoadButtonClicked(ActionEvent actionEvent) throws Exception {
        File DSA = new File(textField.getText());
        if (!DSA.exists()) {
            textArea.setText("Datei nicht gefunden!");
            return;
        }

        try (PDDocument document = PDDocument.load(DSA)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String extractedText = pdfStripper.getText(document);
            textArea.setText(extractedText);
        } catch (Exception e) {
            textArea.setText("Fehler beim Laden oder Lesen der PDF-Datei:\n" + e.getMessage());
        }

        String PDFContent = textArea.getText();
        PDFContent = PDFContent.replaceAll("\n", ";")
                .replaceAll(" ", ";").replaceAll("  ", ";")
                .replaceAll("  ", ";").replaceAll("\t", ";")
                .replaceAll(";;", ";").replaceAll(";;;", ";")
                .replaceAll(";;;", ";").replaceAll(";;;;", ";")
                .replaceAll(";;;;;", ";").replaceAll(";;;;;;", ";").replaceAll(",", "");
        textArea.setText(PDFContent);
    }

    public void pathTextField(ActionEvent actionEvent) {
        // You can handle events here if needed
    }
}