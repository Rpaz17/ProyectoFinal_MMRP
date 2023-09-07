package Juego;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Botons extends JButton {

    boolean habilitado = false;
    int numero = 0;
    int fila = 0;
    int columna = 0;
    public String jugador;

    public Botons(int fil, int col, int num, String Tipo, String jugador) {
        super();
        fila = fil;
        columna = col;
        numero = num;
        this.jugador = jugador;
    }

    private static Icon resize(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
