import java.awt.*;
import javax.swing.*;

    public class GuiBorderStyle extends JPanel {

    private JButton btSuperior, btInferior, btEsquerda, btCentro, btDireita;

    public GuiBorderStyle() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout (new BorderLayout());
        JButton btSuperior = new JButton("Superior");
        JButton btInferior = new JButton("Inferior");
        JButton btEsquerda = new JButton("Esquerda");
        JButton btCentro = new JButton("Centro");
        JButton btDireita = new JButton("Direita");



        add (btSuperior, BorderLayout.NORTH);
        add (btInferior, BorderLayout.SOUTH);
        add (btEsquerda, BorderLayout.WEST);
        add (btCentro, BorderLayout.CENTER);
        add (btDireita, BorderLayout.EAST);

    }
}