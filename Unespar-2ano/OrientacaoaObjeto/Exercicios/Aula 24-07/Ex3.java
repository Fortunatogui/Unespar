import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Ex3 extends JFrame {
    JButton btMostrar, btOcultar;
    JLabel imagemLabel;
    private ImageIcon imageIcon1;

    public static void main(String[] args) {
        JFrame janela = new Ex3();
        janela.setUndecorated(true);
        janela.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public Ex3() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setTitle("Mostrar/Ocultar Imagem");
        setSize(500, 500);
        setLayout(null); 

        btMostrar = new JButton("Mostrar");
        btOcultar = new JButton("Ocultar");

        btMostrar.setBounds(200, 400, 100, 30);
        btOcultar.setBounds(320, 400, 100, 30);

        imagemLabel = new JLabel();
        imagemLabel.setBounds(0, 0, 50, 50); 

        add(btMostrar);
        add(btOcultar);
        add(imagemLabel);

        String path = "lago.gif";
        imageIcon1 = new ImageIcon(getClass().getResource(path));
    }

    public void definirEventos() {
        btMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int x = rand.nextInt(450); 
                int y = rand.nextInt(350); 

                imagemLabel.setBounds(x, y, 50, 50);
                imagemLabel.setIcon(imageIcon1);
            }
        });

        btOcultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imagemLabel.setIcon(null);
            }
        });
    }
}