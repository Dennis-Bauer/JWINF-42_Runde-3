package main;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import static main.ConsoleUtilities.*;
import main.ConsoleUtilities.colors;
import main.ConsoleUtilities.textStyle;

/**
 *  <h2>Junioraufgabe-2</h2>
 * 
 *  <h3>Jugendwettbewerb 2024 (42.)</h3>
 *  <p>Runde 3</p>
 * 
 * @author Dennis Bauer
 * @version 1.0
 * @since 01.09.2024
 */
public class Main {
    
     /**
     * Dies ist die Main-Funktion, in der das Programm startet und endet.
     * Sie verbindet verschiedene Funktionen und dient als zentrale Steuerungseinheit.
     */
    public static void main(String[] args) {
        consoleOutPut(colors.GREEN, textStyle.BOLD, "-Jugendwettbewerb-2024-42-Runde-3- ");
        consoleOutPut(colors.CYAN, textStyle.BOLD, "Junioraufgabe-2 ");
        consoleOutPutLine(colors.PURPLE, textStyle.REGULAR, "Dennis Bauer");

        System.out.println(" ");

        // Erstellt eine Variable, die einen Text speichert, welcher durch eine separate Funktion eingeben wird.
        String text = getTextInput(getFileName());
        
        System.out.println(" ");

        // Spiel an sich
        consoleOutPut(colors.BLUE, textStyle.REGULAR, "---");
        consoleOutPut(colors.BLUE, textStyle.BOLD, "Ergebnisse");
        consoleOutPutLine(colors.BLUE, textStyle.REGULAR, "---------------------------");

        // Speichert, wie viele Schritte die jeweilige Person benötigt hat. Die Berechnung erfolgt in einer separaten Funktion.
        int bella = runThroughGame(text, 0);
        int amira = runThroughGame(text, 1);

        consoleOutPut(colors.BLUE, textStyle.REGULAR, "Bella hat ");
        consoleOutPut(colors.BLUE, textStyle.BOLD, bella + " ");
        consoleOutPutLine(colors.BLUE, textStyle.REGULAR, "Runden gebraucht.");

        consoleOutPut(colors.BLUE, textStyle.REGULAR, "Amira hat ");
        consoleOutPut(colors.BLUE, textStyle.BOLD, amira + " ");
        consoleOutPutLine(colors.BLUE, textStyle.REGULAR, "Runden gebraucht.");

        // Gibt aus, wer gewonnen hat.
        if (bella < amira) {
            consoleOutPut(colors.BLUE, textStyle.REGULAR, "So hat ");
            consoleOutPut(colors.GREEN, textStyle.BOLD, "Bella mit " + (amira - bella));
            consoleOutPutLine(colors.BLUE, textStyle.REGULAR, " Runden weniger gewonnen!");
        } else if (amira < bella) {
            consoleOutPut(colors.BLUE, textStyle.REGULAR, "So hat ");
            consoleOutPut(colors.GREEN, textStyle.BOLD, "Amira mit " + (bella - amira));
            consoleOutPutLine(colors.BLUE, textStyle.REGULAR, " Runden weniger gewonnen!");
        } else {
            consoleOutPut(colors.BLUE, textStyle.REGULAR, "Bella und Amira brauchten gleich lange, aber da ");
            consoleOutPut(colors.GREEN, textStyle.BOLD, "Bella ");
            consoleOutPutLine(colors.BLUE, textStyle.REGULAR, " begonnen hat, hat sie gewonnen!");
        }

        consoleOutPutLine(colors.BLUE, textStyle.REGULAR, "---------------------------------------");
    }

    /**
     * Simuliert einen Spieler.
     * @param text Der Text, durch den das Programm läuft.
     * @param startPosition Die Position im Text, an der der Spieler startet.
     * @return Die Anzahl an Spielzügen, die das Programm benötigt hat.
     */
    private static int runThroughGame(String text, int startPosition) {
        // Erstellt die Variable "pos", die die aktuelle Position im Text speichert, an der sich das Programm gerade befindet.   
        int pos = startPosition;
    
        // Erstellt die Variable "runs", die speichert, wie viele spiel Züge am Ende benötigt wurden.
        int runs = 0;

        // Die Schleife läuft, bis die Position über das Textende hinausgeht.
        while (pos + 1 <= text.length()) {
            // Jeder Schleifendurchlauf entspricht einem Spielzug, daher wird die Variable erhöht.
            runs++;

            // Wandelt den Buchstaben an der aktuellen Position im Text in eine Zahl um (ASCII).
            int letterNumber = ((int) text.charAt(pos)) - 96;

            // Wandelt Zeichen, die keine Buchstaben des lateinischen Alphabets sind, in die entsprechende Zahl um.            
            if (letterNumber > 26 || letterNumber < 1) {
                switch (letterNumber) {
                    case 132 -> letterNumber = 27; //ä
                    case 150 -> letterNumber = 28; //ö
                    case 156 -> letterNumber = 29; //ü
                    case 127 -> letterNumber = 30; //ß
                    default -> System.exit(1); //Alles andere (Nicht möglich)
                }
            }

            // Erhöht die Position des Programmes um die Anzahl an "Sprünge" die der Buchstabe wieder spiegelt auf
            // dem das Programm sich grade befindet
            pos += letterNumber;
        }

        return runs;
    }

   /**
    * Liest die Datei mit dem angegebenen Namen aus.
    * @param fileName Der Name der Datei, die ausgelesen wird.
    * @return Den Text der Datei, korrekt konvertiert, als einzelner String.
    */
    private static String getTextInput(String fileName) {
        StringBuilder text = new StringBuilder();

        // Erstellt einen extra Scanner, der Dateien auslesen kann und ruft die, grade ausgewählte, Datei aus.
        InputStream inputStream = Main.class.getResourceAsStream("/main/resources/" + fileName + ".txt");

        if (inputStream == null) {
            consoleOutPut(colors.RED, textStyle.REGULAR, "Fehler, Datei mit dem Namen ");
            consoleOutPut(colors.RED, textStyle.BOLD, fileName + ".txt ");
            consoleOutPutLine(colors.RED, textStyle.REGULAR, "wurde nicht im normalen resources Ordner gefunden!");
            System.exit(1);
        }

        try (Scanner fileInput = new Scanner(inputStream)) {

            // Liest die verschiedenen Zeilen aus der Datei und fügt sie an den String text an.
            while (fileInput.hasNext()) {
                String line = fileInput.nextLine();
                if (!line.isEmpty()) {
                    // Wandelt alle Buchstaben in der Zeile in Kleinbuchstaben um.
                    line = line.toLowerCase();
                    // Entfernt alle Zeichen, die nicht im Unicode-Bereich liegen.
                    line = line.replaceAll("[^a-zäöüß]", "");
                    text.append(line);
                }
            }
            
        }

        return text.toString();
    }

     /**
     * Fragt den Nutzer, ob er Beispiele nutzen möchte oder eine eigene Datei. Falls eine eigene Datei gewählt wird, 
     * wird auch nach dem Dateinamen gefragt.
     * @return Den Dateinamen des ausgewählten Beispiels oder der eigenen Datei.
     */
    private static String getFileName() {

         // Erstellt ein Scanner, der die Eingabe des Benutzers durch die Konsole aus lesen kann.
         try (Scanner userInput = new Scanner(System.in)) {

            int answer;

            do { 
                consoleOutPut(colors.BLUE, textStyle.REGULAR, "Möchten sie eine Beispieldatei nutzen (");
                consoleOutPut(colors.BLUE, textStyle.BOLD, "1");
                consoleOutPut(colors.BLUE, textStyle.REGULAR, ") oder eine eigene Datei (");
                consoleOutPut(colors.BLUE, textStyle.BOLD, "2");
                consoleOutPut(colors.BLUE, textStyle.REGULAR, ")?: ");

                try {
                    // Versucht, eine Ganzzahl vom Benutzer über die Konsole einzulesen und diese als Rückgabewert der Funktion zurückzugeben.
                    answer = userInput.nextInt();

                    if (answer != 2 && answer != 1) {
                        consoleOutPut(colors.RED, textStyle.REGULAR, "Bitte geben sie nur ");
                        consoleOutPut(colors.BLUE, textStyle.BOLD, "\"1\" für eine Beispieldatei ");
                        consoleOutPut(colors.RED, textStyle.REGULAR, "oder ");
                        consoleOutPut(colors.BLUE, textStyle.BOLD, "\"2\" für eine eigene Datei ");
                        consoleOutPutLine(colors.RED, textStyle.REGULAR, "an!");
                    }

                } catch (InputMismatchException e) { // Wenn die Eingabe keine Ganzzahl (Integer) ist, fängt dieser Catch-Block den geworfenen Fehler ab und wiederholt die Abfrage des Benutzers.
                    consoleOutPut(colors.RED, textStyle.REGULAR, "Bitte geben sie nur ");
                    consoleOutPut(colors.RED, textStyle.BOLD, "Zahlen ");
                    consoleOutPutLine(colors.RED, textStyle.REGULAR, "an!");
    
                    userInput.next();
                    answer = -1;
                }                  

            } while  (answer != 2 && answer != 1);

            // Erstellt eine Variable, in der der Dateiname gespeichert wird.
            String fileName;

            if (answer == 1) {
                // Erstellt eine Variable, die die Nummer in dem ausgewählten Beispiel speichert.
                int exampleNumber;

                do { 
                    consoleOutPut(colors.BLUE, textStyle.REGULAR, "Bitte geben sie an, mit welchem Beispiel gespielt werden soll (1-5): ");

                    try {
                        // Versucht, eine Ganzzahl vom Benutzer über die Konsole einzulesen und diese als Rückgabewert der Funktion zurückzugeben.
                        exampleNumber = userInput.nextInt();
    
                        if (exampleNumber < 1 || exampleNumber > 5) {
                            consoleOutPut(colors.RED, textStyle.REGULAR, "Bitte geben sie nur Werte von ");
                            consoleOutPut(colors.BLUE, textStyle.BOLD, "1 bis 5 ");
                            consoleOutPutLine(colors.RED, textStyle.REGULAR, "an!"); 
                        }
    
                    } catch (InputMismatchException e) { // Wenn die Eingabe keine Ganzzahl (Integer) ist, fängt dieser Catch-Block den geworfenen Fehler ab und wiederholt die Abfrage des Benutzers.
                        consoleOutPut(colors.RED, textStyle.REGULAR, "Bitte geben sie nur ");
                        consoleOutPut(colors.RED, textStyle.BOLD, "Zahlen ");
                        consoleOutPutLine(colors.RED, textStyle.REGULAR, "an!");
        
                        userInput.next();
                        exampleNumber = -1;
                    }  
                    
                } while (exampleNumber < 1 || exampleNumber > 5);

                fileName = "hopsen" + exampleNumber;
            } else {
                consoleOutPut(colors.BLUE, textStyle.REGULAR, "Bitte geben sie den Namen der Datei an (beachten sie, dass sich die Datei im \"resources\" Ordner befinden muss): ");
                                
                // Diese konsolen Eingabe muss nicht in einen Try-Catch-Block, da jede eingabe ein String sein kann. Auch zmB Zahlen, da diese auch eine Folge von Zeichen sind.
                fileName = userInput.next();
            }

            // Falls der Benutzer das datei Format im Dateinamen angegeben hat, wird dieser hier entfernt.
            if (fileName.endsWith(".txt"))
                fileName = fileName.substring(0, fileName.length() - 4);

            return fileName;
        }
    }

}
