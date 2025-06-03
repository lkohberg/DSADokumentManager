package htl.steyr.dsadokumentmanager;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PDFService {
    private static PDFService instance;
    private final Map<String, String> charakterAttribute;
    private String pdfContent;
    private String rasse;
    private String kultur;

    // Private Konstruktor für Singleton-Pattern
    private PDFService() {
        charakterAttribute = new HashMap<>();
    }

    // Singleton-Instanz abrufen
    public static PDFService getInstance() {
        if (instance == null) {
            instance = new PDFService();
        }
        return instance;
    }

    public void verarbeitePDF(String pdfPfad) throws IOException {
        File dsaDatei = new File(pdfPfad);
        if (!dsaDatei.exists()) {
            throw new IOException("Datei nicht gefunden: " + pdfPfad);
        }

        try (PDDocument document = PDDocument.load(dsaDatei)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfContent = pdfStripper.getText(document);

            // Text aufbereiten
            pdfContent = pdfContent.replaceAll("\\s+", ";")
                    .replaceAll(";+", ";")
                    .replaceAll(",", "")
                    .replaceAll("\n", ";")
                    .replaceAll("-", "")
                    .replaceAll("Summe;Darf;zum;eigenen;Gebrauch;photokopiert;werden;Copyright;©;2006;by;Fantasy;Productions;GmbH;alle;Rechte;vorbehalten.;", "");

            extrahiereAttribute();
            bestimmeRasse();
            bestimmeKultur();
        }
    }

    private void extrahiereAttribute() {
        // Charakterattribute extrahieren
        charakterAttribute.put("Mut", pdfContent.substring(28, 30).trim());
        charakterAttribute.put("Klugheit", pdfContent.substring(31, 33).trim());
        charakterAttribute.put("Intuition", pdfContent.substring(34, 36).trim());
        charakterAttribute.put("Charisma", pdfContent.substring(37, 38).trim());
        charakterAttribute.put("Fingerfertigkeit", pdfContent.substring(39, 41).trim());
        charakterAttribute.put("Gewandheit", pdfContent.substring(42, 44).trim());
        charakterAttribute.put("Konstitution", pdfContent.substring(45, 47).trim());
        charakterAttribute.put("Körperkraft", pdfContent.substring(48, 50).trim());
        charakterAttribute.put("Lebensenergie", pdfContent.substring(51, 53).trim());
        charakterAttribute.put("Ausdauer", pdfContent.substring(54, 56).trim());
        charakterAttribute.put("Magieresistenz", pdfContent.substring(57, 60).trim());
        charakterAttribute.put("Wundschwelle", pdfContent.substring(61, 62).trim());
    }

    private void bestimmeRasse() {
        String suchbereich = pdfContent.substring(1, 9);

        if (suchbereich.contains("Zwerg")) rasse = "Zwerg";
        else if (suchbereich.contains("Mensch")) rasse = "Mensch";
        else if (suchbereich.contains("Ork")) rasse = "Ork";
        else if (suchbereich.contains("Elf")) rasse = "Elf";
        else if (suchbereich.contains("Achaz")) rasse = "Achaz";
        else if (suchbereich.contains("Hybrid")) rasse = "Hybrid";
        else if (suchbereich.contains("Goblin")) rasse = "Goblin";
        else rasse = "Unbekannt";

        charakterAttribute.put("Rasse", rasse);
    }

    private void bestimmeKultur() {
        String suchbereich = pdfContent.substring(1, 100);

        // Kulturen-Map für einfachere Wartung
        Map<String, String> kulturMap = new HashMap<>();
        kulturMap.put("Mittelländer", "Mittelländer");
        kulturMap.put("Nievesen", "Nievesen");
        kulturMap.put("Norbarden", "Norbarden");
        kulturMap.put("Thorwaler", "Thorwaler");
        kulturMap.put("Fjarninger", "Fjarninger");
        kulturMap.put("Gjalskerländer", "Gjalskerländer");
        kulturMap.put("Trollzacker", "Trollzacker");
        kulturMap.put("Rochshazi", "Rochshazi");
        kulturMap.put("Tulamid", "Tulamiden");
        kulturMap.put("Utulus", "Utulus");
        kulturMap.put("Waldmensch", "Waldmenschen");
        kulturMap.put("Tocamuyac", "Tocamuyac");
        kulturMap.put("Halbelf", "Halbelfen");
        kulturMap.put("Halbork", "Halbork");
        kulturMap.put("Echsensumpf", "Echsensumpf-Achaz");
        kulturMap.put("Maraskan", "Maraskan");
        kulturMap.put("Orkland", "Orkland");
        kulturMap.put("Regenwald", "Regenwald");
        kulturMap.put("Waldinsel", "Waldinsel");
        kulturMap.put("Auelf", "Auelfen");
        kulturMap.put("Firnelf", "Firnelfen");
        kulturMap.put("Waldelf", "Waldelfen");
        kulturMap.put("Ambosszwerg", "Ambosszwerg");
        kulturMap.put("Brilliantzwerg", "Brilliantzwerg");
        kulturMap.put("Erzzwerg", "Erzzwerg");
        kulturMap.put("Hügelzwerg", "Hügelzwerg");
        kulturMap.put("Wilder Zwerg", "Wilder Zwerg");
        kulturMap.put("Ork Frau", "Ork Frau");
        kulturMap.put("Goblin Frau", "Goblin Frau");

        kultur = "Unbekannt";
        for (Map.Entry<String, String> entry : kulturMap.entrySet()) {
            if (suchbereich.contains(entry.getKey())) {
                kultur = entry.getValue();
                break;
            }
        }

        charakterAttribute.put("Kultur", kultur);
    }

    // Getter-Methoden
    public Map<String, String> getCharakterAttribute() {
        return new HashMap<>(charakterAttribute); // Returnt eine Kopie der Map
    }

    public String getRasse() {
        return rasse;
    }

    public String getKultur() {
        return kultur;
    }

    public String getAttributWert(String attributName) {
        return charakterAttribute.getOrDefault(attributName, "");
    }

    // Methode zum Zurücksetzen des Services
    public void reset() {
        charakterAttribute.clear();
        pdfContent = null;
        rasse = null;
        kultur = null;
    }
}