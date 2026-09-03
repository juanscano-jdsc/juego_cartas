import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 40;

    private Random r = new Random();
    private Carta[] cartas = new Carta[TOTAL_CARTAS];

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

}
