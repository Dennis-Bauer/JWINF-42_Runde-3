package main;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import static main.ConsoleUtilities.*;

/**
 *  <h2>Junioraufgabe-1</h2>
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
        consoleOutPut(ConsoleUtilities.colors.GREEN, ConsoleUtilities.textStyle.BOLD, "-Jugendwettbewerb-2024-42-Runde-3- ");
        consoleOutPut(ConsoleUtilities.colors.CYAN, ConsoleUtilities.textStyle.BOLD, "Junioraufgabe-1 ");
        consoleOutPutLine(ConsoleUtilities.colors.PURPLE, ConsoleUtilities.textStyle.REGULAR, "Dennis Bauer");

        System.out.println(" ");

        // Erstellt die 3 Variablen zur Berechnung der Ergebnisse.
        int customers;
        double gardenHeight, gardenWidth;

        // Holt die Werte über eine separate Funktion und speichert sie in den zuvor erstellten Variablen. 
        double[] values = getValues();
        customers = (int) values[0];
        gardenHeight = values[1];
        gardenWidth  = values[2];
        
        System.out.println(" ");

        // Gibt alle 3 Werte aus.   
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Es gibt ");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, customers + " Interessenten");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, ". Der Garten ist ");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, String.format("%.2f", gardenHeight) + "m x " + String.format("%.2f", gardenWidth) + "m");
        consoleOutPutLine(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, " groß.");

        System.out.println(" ");

        calculateAnswers(customers, gardenHeight, gardenWidth);
    }

    /**
     * Diese Funktion führt die Berechnung der gesamten Aufgabe durch und gibt die Ergebnisse in der Konsole aus.
     * @param customers Diese Variable speichert die Anzahl der Interessenten.
     * @param gardenHeight Diese Variable speichert die Höhe des Gartens.
     * @param gardenWidth Diese Variable speichert die Breite des Gartens.
     */
    private static void calculateAnswers(int customers, double gardenHeight, double gardenWidth) {
        // Berechnung (Die Aufgabe an sich)
        consoleOutPut(ConsoleUtilities.colors.BLACK, ConsoleUtilities.textStyle.REGULAR, "---");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, "Ergebnisse");
        consoleOutPut(ConsoleUtilities.colors.BLACK, ConsoleUtilities.textStyle.REGULAR, "-------------------");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "(Dezimalzahlen gerundet)");
        consoleOutPutLine(ConsoleUtilities.colors.BLACK, ConsoleUtilities.textStyle.REGULAR, "--");

        // Gibt den Flächeninhalt des Gartens aus.
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Der Garten hat eine Fläche von: ");
        consoleOutPutLine(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, String.format("%.2f", (gardenHeight * gardenWidth)) + "m².");

        // Berechnet, wie viele Kleingärten in der Höhe und in der Breite des Gartens Platz finden mithilfe einer extra dafür erstellten Funktion.
        int[] dividing = calculateBestDividing(customers, gardenWidth, gardenHeight);
        int mGardenPerWidth = dividing[0];
        int mGardenPerHeight = dividing[1];

        // Gibt die gerade berechneten Werte aus.
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Der Garten wird in der Höhe des Grundstücks in ");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, mGardenPerHeight + " ");
        consoleOutPutLine(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Kleingärten unterteilt.");

        // Gibt die gerade berechneten Werte aus.
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Der Garten wird in der Breite des Grundstücks in ");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, mGardenPerWidth + " ");
        consoleOutPutLine(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Kleingärten unterteilt.");

        // Berechnet die gesamte Anzahl an Kleingärten und deren aktuelle Höhe/Breite (die Breite/Höhe ändert sich durch die gerundeten Werte).
        int mGardenTotal = mGardenPerHeight * mGardenPerWidth;
        double mGardenHeight = gardenHeight / (double) mGardenPerHeight;
        double mGardenWidth = gardenWidth / (double) mGardenPerWidth;
        double mGardenSize = mGardenHeight * mGardenWidth;

        // Gibt die gerade berechneten Werte aus.
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Insgesamt wird der Garten in ");
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, mGardenTotal + " ");
        consoleOutPutLine(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Kleingärten unterteilt.");

        // Gibt die gerade berechneten Werte aus.
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Ein Kleingarten hat so die Maße: ");
        consoleOutPutLine(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, String.format("%.2f", mGardenHeight) + "m x " + String.format("%.2f", mGardenWidth) + "m");

        // Gibt die gerade berechneten Werte aus.
        consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Der Flächeninhalt eines Kleingarten ist: ");
        consoleOutPutLine(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, String.format("%.2f", mGardenSize) + "m².");

        consoleOutPutLine(ConsoleUtilities.colors.BLACK, ConsoleUtilities.textStyle.REGULAR, "---------------------------------------------------------");
    }

    /**
     * Diese Funktion berechnet aus den drei Werten (Interessenten, Höhe, Breite) die bestmögliche Aufteilung des Grundstücks, um eine sp quadratisch wie mögliche Aufteilung zu erreichen.
     * Dies geschieht durchs Durchlaufen aller Möglichkeiten für x (Anzahl der Kleingärten), wobei diese bis zu 10 % größer sein darf als die Anzahl der Interessenten. In diesem Durchlauf
     * berechnet die Funktion die bestmögliche Aufteilung in Höhe und Breite durch Testen der Möglichkeiten. Die verschiedenen Ergebnisse werden verglichen und das Beste wird
     * zurückgegeben.
     * @param customers Die Anzahl der Interessenten (x)
     * @param width Die Breite des Grundstücks (a)
     * @param height Die Höhe des Grundstücks (b)
     * @return Gibt die beste Aufteilung als int-Array zurück (Kleingärten pro Breite, Kleingärten pro Höhe)
     */
    private static int[] calculateBestDividing(int customers, double width, double height) {

        // Speichert die Differenz zwischen zwei Seitenlängen eines Kleingartens. Je näher sie bei null liegt,
        // desto quadratischer ist ein Kleingarten
        BigDecimal bestDiff = BigDecimal.valueOf(Double.MAX_VALUE);
        // Speichert die beste Aufteilung der Kleingärten in die Breite (m) und in die Höhe (n)
        int[] dividing = new int[2];
    
        // Schleife starten, die 10 % von der Anzahl an Interessenten durchläuft.
        for (int i = 0; i <= customers / 10; i++) {
            // Neuer Interessenten wert wird mit I addiert
            int nCustomers = customers + i;

            // Speichert die beste Aufteilung der Kleingärten in die Breite (m) und in die Höhe (n) für
            // den neuen Interessentenwert
            int bestM = 0;
            int bestN = 0;

            // Erstellt eine Variable, die die bisher kleinste Differenz zwischen den Seitenlängen eines
            // Kleingartens speichert.
            BigDecimal minDiff = BigDecimal.valueOf(Double.MAX_VALUE);
        
            // Durchläuft mögliche Werte für die Anzahl der Kleingärten in Höhe und Breite
            for (int m = 1; m <= nCustomers; m++) {
                // Testet, ob die Anzahl der Kleingärten pro Breite möglich ist (ob sie ein Teiler von nCustomers ist)
                if (nCustomers % m == 0) {
                    // Da m (Kleingärten pro Breite) eine Möglichkeit ist, die Kleingärten in der Breite zu unterteilen,
                    // wird die Höheneinteilung ausgerechnet
                    int n = nCustomers / m;
                    
                    // Berechnet die Seitengrößen der Kleingärten und speichert die Differenz zwischen den beiden.
                    // Je kleiner die Differenz ist, desto quadratischer ist der Kleingarten.
                    // Die Math.abs-Methode wandelt jedes Ergebnis in ein positives Ergebnis um, um die
                    // Vergleiche immer beizubehalten.
                    BigDecimal diff = BigDecimal.valueOf(Math.abs((width / m) - (height / n)));
                    
                    if (diff.compareTo(minDiff) < 0) {
                        minDiff = diff;
                        bestM = m;
                        bestN = n;
                    }
                }
            }

            BigDecimal newDiff = BigDecimal.valueOf(Math.abs((height / bestN) - (width / bestM)));

            // Überprüft, ob die Aufteilung der Kleingärten besser ist
            if (newDiff.compareTo(bestDiff) < 0) {
                dividing[0] = bestM;
                dividing[1] = bestN;

                bestDiff = newDiff;
            }

            // Überprüft, ob die bisherige beste Differenz 0 ist. Wenn ja, wird der Array zurückgeben, da es kein
            // besseres Ergeben geben kann.
            if (bestDiff.doubleValue() == 0)
                return dividing;
        }   

        return dividing;
    }

    /**
     * Diese Funktion sammelt die 3 Werte (Interessenten, Höhe, Breite).
     * Diese Werte liest sie durch die Benutzereingabe ein.
     * @return Gibt die Werte als Integer-Array zurück (Interessenten, Höhe, Breite).
     */
    private static double[] getValues() {

         // Erstellt ein Scanner, der die Eingabe des Benutzers durch die Konsole aus lesen kann.
         try (Scanner input = new Scanner(System.in)) {

            // Erstellt ein Integer Array der die späteren 3 Werte (Interessenten, Höhe, Breite) widerspiegelt.
            double[] values = new double[3];

            int i = 0;

            // Fragt nach den 3 Werten (Interessanten, Breite, Höhe) solang bis alle akzeptabel sind
            do {
                try {
                    switch (i) {
                        // Fragt nach der Anzahl der Interessenten mithilfe des Scanners
                        case 0 ->{  
                            consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Geben sie bitte an, wie viele Interessenten es gibt: ");
                            values[i] = input.nextInt();
                        }
                        // Fragt nach der Höhe des Grundstücks mithilfe des Scanners
                        case 1 -> {
                            consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Geben sie bitte an, wie hoch das Grundstück ist (in Meter): ");
                            values[i] = input.nextDouble();
                        }
                        // Fragt nach der Breit des Grundstücks mithilfe des Scanners
                        case 2 -> {
                            consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.REGULAR, "Geben sie bitte an, wie breit das Grundstück ist (in Meter): ");
                            values[i] = input.nextDouble();
                        }
                    }

                } catch (InputMismatchException e) {
                    consoleOutPut(ConsoleUtilities.colors.RED, ConsoleUtilities.textStyle.REGULAR, "Bitte geben sie nur ");
                    consoleOutPut(ConsoleUtilities.colors.RED, ConsoleUtilities.textStyle.BOLD, "Zahlen ");
                    consoleOutPutLine(ConsoleUtilities.colors.RED, ConsoleUtilities.textStyle.REGULAR, "an! ");

                    input.next();
                    continue;
                }

                if (values[i] <= 0) {
                    consoleOutPut(ConsoleUtilities.colors.RED, ConsoleUtilities.textStyle.REGULAR, "Bitte geben sie nur Werte an, die ");
                    consoleOutPut(ConsoleUtilities.colors.BLUE, ConsoleUtilities.textStyle.BOLD, "größer als 0 ");
                    consoleOutPutLine(ConsoleUtilities.colors.RED, ConsoleUtilities.textStyle.REGULAR, "sind!");
                } else {
                    // Erhöht i, falls die Eingabe des grade erfragten Wertes richtig ist.
                    i++;
                }
            } while (i < 3);

            return values;
        }

    }

}
