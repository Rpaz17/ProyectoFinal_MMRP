/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;



import java.awt.*;
import javax.swing.*;
public class casillas {
    
    JLabel label;
    Personajes personajeActual;
    int row;
    int column;
    boolean selected = false;
    boolean jaja ;
    
   
    public casillas(int row, int column, Personajes personajeActual) {        
        this.label = new JLabel();
        this.row = row;
        this.column = column;
        this.personajeActual = personajeActual;
        
    }

    public boolean isJaja() {
        return jaja;
    }
       
    public void highlightMove(boolean activar) {
        if (activar) {
            jaja = true;
            label.setBorder(BorderFactory.createLineBorder(Color.magenta,7));
        } else {
            // la variable jaja evalua que la ficha este coloreada o no, optimiza el proceso
            jaja = false;
            label.setBorder(null);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public void setPersonaje(Personajes personaje) {
        personajeActual = personaje;
        
        if (personaje == null) {
            label.setText("");
            label.setIcon(null);
        } else {            
             if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            }
            
        }        
    }
    
    public Personajes getPersonaje()
    {
        return personajeActual;
    }
    
    public void esconderCasilla(boolean esconder) {
        if (esconder) {
            if (personajeActual.iconoEscondido != null) {
                label.setIcon(personajeActual.iconoEscondido);
                label.repaint();
            } else {
                label.setIcon(null);
                label.setText("???");
            }  
        } else {
            if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            }
            else
                label.setText(personajeActual.NombreCarta);
        }
    } 

    
    
    
}
