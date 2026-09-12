import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 40;

    private Random r = new Random();
    private Carta[] cartas = new Carta[TOTAL_CARTAS]; //la mano del jugador

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicionX = MARGEN + TOTAL_CARTAS * DISTANCIA;
        for (Carta carta : cartas) {
            posicionX -= DISTANCIA;
            carta.mostrar(pnl, posicionX, MARGEN);
            System.out.println(carta.getNombre() + " de " + carta.getPinta());
        }
        pnl.repaint();

    }

    public String getGrupos() {
        String respuesta = "No se encontraron grupos";

        // arreglo de contadores de cartas por el nombre
        int[] contadores = new int[NombreCarta.values().length];

        boolean hayGrupos = false;
        for (Carta carta : cartas) {
            int posicion = carta.getNombre().ordinal();
            contadores[posicion]++;
            if (!hayGrupos && contadores[posicion] >= 2)
                hayGrupos = true;
        }

        if (hayGrupos) {
            respuesta = "Se encontraron los siguientes grupos:\n";
            for (int i=0;i<contadores.length;i++) {
            //for (int contador : contadores) {
                //if (contador >= 2) {
                if (contadores[i] >= 2) {
                    //respuesta += Grupo.values()[contador] + " de "+ NombreCarta.values()[] + "\n";
                    respuesta += Grupo.values()[contadores[i]] + " de "+ NombreCarta.values()[i] + "\n";
                }   
            }
        }
    
        return respuesta;
    }

    public String getEscaleras() {
        String respuesta = "No se encontraron escaleras";
        boolean hayEscalera = false;
        String detallesEscalera = "";
        Pinta[] pintas = Pinta.values();

    // Recorrer cada pinta individualmente
        for (int p = 0; p < pintas.length; p++) {
            Pinta pintaActual = pintas[p]; //trebol

        // Guardar los valores de las cartas de la pinta actual
            int[] valores = new int[TOTAL_CARTAS];//arreglo de 10 posiciones
            int cantidadCartas = 0;

            for (int i = 0; i < TOTAL_CARTAS; i++) {
                if (cartas[i].getPinta() == pintaActual) // Revisar la mano carta por carta cual es la pinta
                {
                    valores[cantidadCartas] = cartas[i].getNombre().ordinal() + 1;
                    cantidadCartas++;
                }
            }

        // Si hay al menos 2 cartas de la misma pinta, evaluamos
            if (cantidadCartas >= 2) {
            // Ordenamos usando el metodo burbuja que nos enseñaron en logica 1
                for (int i = 0; i < cantidadCartas - 1; i++) {
                    for (int j = 0; j < cantidadCartas - i - 1; j++) {
                        if (valores[j] > valores[j + 1]) {
                            int aux = valores[j];
                            valores[j] = valores[j + 1];
                            valores[j + 1] = aux;
                        }
                    }
                }
            }
            //Buscar secuencias consecutivas 
            int inicio = 0;
            int contadorConsecutivas = 1;

            for (int i = 0; i < cantidadCartas - 1; i++) {
                if (valores[i + 1] == valores[i] + 1) {
                    contadorConsecutivas++;
                } 
                else if (valores[i + 1] != valores[i]) { // Si son números no consecutivos se corta la secuencia
                    if (contadorConsecutivas >= 2) {
                            hayEscalera = true;
                            detallesEscalera += "Escalera de " + pintaActual + ": desde " + 
                                            NombreCarta.values()[valores[inicio] - 1] + 
                                            " hasta " + NombreCarta.values()[valores[i] - 1] + "\n";
                    }
                    contadorConsecutivas = 1;
                    inicio = i + 1;
                }
            }

            // Validar la última secuencia al finalizar el bucle
            if (contadorConsecutivas >= 2) {
                hayEscalera = true;
                detallesEscalera += "Escalera de " + pintaActual + ": desde " + 
                                    NombreCarta.values()[valores[inicio] - 1] + 
                                    " hasta " + NombreCarta.values()[valores[cantidadCartas - 1] - 1] + "\n";
            }
        }
    
    if (hayEscalera) {
        respuesta = "Se encontraron las siguientes escaleras:\n" + detallesEscalera;
    }

    return respuesta;
}
}



