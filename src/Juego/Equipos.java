package Juego;

import java.util.ArrayList;


public class Equipos {
    
    int cantMaxima;
    int sequence;
    ArrayList<Usuarios> usuarios;
    
    public Equipos(int x){
        cantMaxima = x;
        this.usuarios = new ArrayList<>();
        sequence =0;
    }
    
    public void AddPlayer(Usuarios u){
        if (MaxCapacity()){
            return;
        }
        usuarios.add(u);
    }
    
    public boolean MaxCapacity(){
        return usuarios.size() == cantMaxima;
    }
    
    public int tam(){
        return usuarios.size();
    }
}
