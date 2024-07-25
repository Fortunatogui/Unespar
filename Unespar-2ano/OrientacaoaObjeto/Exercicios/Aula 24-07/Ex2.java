import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ex2 extends JFrame {
    JButton btMudarCor;
    JLabel imagemLabel;
    private ImageIcon imageIcon1;

    public static void main(String[] args) {
        JFrame janela = new Ex2();

        janela.setUndecorated(true);
        janela.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public Ex2() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setTitle("Mostra imagem");
        setSize(300, 200);
        setLayout(new GridBagLayout());

        btMudarCor = new JButton("Passe o mouse");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        gbc.weighty = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btMudarCor, gbc);

        imagemLabel = new JLabel();
        gbc.gridy = 1;
        add(imagemLabel, gbc);
    }

    public void definirEventos() {
        btMudarCor.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                String path = "lago.gif";
                imageIcon1 = new ImageIcon(getClass().getResource(path));
                if (imageIcon1 != null) {
                    imagemLabel.setIcon(imageIcon1);
                } else {
                    imagemLabel.setText("Imagem não encontrada");
                }
            }

            public void mouseExited(MouseEvent e) {
                imagemLabel.setIcon(null);
                imagemLabel.setText("");
            }
        });
    }
}
