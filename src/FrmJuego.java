import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {

    // Variables globales / Atributos
    private JTabbedPane tpJugadores;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;

    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();

    // Constructor de la clase
    public FrmJuego() {
        setTitle("Juego de Cartas");
        setSize(510, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        add(btnVerificar);

        // Definir una interfaz con varios paneles agrupados mediante pestañas
        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 45, 470, 200);
        add(tpJugadores);

        pnlJugador1 = new JPanel();
        tpJugadores.add("Martín Estrada Contreras", pnlJugador1);
        pnlJugador1.setBackground(new Color(0, 255, 0));
        pnlJugador1.setLayout(null);

        pnlJugador2 = new JPanel();
        tpJugadores.add("Raúl Vidal", pnlJugador2);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        // Eventos
        btnRepartir.addActionListener(evento -> {
            repartir();
        });

        btnVerificar.addActionListener(evento -> {
            verificar();
        });
    }

    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void verificar() {
    String gruposEncontrados = "";
    String escalerasEncontradas = "";

    switch (tpJugadores.getSelectedIndex()) {
        case 0:
            gruposEncontrados = jugador1.getGrupos();
            escalerasEncontradas = jugador1.getEscaleras();
            break;
        case 1:
            gruposEncontrados = jugador2.getGrupos();
            escalerasEncontradas = jugador2.getEscaleras();
            break;
    }

    String mensajeFinal = gruposEncontrados + "\n\n" + escalerasEncontradas;
    JOptionPane.showMessageDialog(null, mensajeFinal);
    }
}