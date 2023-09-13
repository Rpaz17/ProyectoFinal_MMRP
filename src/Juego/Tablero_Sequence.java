package Juego;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Tablero_Sequence extends JPanel{
    //Atributos
    Usuarios usuario;
    DatosUsuario datos;
    Login login;
    Menu menu;
    Juego juego;
    ArrayList<Personajes> ArregloCartasTablero = Personajes.getCartasTablero();
    ArrayList<Personajes> ArregloCartasMazo = Personajes.getCartasMazo();
    ArrayList<Usuarios> ArregloUsuarios;
    private JTextArea txtAreaEliminados;
    private JLabel Turnos;
    private boolean SeguirJugando = true;
    private boolean SeSeleccionoCasilla = false;
    private casillas casillaSeleccionada;
    private casillas[][] fichas;
    private boolean TrunoJugador = false;
    private boolean HayGanador = false;
    public int numerocoartas=5;

    
    private Image tablero;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        int imageWidth = getWidth() / 10;
        int imageHeight = getHeight() / 10;
        g.drawImage(tablero, 0, 0, getWidth(), getHeight(), this);
    }
    
    public Tablero_Sequence(DatosUsuario datos, Login login, JLabel Turnos, Juego juego, JPanel mano) {
        
        this.datos = datos;
        this.login = login;
        this.juego = juego;
        this.txtAreaEliminados = txtAreaEliminados;
        this.Turnos = Turnos;
        this.ArregloUsuarios=datos.getListaUsuarios();

        //Definir un grid de 10 x 10 para las fichas
        setLayout(new GridLayout(10, 10));
        mano.setLayout(new GridLayout(1,10));
        ImageIcon fondo = new ImageIcon("src/img/tablero1.png");
        
        tablero = fondo.getImage();

        //Creacion de Jlabels para las fichas en el Grid de 10 x 10, se definen los personajes con otro metodo
        fichas = new casillas[11][10];
        for (int filas = 0; filas < 10; filas++) {
            for (int columnas = 0; columnas < 10; columnas++) {
                casillas ficha = new casillas(filas, columnas, null);
                fichas[filas][columnas] = ficha;
                add(fichas[filas][columnas].label);  
            }
        }
        
        for (int columnas = 0; columnas < 10; columnas++) {
                casillas ficha = new casillas(10, columnas, null);
                fichas[10][columnas] = ficha;
                mano.add(fichas[10][columnas].label);  
            }
        
        //Agregar evento para aceptar los clics del mouse en el tablero
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                int row = -1, column = -1;  //valores negativos en caso de que no se seleccione la casilla correcta

                //Si se selecciona una casilla con un personaje
                if (SeSeleccionoCasilla == false) { 
                    //Se obtienen las coordenadas de la casilla
                    for (int i = 0; i < 11; i++) {
                        for (int j = 0; j < 10; j++) {
                            // La casilla seleccionada es de la casilla actual
                            if (fichas[i][j].label == label) {                         
                                casillaSeleccionada = fichas[i][j];
                                ////Comprobar que la casilla contnga un personaje y sea el turno correcto
                                if (casillaSeleccionada.personajeActual != null && casillaSeleccionada.personajeActual.FichaColocada == TrunoJugador) {
                                    SeSeleccionoCasilla = true;  
                                    
                                    break;
                                } else {
                                    casillaSeleccionada = null;
                                    SeSeleccionoCasilla = false;
                                    break;
                                }
                            }
                        }
                    }
                //si ya se selecciono una casilla y se quiere mover a otro puesto
                } else { 
                    for (int filas = 0; filas < 11; filas++)  {
                        for (int columnas = 0; columnas < 10; columnas++) {
                            if (fichas[filas][columnas].label == label) {
                                // Si se selecciona una  ficha del mismo bando se cambia a esa ficha 
                                if (fichas[filas][columnas].personajeActual != null) {
                                    if (fichas[filas][columnas].personajeActual.FichaColocada == TrunoJugador) {
                                        // Actualizar casillas  
                                      
                                        casillaSeleccionada = fichas[filas][columnas];
                                      
                                        break;
                                    }
                                }
                                //si se comprobo que el movimiento es valido se mueve a esa casilla seleccionada
                                if (ComprobarMovimientoValido(filas, columnas) == true) {
                                    moverPersonaje(filas, columnas);
                                } else{
                                    JOptionPane.showMessageDialog(null, "LAS CASILLAS A LAS QUE PUEDES MOVER TU FICHA ESTAN EN VERDE.");
                                }
                            }
                            
                        }
                    } 
                }
            }
        };

        // Agregar el MouseListener a las casillas
        for (int filas = 0; filas < 11; filas++) {
            for (int columnas = 0; columnas < 10; columnas++) {
                fichas[filas][columnas].label.addMouseListener(mouseAdapter);
            }
        }

      CartasTableroColocar ();
      RepartirCartas();
      mostarCartasMazo();
      
        setVisible(true);
    }
    
   
    
    private boolean ComprobarMovimientoValido(int fila, int columna) {
 return true;
    }
    //Revisar todas las casilla y resaltar los movimientos validos
 
    //se mueven los personajes
    private void moverPersonaje(int filanueva, int columananueva) {
        String TurnoDeUsuario = Turnos.getText();
    // Se verifica que haya un personaje para empezar el combate
    if (fichas[filanueva][columananueva].personajeActual != null) {
        Personajes ganador = EmpezarBatalla(casillaSeleccionada.personajeActual, fichas[filanueva][columananueva].personajeActual);
       String mensajeBatalla = "" +casillaSeleccionada.personajeActual.toString() + " vs " + fichas[filanueva][columananueva].personajeActual.toString() + "\nGanador: " + (ganador != null ? ganador.toString() : "Empate"+"    ");
        JOptionPane.showMessageDialog(null, mensajeBatalla);

        // Se eliminan las dos fichas si eran del mismo rango
        if (ganador == null) {
            casillaSeleccionada.setPersonaje(null);
            fichas[filanueva][columananueva].setPersonaje(null);
        } else if (casillaSeleccionada.personajeActual == ganador) {                
            casillaSeleccionada.setPersonaje(null);
            fichas[filanueva][columananueva].setPersonaje(ganador);                
        } else {
            casillaSeleccionada.setPersonaje(null);
        }
            
        
        return;
    }

    // No había un personaje en esa casilla, así que solo se actualiza la posición
    Personajes personaje = casillaSeleccionada.personajeActual;
    casillaSeleccionada.setPersonaje(null);

    // Mover la imagen a la nueva posición
    fichas[filanueva][columananueva].setPersonaje(personaje);        
}

// Se verifican los rangos de las fichas y gana quien tenga mayor rango (con excepciones)
public Personajes EmpezarBatalla(Personajes atacante, Personajes defensor) {
 
    if (atacante.RangoCarta==defensor.RangoCarta) {
        return atacante;
    }
        return null;
    }

public void CartasTableroColocar (){
    int hola=0;
    for (int Fila = 0; Fila < 10; Fila++) {
        
        for (int Columna = 0; Columna < 10; Columna++) {
            
            fichas[Fila][Columna].setPersonaje(ArregloCartasTablero.get(hola));
        hola++;
        }
        
    }
}

public void RepartirCartas(){
    Random variablerandom=new Random();
    for (Usuarios cantidadusuarios: ArregloUsuarios){
        for (int i = 0; i < numerocoartas; i++) {
            int numeroR=variablerandom.nextInt(ArregloCartasMazo.size());
            cantidadusuarios.obtenerMazoPersonal().add(ArregloCartasMazo.get(numeroR));
            ArregloCartasMazo.remove(numeroR);
        }
    }   
}

public void mostarCartasMazo(){
    for (int i = 0; i < numerocoartas ; i++) {
        Usuarios hola=ArregloUsuarios.get(0);
        Personajes hi=(Personajes) hola.obtenerMazoPersonal().get(i);
        fichas[10][i].setPersonaje(hi);
    }
}







}
    
    
    

    
    
    
    
    
    
    

    
    
    
  
    
 

   
    
   
    


