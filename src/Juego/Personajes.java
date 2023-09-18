/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Azalia
 */
public class Personajes {

    private int currentRow;
    private int currentColumn;
    String NombreCarta;
    int RangoCarta;
    boolean FichaColocada;

    ImageIcon icono;
    ImageIcon iconoEscondido;

    //Contstructor
    public Personajes(String nombreCarta, int rangoCarta, boolean fichaColocada, String path) {
        this.NombreCarta = nombreCarta;
        this.RangoCarta = rangoCarta;
        this.FichaColocada = fichaColocada;

        try {
            Image resizedImg = resizeImage(ImageIO.read(new File("src/imagenes/oculto.png")), 85, 85);
            iconoEscondido = new ImageIcon(resizedImg);
        } catch (Exception e) {
            iconoEscondido = null;
        }

        try {
            Image newImg = resizeImage(ImageIO.read(new File(path)), 55, 55);
            icono = new ImageIcon(newImg);
        } catch (Exception e) {
            icono = null;
        }

        loadIcon();
    }

    public ImageIcon getIcon (){
        return icono;
    }
    
    public void setNose(){
        FichaColocada = true;
        RangoCarta = -1;
    }
    
    public void loadIcon() {
        String filename;
         if (!FichaColocada) {
            filename = "src/img/" + NombreCarta.replace(" ", "") + ".jpeg";
        } else {
            filename = "src/img/" + NombreCarta.replace(" ", "") + ".jpeg";
        }

        try {

            Image newImg = resizeImage(ImageIO.read(new File(filename)), 60, 60);
            icono = new ImageIcon(newImg);
        } catch (Exception e) {
            icono = null;
        }
    }

    private Image resizeImage(Image img, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public static ArrayList<Personajes> getCartasTablero() {
        ArrayList<Personajes> Carta_Tablero = new ArrayList<Personajes>();

Carta_Tablero.add(new Personajes("Esquina_1.", 100, true, null));
Carta_Tablero.add(new Personajes("Picas_2.", 2, true, null));
Carta_Tablero.add(new Personajes("Picas_3.", 3, true, null));
Carta_Tablero.add(new Personajes("Picas_4.", 4, true, null));
Carta_Tablero.add(new Personajes("Picas_5.", 5, true, null));
Carta_Tablero.add(new Personajes("Picas_6.", 6, true, null));
Carta_Tablero.add(new Personajes("Picas_7.", 7, true, null));
Carta_Tablero.add(new Personajes("Picas_8.", 8, true, null));
Carta_Tablero.add(new Personajes("Picas_9.", 9, true, null));
Carta_Tablero.add(new Personajes("Esquina_2.", 100, true, null));

Carta_Tablero.add(new Personajes("Treboles_6.", 36, true, null));
Carta_Tablero.add(new Personajes("Treboles_5.", 35, true, null));
Carta_Tablero.add(new Personajes("Treboles_4.", 34, true, null));
Carta_Tablero.add(new Personajes("Treboles_3.", 33, true, null));
Carta_Tablero.add(new Personajes("Treboles_2.", 32, true, null));
Carta_Tablero.add(new Personajes("Corazones_A.", 11, true, null));
Carta_Tablero.add(new Personajes("Corazones_K.", 123, true, null));
Carta_Tablero.add(new Personajes("Corazones_Q.", 122, true, null));
Carta_Tablero.add(new Personajes("Corazones_10.", 20, true, null));
Carta_Tablero.add(new Personajes("Picas_10.", 10, true, null));

Carta_Tablero.add(new Personajes("Treboles_7.", 37, true, null));
Carta_Tablero.add(new Personajes("Picas_A.", 1, true, null));
Carta_Tablero.add(new Personajes("Diamantes_2.", 22, true, null));
Carta_Tablero.add(new Personajes("Diamantes_3.", 23, true, null));
Carta_Tablero.add(new Personajes("Diamantes_4.", 24, true, null));
Carta_Tablero.add(new Personajes("Diamantes_5.", 25, true, null));
Carta_Tablero.add(new Personajes("Diamantes_6.", 26, true, null));
Carta_Tablero.add(new Personajes("Diamantes_7.", 27, true, null));
Carta_Tablero.add(new Personajes("Corazones_9.", 19, true, null));
Carta_Tablero.add(new Personajes("Picas_Q.", 112, true, null));

Carta_Tablero.add(new Personajes("Treboles_8.", 38, true, null));
Carta_Tablero.add(new Personajes("Picas_K.", 113, true, null));
Carta_Tablero.add(new Personajes("Treboles_6.", 36, true, null));
Carta_Tablero.add(new Personajes("Treboles_5.", 35, true, null));
Carta_Tablero.add(new Personajes("Treboles_4.", 34, true, null));
Carta_Tablero.add(new Personajes("Treboles_3.", 33, true, null));
Carta_Tablero.add(new Personajes("Treboles_2.", 32, true, null));
Carta_Tablero.add(new Personajes("Diamantes_8.", 28, true, null));
Carta_Tablero.add(new Personajes("Corazones_8.", 18, true, null));
Carta_Tablero.add(new Personajes("Picas_K.", 113, true, null));

Carta_Tablero.add(new Personajes("Treboles_9.", 39, true, null));
Carta_Tablero.add(new Personajes("Picas_Q.", 112, true, null));
Carta_Tablero.add(new Personajes("Treboles_7.", 37, true, null));
Carta_Tablero.add(new Personajes("Corazones_6.", 16, true, null));
Carta_Tablero.add(new Personajes("Corazones_5.", 15, true, null));
Carta_Tablero.add(new Personajes("Corazones_4.", 14, true, null));
Carta_Tablero.add(new Personajes("Corazones_A.", 11, true, null));
Carta_Tablero.add(new Personajes("Diamantes_9.", 29, true, null));
Carta_Tablero.add(new Personajes("Corazones_7.", 17, true, null));
Carta_Tablero.add(new Personajes("Picas_A.", 1, true, null));

Carta_Tablero.add(new Personajes("Treboles_10.", 40, true, null));
Carta_Tablero.add(new Personajes("Picas_10.", 10, true, null));
Carta_Tablero.add(new Personajes("Treboles_8.", 38, true, null));
Carta_Tablero.add(new Personajes("Corazones_7.", 17, true, null));
Carta_Tablero.add(new Personajes("Corazones_2.", 12, true, null));
Carta_Tablero.add(new Personajes("Corazones_3.", 13, true, null));
Carta_Tablero.add(new Personajes("Corazones_K.", 123, true, null));
Carta_Tablero.add(new Personajes("Diamantes_10.", 30, true, null));
Carta_Tablero.add(new Personajes("Corazones_6.", 16, true, null));
Carta_Tablero.add(new Personajes("Diamantes_2.", 22, true, null));

Carta_Tablero.add(new Personajes("Treboles_Q.", 142, true, null));
Carta_Tablero.add(new Personajes("Picas_9.", 9, true, null));
Carta_Tablero.add(new Personajes("Treboles_9.", 39, true, null));
Carta_Tablero.add(new Personajes("Corazones_8.", 18, true, null));
Carta_Tablero.add(new Personajes("Corazones_9.", 19, true, null));
Carta_Tablero.add(new Personajes("Corazones_10.", 20, true, null));
Carta_Tablero.add(new Personajes("Corazones_Q.", 122, true, null));
Carta_Tablero.add(new Personajes("Diamantes_Q.", 132, true, null));
Carta_Tablero.add(new Personajes("Corazones_5.", 15, true, null));
Carta_Tablero.add(new Personajes("Diamantes_3.", 23, true, null));

Carta_Tablero.add(new Personajes("Treboles_K.", 143, true, null));
Carta_Tablero.add(new Personajes("Picas_8.", 8, true, null));
Carta_Tablero.add(new Personajes("Treboles_10.", 40, true, null));
Carta_Tablero.add(new Personajes("Treboles_Q.", 142, true, null));
Carta_Tablero.add(new Personajes("Treboles_K.", 143, true, null));
Carta_Tablero.add(new Personajes("Treboles_A.", 31, true, null));
Carta_Tablero.add(new Personajes("Diamantes_A.", 21, true, null));
Carta_Tablero.add(new Personajes("Diamantes_K.", 133, true, null));
Carta_Tablero.add(new Personajes("Corazones_4.", 14, true, null));
Carta_Tablero.add(new Personajes("Diamantes_4.", 24, true, null));

Carta_Tablero.add(new Personajes("Treboles_A.", 31, true, null));
Carta_Tablero.add(new Personajes("Picas_7.", 7, true, null));
Carta_Tablero.add(new Personajes("Picas_6.", 6, true, null));
Carta_Tablero.add(new Personajes("Picas_5.", 5, true, null));
Carta_Tablero.add(new Personajes("Picas_4.", 4, true, null));
Carta_Tablero.add(new Personajes("Picas_3.", 3, true, null));
Carta_Tablero.add(new Personajes("Picas_2.", 2, true, null));
Carta_Tablero.add(new Personajes("Corazones_2.", 12, true, null));
Carta_Tablero.add(new Personajes("Corazones_3.", 13, true, null));
Carta_Tablero.add(new Personajes("Diamantes_5.", 25, true, null));

Carta_Tablero.add(new Personajes("Esquina_3.", 100, true, null));
Carta_Tablero.add(new Personajes("Diamantes_A.", 21, true, null));
Carta_Tablero.add(new Personajes("Diamantes_K.", 133, true, null));
Carta_Tablero.add(new Personajes("Diamantes_Q.", 132, true, null));
Carta_Tablero.add(new Personajes("Diamantes_10.", 30, true, null));
Carta_Tablero.add(new Personajes("Diamantes_9.", 29, true, null));
Carta_Tablero.add(new Personajes("Diamantes_8.", 28, true, null));
Carta_Tablero.add(new Personajes("Diamantes_7.", 27, true, null));
Carta_Tablero.add(new Personajes("Diamantes_6.", 26, true, null));
Carta_Tablero.add(new Personajes("Esquina_4.", 100, true, null));


        return Carta_Tablero;
    }

    public static ArrayList<Personajes> getCartasMazo() {
        ArrayList<Personajes> Cartas_Mazo = new ArrayList<Personajes>();
Cartas_Mazo.add(new Personajes("Picas_2", 2, false, null));
Cartas_Mazo.add(new Personajes("Picas_3", 3, false, null));
Cartas_Mazo.add(new Personajes("Picas_4", 4, false, null));
Cartas_Mazo.add(new Personajes("Picas_5", 5, false, null));
Cartas_Mazo.add(new Personajes("Picas_6", 6, false, null));
Cartas_Mazo.add(new Personajes("Picas_7", 7, false, null));
Cartas_Mazo.add(new Personajes("Picas_8", 8, false, null));
Cartas_Mazo.add(new Personajes("Picas_9", 9, false, null));

Cartas_Mazo.add(new Personajes("Treboles_6", 36, false, null));
Cartas_Mazo.add(new Personajes("Treboles_5", 35, false, null));
Cartas_Mazo.add(new Personajes("Treboles_4", 34, false, null));
Cartas_Mazo.add(new Personajes("Treboles_3", 33, false, null));
Cartas_Mazo.add(new Personajes("Treboles_2", 32, false, null));
Cartas_Mazo.add(new Personajes("Corazones_A", 11, false, null));
Cartas_Mazo.add(new Personajes("Corazones_K", 123, false, null));
Cartas_Mazo.add(new Personajes("Corazones_Q", 122, false, null));
Cartas_Mazo.add(new Personajes("Corazones_10", 20, false, null));
Cartas_Mazo.add(new Personajes("Picas_10", 10, false, null));

Cartas_Mazo.add(new Personajes("Treboles_7", 37, false, null));
Cartas_Mazo.add(new Personajes("Picas_A", 1, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_2", 22, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_3", 23, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_4", 24, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_5", 25, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_6", 26, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_7", 27, false, null));
Cartas_Mazo.add(new Personajes("Corazones_9", 19, false, null));
Cartas_Mazo.add(new Personajes("Picas_Q", 112, false, null));

Cartas_Mazo.add(new Personajes("Treboles_8", 38, false, null));
Cartas_Mazo.add(new Personajes("Picas_K", 113, false, null));
Cartas_Mazo.add(new Personajes("Treboles_6", 36, false, null));
Cartas_Mazo.add(new Personajes("Treboles_5", 35, false, null));
Cartas_Mazo.add(new Personajes("Treboles_4", 34, false, null));
Cartas_Mazo.add(new Personajes("Treboles_3", 33, false, null));
Cartas_Mazo.add(new Personajes("Treboles_2", 32, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_8", 28, false, null));
Cartas_Mazo.add(new Personajes("Corazones_8", 18, false, null));
Cartas_Mazo.add(new Personajes("Picas_R", 113, false, null));

Cartas_Mazo.add(new Personajes("Treboles_9", 39, false, null));
Cartas_Mazo.add(new Personajes("Picas_Q", 112, false, null));
Cartas_Mazo.add(new Personajes("Treboles_7", 37, false, null));
Cartas_Mazo.add(new Personajes("Corazones_6", 16, false, null));
Cartas_Mazo.add(new Personajes("Corazones_5", 15, false, null));
Cartas_Mazo.add(new Personajes("Corazones_4", 14, false, null));
Cartas_Mazo.add(new Personajes("Corazones_A", 11, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_9", 29, false, null));
Cartas_Mazo.add(new Personajes("Corazones_7", 17, false, null));
Cartas_Mazo.add(new Personajes("Picas_A", 1, false, null));

Cartas_Mazo.add(new Personajes("Treboles_10", 40, false, null));
Cartas_Mazo.add(new Personajes("Picas_10", 10, false, null));
Cartas_Mazo.add(new Personajes("Treboles_8", 38, false, null));
Cartas_Mazo.add(new Personajes("Corazones_7", 17, false, null));
Cartas_Mazo.add(new Personajes("Corazones_2", 12, false, null));
Cartas_Mazo.add(new Personajes("Corazones_3", 13, false, null));
Cartas_Mazo.add(new Personajes("Corazones_K", 123, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_10", 30, false, null));
Cartas_Mazo.add(new Personajes("Corazones_6", 16, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_2", 22, false, null));

Cartas_Mazo.add(new Personajes("Treboles_Q", 142, false, null));
Cartas_Mazo.add(new Personajes("Picas_9", 9, false, null));
Cartas_Mazo.add(new Personajes("Treboles_9", 39, false, null));
Cartas_Mazo.add(new Personajes("Corazones_8", 18, false, null));
Cartas_Mazo.add(new Personajes("Corazones_9", 19, false, null));
Cartas_Mazo.add(new Personajes("Corazones_10", 20, false, null));
Cartas_Mazo.add(new Personajes("Corazones_Q", 122, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_Q", 132, false, null));
Cartas_Mazo.add(new Personajes("Corazones_5", 15, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_3", 23, false, null));

Cartas_Mazo.add(new Personajes("Treboles_K", 143, false, null));
Cartas_Mazo.add(new Personajes("Picas_8", 8, false, null));
Cartas_Mazo.add(new Personajes("Treboles_10", 40, false, null));
Cartas_Mazo.add(new Personajes("Treboles_Q", 142, false, null));
Cartas_Mazo.add(new Personajes("Treboles_K", 143, false, null));
Cartas_Mazo.add(new Personajes("Treboles_A", 31, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_A", 21, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_K", 133, false, null));
Cartas_Mazo.add(new Personajes("Corazones_4", 14, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_4", 24, false, null));

Cartas_Mazo.add(new Personajes("Treboles_A", 31, false, null));
Cartas_Mazo.add(new Personajes("Picas_7", 7, false, null));
Cartas_Mazo.add(new Personajes("Picas_6", 6, false, null));
Cartas_Mazo.add(new Personajes("Picas_5", 5, false, null));
Cartas_Mazo.add(new Personajes("Picas_4", 4, false, null));
Cartas_Mazo.add(new Personajes("Picas_3", 3, false, null));
Cartas_Mazo.add(new Personajes("Picas_2", 2, false, null));
Cartas_Mazo.add(new Personajes("Corazones_2", 12, false, null));
Cartas_Mazo.add(new Personajes("Corazones_3", 13, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_5", 25, false, null));

Cartas_Mazo.add(new Personajes("Diamantes_A", 21, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_K", 133, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_Q", 132, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_10", 30, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_9", 29, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_8", 28, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_7", 27, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_6", 26, false, null));

Cartas_Mazo.add(new Personajes("Treboles_J", 1000, false, null));
Cartas_Mazo.add(new Personajes("Picas_J", 1000, false, null));

Cartas_Mazo.add(new Personajes("Diamantes_6", 26, false, null));
Cartas_Mazo.add(new Personajes("Diamantes_6", 26, false, null));



        
        return Cartas_Mazo;
    }

    public String toString() {
        String nombreSinEtiquetas = NombreCarta.replaceAll("\\<.*?\\>", "");
        return nombreSinEtiquetas;
    }

}
