import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ex1 extends JFrame {
    JButton btMudarCor;

    public static void main(String[] args) {
        JFrame janela = new Ex1();

        janela.setUndecorated(true);
        janela.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public Ex1() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setTitle("Muda cor de botão");
        setSize(300, 200);
        setLayout(new GridBagLayout()); 

        btMudarCor = new JButton("Trocar");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        
        add(btMudarCor, gbc);
    }

    public void definirEventos() {
        btMudarCor.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btMudarCor.setBackground(Color.orange);
            }
            public void mouseExited(MouseEvent e) {
                btMudarCor.setBackground(Color.yellow);
            }
        });
    }
}
