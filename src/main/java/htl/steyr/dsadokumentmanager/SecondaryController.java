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
import java.util.Objects;

public class SecondaryController {
@FXML
    public Button NextButton;
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
        PDFContent = PDFContent.replaceAll("\\s+", ";")
                .replaceAll(";+", ";")
                .replaceAll(",", "")
                .replaceAll("\n", ";")
                .replaceAll("-", "")
                .replaceAll("Summe;Darf;zum;eigenen;Gebrauch;photokopiert;werden;Copyright;©;2006;by;Fantasy;Productions;GmbH;alle;Rechte;vorbehalten.;", "");
        textArea.setText(PDFContent);



//Folgende werte sind immer an gleicher stelle
        String Mut = PDFContent.substring(28, 30);
        String Klugheit = PDFContent.substring(31, 33);
        String Intuition = PDFContent.substring(34, 36);
        String Charisma = PDFContent.substring(37, 38);
        String Fingerfertigkeit = PDFContent.substring(39, 41);
        String Gewandheit = PDFContent.substring(42, 44);
        String Konstitution = PDFContent.substring(45, 47);
        String Körperkraft = PDFContent.substring(48, 50);
        String Lebensenergie = PDFContent.substring(51, 53);
        String Ausdauer = PDFContent.substring(54, 56);
        String Magieresistenz = PDFContent.substring(57, 60);
        String Wundschwelle = PDFContent.substring(61, 62);

        String Rasse;
        String Kultur;



// Die längste rasse ist 8 zeichen lang daher suchen wir in einem raster von 1 - 8 (+1)
        if (PDFContent.substring(1, 9).contains("Zwerg")) {
            Rasse = "Zwerg";
        } else if (PDFContent.substring(1, 9).contains("Mensch")) {
            Rasse = "Mensch";
        } else if (PDFContent.substring(1, 9).contains("Ork")) {
            Rasse = "Ork";
        } else if (PDFContent.substring(1, 9).contains("Elf")) {
            Rasse = "Elf";
        } else if (PDFContent.substring(1, 9).contains("Achaz")) {
            Rasse = "Achaz";
        } else if (PDFContent.substring(1, 9).contains("Hybrid")) {
            Rasse = "Hybrid";
        } else if (PDFContent.substring(1, 9).contains("Goblin")) {
            Rasse = "Goblin";
        }


        //Mehrzahl Kontrollieren!!!!!
        if (PDFContent.substring(1, 100).contains("Mittelländer")) {
            Kultur = "Mittelländer";
        } else if (PDFContent.substring(1, 100).contains("Nievesen")) {
            Kultur = "Nievesen";
        } else if (PDFContent.substring(1, 100).contains("Norbarden")) {
            Kultur = "Norbarden";
        } else if (PDFContent.substring(1, 100).contains("Thorwaler")) {
            Kultur = "Thorwaler";
        } else if (PDFContent.substring(1, 100).contains("Fjarninger")) {
            Kultur = "Fjarninger";
        } else if (PDFContent.substring(1, 100).contains("Gjalskerländer")) {
            Kultur = "Gjalskerländer";
        } else if (PDFContent.substring(1, 100).contains("Trollzacker")) {
            Kultur = "Trollzacker";
        } else if (PDFContent.substring(1, 100).contains("Rochshazi")) {
            Kultur = "Rochshazi";
        }else if (PDFContent.substring(1, 100).contains("Tulamid")) {
            Kultur = "Tulamiden";
        } else if (PDFContent.substring(1, 100).contains("Utulus")) {
            Kultur = "Utulus";
        } else if (PDFContent.substring(1, 100).contains("Waldmensch")) {
            Kultur = "Waldmenschen";
        } else if (PDFContent.substring(1, 100).contains("Tocamuyac")) {
            Kultur = "Tocamuyac";
        } else if (PDFContent.substring(1, 100).contains("Halbelf")) {
            Kultur = "Halbelfen";
        } else if (PDFContent.substring(1, 100).contains("Halbork")) {
            Kultur = "Halbork";
        } else if (PDFContent.substring(1, 100).contains("Echsensumpf")) {
            Kultur = "Echsensumpf-Achaz";
        } else if (PDFContent.substring(1, 100).contains("Maraskan")) {
            Kultur = "Maraskan";
        } else if (PDFContent.substring(1, 100).contains("Orkland")) {
            Kultur = "Orkland";
        } else if (PDFContent.substring(1, 100).contains("Regenwald")) {
            Kultur = "Regenwald";
        } else if (PDFContent.substring(1, 100).contains("Waldinsel")) {
            Kultur = "Waldinsel";
        } else if (PDFContent.substring(1, 100).contains("Auelf")) {
            Kultur = "Auelfen";
        } else if (PDFContent.substring(1, 100).contains("Firnelf")) {
            Kultur = "Firnelfen";
        } else if (PDFContent.substring(1, 100).contains("Waldelf")) {
            Kultur = "Waldelfen";
        } else if (PDFContent.substring(1, 100).contains("Ambosszwerg")) {
            Kultur = "Ambosszwerg";
        } else if (PDFContent.substring(1, 100).contains("Brilliantzwerg")) {
            Kultur = "Brilliantzwerg";
        } else if (PDFContent.substring(1, 100).contains("Erzzwerg")) {
            Kultur = "Erzzwerg";
        } else if (PDFContent.substring(1, 100).contains("Hügelzwerg")) {
            Kultur = "Hügelzwerg";
        } else if (PDFContent.substring(1, 100).contains("Wilder Zwerg")) {
            Kultur = "Wilder Zwerg";
        } else if (PDFContent.substring(1, 100).contains("Ork Frau")) {
            Kultur = "Ork Frau";
        }else if (PDFContent.substring(1, 100).contains("Goblin Frau")) {
            Kultur = "Goblin Frau";
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
        stage.setTitle("DSADokument");
        stage.setScene(scene);
        stage.show();
    }



    public void pathTextField(ActionEvent actionEvent) {
        // You can handle events here if needed
    }



}