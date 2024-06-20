import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiMenuPrincipal extends JFrame {
    private Container contentPane;
    private JMenuBar mnBarra;
    private JMenu mnArquivo, mnExemplos;
    private JMenuItem miSair, miBotao, miAreaDeTexto, miCaixaOpcao, miCombo, miDialogoMensagem, miLabel, miLista, miRadio;

    public GuiMenuPrincipal() {
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setTitle("Menu Principal");
        setBounds(0, 0, 800, 600);
        contentPane = getContentPane();
        mnBarra = new JMenuBar();
        mnArquivo = new JMenu("Arquivo");
        mnArquivo.setMnemonic ('A') ;
        mnExemplos = new JMenu("Exemplos");
        mnExemplos.setMnemonic('E');
        String path = "sair.png";
        miSair = new JMenuItem("Sair", new ImageIcon(getClass().getResource(path)));
        miSair.setAccelerator (KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        miBotao = new JMenuItem("Botão");
        miAreaDeTexto = new JMenuItem("Area de Texto");
        miCaixaOpcao = new JMenuItem("Caixa de Opção");
        miCombo = new JMenuItem("Combo");
        miDialogoMensagem = new JMenuItem("Dialogo");
        miLabel = new JMenuItem("Label");
        miLista = new JMenuItem("Lista");
        miRadio = new JMenuItem("Radio");

        mnArquivo.add(miSair);
        mnExemplos.add (miBotao);
        mnExemplos.add(miAreaDeTexto);
        mnExemplos.add(miCaixaOpcao);
        mnExemplos.add(miCombo);
        mnExemplos.add(miDialogoMensagem);
        mnExemplos.add(miLabel);
        mnExemplos.add(miLista);
        mnExemplos.add(miRadio);

        mnBarra.add (mnArquivo);
        mnBarra.add(mnExemplos);
        setJMenuBar (mnBarra);
    }

    private void definirEventos() {
        miSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        miBotao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiBotao botao = new GuiBotao();
                contentPane.removeAll();
                contentPane.add(botao);
                contentPane.validate();
            }
        });

        miAreaDeTexto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiAreaDeTexto texto = new GuiAreaDeTexto();
                contentPane.removeAll();
                contentPane.add(texto);
                contentPane.validate();
            }
        });

        miCaixaOpcao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiCaixaOpcao caixaOpcao = new GuiCaixaOpcao();
                contentPane.removeAll();
                contentPane.add(caixaOpcao);
                contentPane.validate();
            }
        });

        miCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiCombo combo = new GuiCombo();
                contentPane.removeAll();
                contentPane.add(combo);
                contentPane.validate();
            }
        });

        miDialogoMensagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiDialogoMensagem dialogo = new GuiDialogoMensagem();
                contentPane.removeAll();
                contentPane.add(dialogo);
                contentPane.validate();
            }
        });

        miLabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiLabel label = new GuiLabel();
                contentPane.removeAll();
                contentPane.add(label);
                contentPane.validate();
            }
        });

        miLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiLista lista = new GuiLista();
                contentPane.removeAll();
                contentPane.add(lista);
                contentPane.validate();
            }
        });

        miRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiRadio radio = new GuiRadio();
                contentPane.removeAll();
                contentPane.add(radio);
                contentPane.validate();
            }
        });



    }

    public static void abrir () {
        GuiMenuPrincipal frame = new GuiMenuPrincipal();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((tela.width - frame.getSize().width) / 2,
                (tela.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }
}