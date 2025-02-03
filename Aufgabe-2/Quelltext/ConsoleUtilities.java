package main;

/**
 * <h2>Verbesserte Konsolenausgabe</h2>
 * <p>
 * Diese Klasse bietet zwei zusätzliche Funktionen, mit denen man farbig und in verschiedenen 
 * Stilen in die Konsole schreiben kann. Da wir Konsolenprogramme schreiben, ist diese Funktionalität 
 * besonders nützlich.
 * </p>
 * @author bauer
 * @version 1.0
 */

public class ConsoleUtilities {

    public static final String RESET = "\033[0m";  // Text Reset

    public enum colors { // Color Values
        BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE
    }
    public enum textStyle { // Style Values
        REGULAR, BOLD, UNDERLINE, BACKGROUND, HIGH_INTENSITY, BOLD_HIGH_INTENSITY, HIGH_INTENSITY_BACKGROUNDS
    }
    
    /**
     * Diese Methode gibt den modifizierten Text in der Konsole aus und formatiert ihn in einer Textzeile.
     * @param color Dieser Parameter legt die Farbe des Textes fest.
     * @param style Dieser Parameter legt den Stil des Textes fest.
     * @param text Der Text, der ausgegeben werden soll.
     */
    public static void consoleOutPutLine(colors color, textStyle style, String text) {
        System.out.println(getTextCode(color, style) + text + RESET);
    }

    /**
     * Diese Methode gibt den modifizierten Text in der Konsole aus.
     * @param color Dieser Parameter legt die Farbe des Textes fest.
     * @param style Dieser Parameter legt den Stil des Textes fest.
     * @param text Der Text, der ausgegeben werden soll.
     */
    public static void consoleOutPut(colors color, textStyle style, String text) {
        System.out.print(getTextCode(color, style) + text + RESET);
    }

    /**
     * Diese Methode erzeugt den Textcode, der die Farbe und den Stil festlegt.
     * @param color Dieser Parameter bestimmt, welche Farbe im Textcode verwendet wird.
     * @param style Dieser Parameter bestimmt, welchen Stil der Textcode haben soll.
     * @return Gibt den Textcode zurück.
     */
    private static String getTextCode(colors color, textStyle style) {
        String colorCode = "\033[";
        switch (style) {
            case REGULAR, UNDERLINE, BOLD -> {
                switch (style) {
                    case REGULAR -> colorCode = colorCode + "0;";
                    case BOLD -> colorCode = colorCode + "1;";
                    case UNDERLINE -> colorCode = colorCode + "4;";
                }

                switch (color) {
                    case BLACK ->   colorCode = colorCode + "30m";
                    case RED ->     colorCode = colorCode + "31m";
                    case GREEN ->   colorCode = colorCode + "32m";
                    case YELLOW ->  colorCode = colorCode + "33m";
                    case BLUE ->    colorCode = colorCode + "34m";
                    case PURPLE ->  colorCode = colorCode + "35m";
                    case CYAN ->    colorCode = colorCode + "36m";
                    case WHITE ->   colorCode = colorCode + "37m";
                }

            }
            case BACKGROUND -> {
                switch (color) {
                    case BLACK ->   colorCode = colorCode + "40m";
                    case RED ->     colorCode = colorCode + "41m";
                    case GREEN ->   colorCode = colorCode + "42m";
                    case YELLOW ->  colorCode = colorCode + "43m";
                    case BLUE ->    colorCode = colorCode + "44m";
                    case PURPLE ->  colorCode = colorCode + "45m";
                    case CYAN ->    colorCode = colorCode + "46m";
                    case WHITE ->   colorCode = colorCode + "47m";
                }
            }
            case HIGH_INTENSITY, BOLD_HIGH_INTENSITY -> {
                switch (style) {
                    case HIGH_INTENSITY -> colorCode = colorCode + "0;";
                    case BOLD_HIGH_INTENSITY -> colorCode = colorCode + "1;";
                }
                switch (color) {
                    case BLACK ->   colorCode = colorCode + "90m";
                    case RED ->     colorCode = colorCode + "91m";
                    case GREEN ->   colorCode = colorCode + "92m";
                    case YELLOW ->  colorCode = colorCode + "93m";
                    case BLUE ->    colorCode = colorCode + "94m";
                    case PURPLE ->  colorCode = colorCode + "95m";
                    case CYAN ->    colorCode = colorCode + "96m";
                    case WHITE ->   colorCode = colorCode + "97m";
                }
            }
            case HIGH_INTENSITY_BACKGROUNDS -> {
                switch (color) {
                    case BLACK ->   colorCode = colorCode + "0;100m";
                    case RED ->     colorCode = colorCode + "0;101m";
                    case GREEN ->   colorCode = colorCode + "0;102m";
                    case YELLOW ->  colorCode = colorCode + "0;103m";
                    case BLUE ->    colorCode = colorCode + "0;104m";
                    case PURPLE ->  colorCode = colorCode + "0;105m";
                    case CYAN ->    colorCode = colorCode + "0;106m";
                    case WHITE ->   colorCode = colorCode + "0;107m";
                }
            }
            default -> System.out.println("Fehler, diese Art an Farben oder/und Styles kann nicht wiedergeben werden!");

        }
        return colorCode;
    }


}
