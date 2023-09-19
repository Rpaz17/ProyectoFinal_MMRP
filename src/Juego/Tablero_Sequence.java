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
    public int numerocoartas = 7;
    public int posArreglo = 0;
    public JLabel time;
    public JLabel ucj;
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

    public Tablero_Sequence(DatosUsuario datos, Login login, JLabel Turnos, Juego juego, JPanel mano, JLabel timer, JLabel ucj) {

        this.datos = datos;
        this.login = login;
        this.juego = juego;
        this.txtAreaEliminados = txtAreaEliminados;
        this.Turnos = Turnos;
        this.ArregloUsuarios = datos.getListaUsuarios();
        AsignarFichas();
        time = timer;
        this.ucj = ucj;
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
                                        Resaltador(casillaSeleccionada.personajeActual);
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

    public boolean cumple(int x) {
        return x != 0;
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
                if (fichas[filanueva][columananueva].personajeActual.NombreCarta.contains("Ficha")) {
                    VerificarSecuencia(filanueva, columananueva, ganador);
                }
            } else {
                casillaSeleccionada.setPersonaje(null);
            }

            return;
        }
    }

    public boolean Eliminar(int x) {
        for (int filas = 0; filas < 10; filas++) {
            for (int c = 0; c < 10; c++) {
                if (fichas[filas][c].personajeActual.RangoCarta == x) {
                    return false;
                }
            }
        }
        return true;
    }

    // Se verifican los rangos de las fichas y gana quien tenga mayor rango (con excepciones)
    public Personajes EmpezarBatalla(Personajes atacante, Personajes defensor) {
        if (atacante.RangoCarta == defensor.RangoCarta) {
            ArregloUsuarios.get(posArreglo).obtenerMazoPersonal().remove(atacante);
            seconds = 0;
            Resaltador(null);
            atacante.NombreCarta = ArregloUsuarios.get(posArreglo).getColorFicha();
            ucj.setIcon(atacante.getIcon());
            atacante.setNose();
            atacante.loadIcon();
            return atacante;
        }

        if (atacante.RangoCarta == 1000) {
            ArregloUsuarios.get(posArreglo).obtenerMazoPersonal().remove(atacante);
            seconds = 0;
            Resaltador(null);
            atacante.NombreCarta = ArregloUsuarios.get(posArreglo).getColorFicha();
            ucj.setIcon(atacante.getIcon());
            atacante.setNose();
            atacante.loadIcon();
            return atacante;
        }

        if (defensor.RangoCarta == 100) {
            if (Eliminar(atacante.RangoCarta) == true) {
                JOptionPane.showMessageDialog(null, "Se removio la carta " + atacante.NombreCarta + "de tu mazo");
                ArregloUsuarios.get(posArreglo).obtenerMazoPersonal().remove(atacante);
                return defensor;
            } else {
                JOptionPane.showMessageDialog(null, "Todavia puedes usar esta carta, no puedes eliminarla");
            }
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

    public void AsignarFichas() {
        ArrayList<String> coloresFicha = new ArrayList<>();
        coloresFicha.add("FichaAnaranjada");
        coloresFicha.add("FichaAzul");
        coloresFicha.add("FichaRoja");
        coloresFicha.add("FichaVerde");
        coloresFicha.add("FichaRosada");
        coloresFicha.add("FichaAmarilla");
        coloresFicha.add("FichaMorada");
        coloresFicha.add("FichaNegra");
        Random variablerandom = new Random();
        for (Usuarios cantidadusuarios : ArregloUsuarios) {
            int numeroR = variablerandom.nextInt(coloresFicha.size());
            cantidadusuarios.setColorFicha(coloresFicha.get(numeroR));

            coloresFicha.remove(numeroR);
        }
    }

    public void VerificarSecuencia(int fila, int columna, Personajes ficha) {
        int contadorSecuencia = 0;
        // Verificar secuencia vertical hacia arriba
        for (int up = fila - 1; up >= 0 && fichas[up][columna].getPersonaje() == ficha; up--) {
            contadorSecuencia++;
        }

        // Verificar secuencia vertical hacia abajo
        for (int down = fila + 1; down < 10 && fichas[down][columna].getPersonaje() == ficha; down++) {
            contadorSecuencia++;
        }

        if (contadorSecuencia >= 4) {
            JOptionPane.showMessageDialog(null, "Secuencia vertical de 5 fichas encontrada");
            ArregloUsuarios.get(posArreglo).IncrementarPuntos(3);
            this.setVisible(false);
            menu.setVisible(true);
            return; // Terminar la función si se encuentra una secuencia
        }

        contadorSecuencia = 0; // Reiniciar contador

        // Verificar secuencia horizontal hacia la izquierda
        for (int left = columna - 1; left >= 0 && fichas[fila][left].getPersonaje() == ficha; left--) {
            contadorSecuencia++;
        }

        // Verificar secuencia horizontal hacia la derecha
        for (int right = columna + 1; right < 10 && fichas[fila][right].getPersonaje() == ficha; right++) {
            contadorSecuencia++;
        }

        if (contadorSecuencia >= 4) {
            JOptionPane.showMessageDialog(null, "Secuencia horizontal de 5 fichas encontrada");
            ArregloUsuarios.get(posArreglo).IncrementarPuntos(3);
            this.setVisible(false);
            menu.setVisible(true);
        }

        // Verificar secuencias en diagonales (desde la esquina superior izquierda hacia la esquina inferior derecha)
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 6; c++) {
                int contador = 0;
                for (int i = 0; i < 5; i++) {
                    if (fichas[f + i][c + i].getPersonaje() == ficha) {
                        contador++;
                        if (contador >= 5) {
                            contadorSecuencia++;
                            JOptionPane.showMessageDialog(null, "Se completató una secuencia de 5 en diagonal");
                            ArregloUsuarios.get(posArreglo).IncrementarPuntos(3);
                            this.setVisible(false);
                            menu.setVisible(true);
                            break; // Romper si se encuentra una secuencia
                        }
                    } else {
                        contador = 0; // Reiniciar contador si la ficha no coincide
                    }
                }
            }
        }

        // Verificar secuencias en diagonales (desde la esquina superior derecha hacia la esquina inferior izquierda)
        for (int fil = 0; fil < 6; fil++) {
            for (int col = 9; col >= 4; col--) {
                int contador = 0;
                for (int i = 0; i < 5; i++) {
                    if (fichas[fil + i][col - i].getPersonaje() == ficha) {
                        contador++;
                        if (contador >= 5) {
                            contadorSecuencia++;
                            JOptionPane.showMessageDialog(null, "Se completató una secuencia de 5 en diagonal");
                            ArregloUsuarios.get(posArreglo).IncrementarPuntos(3);
                            this.setVisible(false);
                            menu.setVisible(true);
                            break; // Romper si se encuentra una secuencia
                        }
                    } else {
                        contador = 0; // Reiniciar contador si la ficha no coincide
                    }
                }
            }
        }

//        int contadorsecuencia = 0;
//
//        if (fila > 0) {
//            up = fila - 1;
//            do {
//                if (fichas[up][columna].getPersonaje() == ficha) {
//                    contadorsecuencia += 1;
//                }
//                up =up - 1;
//            } while (fichas[up][columna].getPersonaje() == ficha || contadorsecuencia < 5);
//        }
//        if (fila < 9) {
//            down = fila + 1;
//            do {
//                if (fichas[down][columna].getPersonaje() == ficha) {
//                    contadorsecuencia += 1;
//                }
//                down =down + 1;
//            } while (fichas[down][columna].getPersonaje() == ficha || contadorsecuencia < 5);
//        }
//        if (contadorsecuencia == 5) {
//            JOptionPane.showMessageDialog(null, "5 fichas en secuencia vertical ");
//        } else {
//            contadorsecuencia = 0;
//            if (columna > 0) {
//                left = columna - 1;
//                do {
//                    if (fichas[fila][left].getPersonaje() == ficha) {
//                        contadorsecuencia += 1;
//                    }
//                    left = left -  1;
//                } while (fichas[fila][left].getPersonaje() == ficha || contadorsecuencia < 5);
//            }
//            if (columna < 9) {
//                right = columna + 1;
//                do {
//                    if (fichas[fila][right].getPersonaje() == ficha) {
//                        contadorsecuencia += 1;
//                    }
//                    right = right + 1;
//                } while (fichas[fila][right].getPersonaje() == ficha || contadorsecuencia < 5);
//            }
//            if (contadorsecuencia == 5) {
//                JOptionPane.showMessageDialog(null, "5 fichas en secuencia horizontal ");
//            } else {
//
//            }
//        }
    }

    public void cambiarTurnoMazo() {
        JOptionPane.showMessageDialog(null, "Turno de: " + ArregloUsuarios.get(posArreglo).getUsername());
        tiempo.stop();
        seconds = 120;
        tiempo.start();
        if (ArregloUsuarios.get(posArreglo).obtenerMazoPersonal().size() < numerocoartas) {
            addCard(ArregloUsuarios.get(posArreglo).obtenerMazoPersonal());
        }
        for (int i = 0; i < ArregloUsuarios.get(posArreglo).obtenerMazoPersonal().size(); i++) {
            Usuarios usuario = ArregloUsuarios.get(posArreglo);
            Personajes carta = (Personajes) usuario.obtenerMazoPersonal().get(i);
            fichas[10][i].setPersonaje(carta);
        }
    }

    // resalta la carta que esta en el mazo que desea ser colocada 
    public void Resaltador(Personajes carta) {
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

    // añade una nueva carta al mazo luego de cada juego
    public void addCard(ArrayList<Personajes> aja) {
        Random implicito = new Random();
        int numRandom = implicito.nextInt(ArregloCartasMazo.size());
        aja.add(ArregloCartasMazo.get(numRandom));
        ArregloCartasMazo.remove(numRandom);
        JOptionPane.showMessageDialog(null, "Se añadió una nueva carta a su baraja.");
    }

}
