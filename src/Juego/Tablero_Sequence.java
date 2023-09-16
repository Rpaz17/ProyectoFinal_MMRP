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

public class Tablero_Sequence extends JPanel {

    // Atributos
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
    public int numerocoartas = 5;
    public int posArreglo = 0;
    public JLabel time;
    int seconds;
    private Image tablero;
    Timer tiempo;

    // Atributos adicionales para resaltar casillas con el mismo rango
    private Personajes cartaSeleccionada = null;
    private ArrayList<casillas> casillasValidas = new ArrayList<>();
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        int imageWidth = getWidth() / 10;
        int imageHeight = getHeight() / 10;
        g.drawImage(tablero, 0, 0, getWidth(), getHeight(), this);
        
    }
    
    public Tablero_Sequence(DatosUsuario datos, Login login, JLabel Turnos, Juego juego, JPanel mano, JLabel timer) {
        
        this.datos = datos;
        this.login = login;
        this.juego = juego;
        this.txtAreaEliminados = txtAreaEliminados;
        this.Turnos = Turnos;
        this.ArregloUsuarios = datos.getListaUsuarios();
        time = timer;

        // Definir un grid de 10 x 10 para las fichas
        setLayout(new GridLayout(10, 10));
        mano.setLayout(new GridLayout(1, 10));
        ImageIcon fondo = new ImageIcon("src/img/tablero1.png");
        
        tablero = fondo.getImage();

        // Creacion de Jlabels para las fichas en el Grid de 10 x 10, se definen los personajes con otro metodo
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
        
        tiempo = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seconds == 0) {
                    ((Timer) e.getSource()).stop(); // Detener el cronómetro cuando llegue a 0
                    
                    posArreglo++;
                    if (posArreglo >= ArregloUsuarios.size()) {
                        posArreglo = 0;
                    }
                    cambiarTurnoMazo();
                }
                
                seconds--;
                
                int minutos = seconds / 60;
                int segundos = seconds % 60;
                
                time.setText("Tiempo restante: " + String.format("%02d:%02d", minutos, segundos));
                
            }
        });
        // Agregar evento para aceptar los clics del mouse en el tablero
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                int row = -1, column = -1;  // valores negativos en caso de que no se seleccione la casilla correcta

                // Si se selecciona una casilla con un personaje
                if (SeSeleccionoCasilla == false) {
                    // Se obtienen las coordenadas de la casilla
                    for (int i = 0; i < 11; i++) {
                        for (int j = 0; j < 10; j++) {
                            // La casilla seleccionada es de la casilla actual
                            if (fichas[i][j].label == label) {
                                casillaSeleccionada = fichas[i][j];
                                // Comprobar que la casilla contenga un personaje y sea el turno correcto
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
                    // Si ya se seleccionó una casilla y se quiere mover a otro puesto
                } else {
                    for (int filas = 0; filas < 11; filas++) {
                        for (int columnas = 0; columnas < 10; columnas++) {
                            if (fichas[filas][columnas].label == label) {
                                // Si se selecciona una ficha del mismo bando se cambia a esa ficha
                                if (fichas[filas][columnas].personajeActual != null) {
                                    if (fichas[filas][columnas].personajeActual.FichaColocada == TrunoJugador) {
                                        // Actualizar casillas
                                        nose(casillaSeleccionada.personajeActual);
                                        casillaSeleccionada = fichas[filas][columnas];
                                        break;
                                    }
                                }
                                // Si se comprobó que el movimiento es válido se mueve a esa casilla seleccionada
                                if (ComprobarMovimientoValido(filas, columnas)) {
                                    moverCarta(filas, columnas);
                                    SeSeleccionoCasilla = false;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Las casillas válidas son de color verde.");
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
        
        CartasTableroColocar();
        RepartirCartas();
        cambiarTurnoMazo();
        
        setVisible(true);
    }
    
    private boolean ComprobarMovimientoValido(int fila, int columna) {
        return true;
    }

    // Se mueven los personajes
    private void moverCarta(int filanueva, int columananueva) {
        String TurnoDeUsuario = Turnos.getText();
        
        if (fichas[filanueva][columananueva].personajeActual != null) {
            
            Personajes ganador = EmpezarBatalla(casillaSeleccionada.personajeActual, fichas[filanueva][columananueva].personajeActual);
            
            if (ganador == null) {
            } else if (casillaSeleccionada.personajeActual == ganador) {
                casillaSeleccionada.setPersonaje(null);
                fichas[filanueva][columananueva].setPersonaje(ganador);
            } else {
                casillaSeleccionada.setPersonaje(null);
            }
            
            return;
        }
    }

    // Se verifican los rangos de las fichas y gana quien tenga mayor rango (con excepciones)
    public Personajes EmpezarBatalla(Personajes atacante, Personajes defensor) {
        if (atacante.RangoCarta == defensor.RangoCarta) {
            ArregloUsuarios.get(posArreglo).obtenerMazoPersonal().remove(atacante);
            seconds = 0;
            nose(null);
            return atacante;
        }
        return null;
    }
    
    public void CartasTableroColocar() {
        int hola = 0;
        for (int Fila = 0; Fila < 10; Fila++) {
            for (int Columna = 0; Columna < 10; Columna++) {
                fichas[Fila][Columna].setPersonaje(ArregloCartasTablero.get(hola));
                hola++;
            }
        }
    }
    
    public void RepartirCartas() {
        Random variablerandom = new Random();
        for (Usuarios cantidadusuarios : ArregloUsuarios) {
            for (int i = 0; i < numerocoartas; i++) {
                int numeroR = variablerandom.nextInt(ArregloCartasMazo.size());
                cantidadusuarios.obtenerMazoPersonal().add(ArregloCartasMazo.get(numeroR));
                ArregloCartasMazo.remove(numeroR);
            }
        }
    }
    
    public void cambiarTurnoMazo() {
        JOptionPane.showMessageDialog(null, "Turno de: " + ArregloUsuarios.get(posArreglo).getUsername());
        tiempo.stop();
        seconds = 120;
        tiempo.start();
        for (int i = 0; i < ArregloUsuarios.get(posArreglo).obtenerMazoPersonal().size(); i++) {
            Usuarios usuario = ArregloUsuarios.get(posArreglo);
            Personajes carta = (Personajes) usuario.obtenerMazoPersonal().get(i);
            fichas[10][i].setPersonaje(carta);
        }
    }
    
    public void nose(Personajes carta) {
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {
                if (fichas[f][c].isJaja() == true) {
                    fichas[f][c].highlightMove(false);
                }
            }
        }
        
        if (carta != null) {
            for (int f = 0; f < 10; f++) {
                for (int c = 0; c < 10; c++) {
                    if (carta.RangoCarta == fichas[f][c].personajeActual.RangoCarta) {
                        fichas[f][c].highlightMove(true);
                    }
                }
            }
        }
    }
}
