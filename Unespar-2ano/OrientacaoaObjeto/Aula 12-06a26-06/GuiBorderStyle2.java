import java.awt.*;
import javax.swing.*;

public class GuiBorderStyle2 extends JPanel {
    
    private JButton btSuperior, btEsquerda;
    private JTextField tfTexto;

    public GuiBorderStyle2() {

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout (new BorderLayout());
        JButton btSuperior = new JButton("Superior");
        JButton btEsquerda = new JButton("Esquerda");
        tfTexto = new JTextField();



        add (btSuperior, BorderLayout.NORTH);
        add (btEsquerda, BorderLayout.WEST);
        add(tfTexto);

    }
}
