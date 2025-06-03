package htl.steyr.dsadokumentmanager;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Map;

public class DSADokumentenController {

    public ImageView DSALOGO ;
    public Button RechnerButton;
    public TextArea WerteTextArea;
    public Button Minus5Button;
    public Button Minus1Button;
    public Button Plus5Button;
    public Button Plus1Button;
    public Label CharNameLabel;
    public Label LebensenergieLabel;



    public void initialize() {


        try {
            // Angenommen, Ihr Bild heißt "logo.png" - passen Sie den Namen entsprechend an
            String bildPfad = "C:\\Users\\jonas\\OneDrive\\Dokumente\\DSA_Test\\logo_dsa.png";
            File bildDatei = new File(bildPfad);

            if (!bildDatei.exists()) {
                System.err.println("Bilddatei wurde nicht gefunden: " + bildPfad);
                return;
            }

            Image logoImage = new Image(bildDatei.toURI().toString());
            if (logoImage.isError()) {
                System.err.println("Fehler beim Laden des Bildes: " + logoImage.getException().getMessage());
                return;
            }

            DSALOGO.setImage(logoImage);
            DSALOGO.setFitWidth(100);
            DSALOGO.setFitHeight(100);
            DSALOGO.setPreserveRatio(true);

        } catch (Exception e) {
            System.err.println("Fehler beim Laden des Bildes: " + e.getMessage());
        }





        PDFService pdfService = PDFService.getInstance();
        Map<String, String> charakterDaten = pdfService.getCharakterAttribute();

        StringBuilder ausgabe = new StringBuilder();
        StringBuilder Lebensenergie = new StringBuilder();
        ausgabe.append("Charakter Übersicht:\n\n");
        ausgabe.append("=== Grundinformationen ===\n");
        ausgabe.append("Rasse: ").append(pdfService.getRasse()).append("\n");
        ausgabe.append("Kultur: ").append(pdfService.getKultur()).append("\n\n");

        ausgabe.append("=== Eigenschaften ===\n");
        ausgabe.append("MU: ").append(charakterDaten.get("Mut")).append("\n");
        ausgabe.append("KL: ").append(charakterDaten.get("Klugheit")).append("\n");
        ausgabe.append("IN: ").append(charakterDaten.get("Intuition")).append("\n");
        ausgabe.append("CH: ").append(charakterDaten.get("Charisma")).append("\n");
        ausgabe.append("FF: ").append(charakterDaten.get("Fingerfertigkeit")).append("\n");
        ausgabe.append("GE: ").append(charakterDaten.get("Gewandheit")).append("\n");
        ausgabe.append("KO: ").append(charakterDaten.get("Konstitution")).append("\n");
        ausgabe.append("KK: ").append(charakterDaten.get("Körperkraft")).append("\n\n");

        ausgabe.append("=== Basiswerte ===\n");
        Lebensenergie.append("Lebensenergie: ").append(charakterDaten.get("Lebensenergie")).append("\n");
        ausgabe.append("Ausdauer: ").append(charakterDaten.get("Ausdauer")).append("\n");
        ausgabe.append("Magieresistenz: ").append(charakterDaten.get("Magieresistenz")).append("\n");
        ausgabe.append("Wundschwelle: ").append(charakterDaten.get("Wundschwelle")).append("\n");

        WerteTextArea.setText(ausgabe.toString());
        LebensenergieLabel.setText(Lebensenergie.toString());

        WerteTextArea.setEditable(false);
    }


    private void updateLebensenergie(int delta) {
        try {
            String currentText = LebensenergieLabel.getText();
            String[] parts = currentText.split(":");
            if (parts.length != 2) {
                return;
            }

            int currentValue = Integer.parseInt(parts[1].trim());
            int newValue = currentValue + delta;


            LebensenergieLabel.setText("Lebensenergie: " + newValue + "\n");
        } catch (NumberFormatException e) {
            // Behandle ungültige Zahlenformate
            System.err.println("Fehler beim Parsen der Lebensenergie");
        }
    }

    public void onMinus5ButtonClicked() {
        updateLebensenergie(-5);
    }

    public void onMinus1ButtonClicked() {
        updateLebensenergie(-1);
    }

    public void onPlus5ButtonClicked() {
        updateLebensenergie(5);
    }

    public void onPlus1ButtonClicked() {
        updateLebensenergie(1);
    }



}


