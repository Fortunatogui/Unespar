import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiMenuPrincipal extends JFrame {
    private Container contentPane;
    private JMenuBar mnBarra;
    private JMenu mnArquivo, mnExemplos;
    private JMenuItem miSair, miBotao, miAreaDeTexto, miCaixaOpcao, miCombo, miDialogoMensagem, miLabel, miLista, miRadio, miBorderStyle1;
    private JMenuItem miConf, miDialOp, miBarrRol, miBarrPro, miFlay, miGrid, miGuiAba, miFrame, miPedi, miMasc, miBorderStyle2;

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
        miConf = new JMenuItem("Confirmacao");
        miDialOp = new JMenuItem("Dialogo Opcao");
        miBarrRol = new JMenuItem("Barra de Rolagem");
        miBorderStyle1 = new JMenuItem("Estilo da Borda");
        miBorderStyle2 = new JMenuItem("Estilo da Borda2");
        miBarrPro = new JMenuItem("Barra de Progresso");
        miFlay = new JMenuItem("Flay Layout");
        miGrid = new JMenuItem("Grid Layout");
        miGuiAba = new JMenuItem("Abas");
        miFrame = new JMenuItem("Frame Interno");
        miPedi = new JMenuItem("Pedido");
        miMasc = new JMenuItem("Mascara");

        mnArquivo.add(miSair);
        mnExemplos.add (miBotao);
        mnExemplos.add (miBorderStyle1);
        mnExemplos.add (miBorderStyle2);
        mnExemplos.add(miAreaDeTexto);
        mnExemplos.add(miCaixaOpcao);
        mnExemplos.add(miCombo);
        mnExemplos.add(miDialogoMensagem);
        mnExemplos.add(miLabel);
        mnExemplos.add(miLista);
        mnExemplos.add(miRadio);
        mnExemplos.add(miConf);
        mnExemplos.add(miDialOp);
        mnExemplos.add(miBarrRol);
        mnExemplos.add(miBarrPro);
        mnExemplos.add(miFlay);
        mnExemplos.add(miGrid);
        mnExemplos.add(miGuiAba);
        mnExemplos.add(miFrame);
        mnExemplos.add(miPedi);
        mnExemplos.add(miMasc);

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

        miConf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiDialogoConfirmacao confirmacao = new GuiDialogoConfirmacao();
                contentPane.removeAll();
                contentPane.add(confirmacao);
                contentPane.validate();
            }
        });

        miDialOp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiDialogoOpcao opcao = new GuiDialogoOpcao();
                contentPane.removeAll();
                contentPane.add(opcao);
                contentPane.validate();
            }
        });

        miBarrRol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiBarraRolagem rolagem = new GuiBarraRolagem();
                contentPane.removeAll();
                contentPane.add(rolagem);
                contentPane.validate();
            }
        });

        miBarrPro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiBarraProgresso progresso = new GuiBarraProgresso();
                contentPane.removeAll();
                contentPane.add(progresso);
                contentPane.validate();
            }
        });

        miFlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiFlayLayout3 layout3 = new GuiFlayLayout3();
                contentPane.removeAll();
                contentPane.add(layout3);
                contentPane.validate();
            }
        });

        miGrid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiGridLayout1 gridLayout1 = new GuiGridLayout1();
                contentPane.removeAll();
                contentPane.add(gridLayout1);
                contentPane.validate();
            }
        });

        miGuiAba.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiAbas abas = new GuiAbas();
                contentPane.removeAll();
                contentPane.add(abas);
                contentPane.validate();
            }
        });

        miFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiFrameInterno frameInterno = new GuiFrameInterno();
                contentPane.removeAll();
                contentPane.add(frameInterno);
                contentPane.validate();
            }
        });

        miPedi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiPedido Pedido = new GuiPedido();
                contentPane.removeAll();
                contentPane.add(Pedido);
                contentPane.validate();
            }
        });

        miMasc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiMascara mascara = new GuiMascara();
                contentPane.removeAll();
                contentPane.add(mascara);
                contentPane.validate();
            }
        });

        miBorderStyle1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiBorderStyle Estilo1 = new GuiBorderStyle();
                contentPane.removeAll();
                contentPane.add(Estilo1);
                contentPane.validate();
            }
        });

        miBorderStyle2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiBorderStyle2 Estilo2 = new GuiBorderStyle2();
                contentPane.removeAll();
                contentPane.add(Estilo2);
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