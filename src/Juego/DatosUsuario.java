/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class DatosUsuario {

    private ArrayList<Usuarios> ListaUsuarios;

    public String UsuarioLogeado;
    public String SegundoUsuario;

    RandomAccessFile usuarios;

    public DatosUsuario() {
        ListaUsuarios = new ArrayList<>();
        try {

            usuarios = new RandomAccessFile("src/RegistroJugadores/Regsitro.emp", "rw");

        } catch (IOException e) {
            System.out.println("No deberia de pasar esto.");
        }
    }
    //Buscar Usuarios si existentes

    public Usuarios buscarUsuario(String username, int i) {
        if (i < ListaUsuarios.size()) {
            Usuarios usuario = ListaUsuarios.get(i);

            if (usuario != null) {
                if (usuario.getUsername().equals(username)) {
                    return usuario;
                }
            }

            return buscarUsuario(username, i + 1);
        }
        return null;
    }

    public void addJUgadorArray() throws IOException {
        usuarios.seek(0);
        while (usuarios.getFilePointer() < usuarios.length()) {
            String UsuarioActual = usuarios.readUTF();
            String UsuarioContra = usuarios.readUTF();
            String Usuarionombre = usuarios.readUTF();
            Usuarios obj = new Usuarios(UsuarioActual, UsuarioContra, Usuarionombre);
            agregarUsuario(obj);

        }
    }

    public void AddJugadorDocument(String Usuario, String Contra,String Nombre) throws IOException {

        usuarios.seek(usuarios.length());

        usuarios.writeUTF(Usuario);
        usuarios.writeUTF(Contra);
        usuarios.writeUTF(Nombre);

    }

    //Agregar Usuarios
    public boolean agregarUsuario(Usuarios usuario) {
        Usuarios aux = buscarUsuario(usuario.getUsername(), 0);

        if (aux == null) {
            ListaUsuarios.add(usuario);
            return true;
        }

        return false;
    }


    public ArrayList<Usuarios> getListaUsuarios() {
        return ListaUsuarios;
    }


}
