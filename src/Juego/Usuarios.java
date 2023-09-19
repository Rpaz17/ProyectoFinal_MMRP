/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import java.util.ArrayList;

/**
 *
 * @author Azalia
 */
public class Usuarios {
    
    private String username,password, Nombre, colorFicha;
    private int puntos;
    private ArrayList<Personajes> MazoUsuario;
    
    public Usuarios(String username,String password, String Nombre){
      this.colorFicha = ""; 
        this.username=username;
       this.password=password;
       this.Nombre=Nombre;
       this.puntos=0;
       this.MazoUsuario=new ArrayList<>();
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setColorFicha(String colorFicha){
        this.colorFicha = colorFicha;
    }
    
    public String getColorFicha(){
        return colorFicha;
    }
    
    public String infoCreatePalayer(){
        return "\nUsername: "+username+"\nNombre: "+Nombre;  
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public ArrayList obtenerMazoPersonal(){
        return MazoUsuario;
    }

    public void IncrementarPuntos(int cantidad) {
       puntos = puntos + (cantidad - (puntos % cantidad));
    }
    
    
}
