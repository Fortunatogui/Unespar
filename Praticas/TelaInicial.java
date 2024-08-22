import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Classe que exibe a tela inicial do jogo, é aqui que deve ser feita a execução do jogo
// então não testem a execução do jogo na classe PetShop.java ou em outra classe

public class TelaInicial extends JFrame {
    private ImageIcon fundoInicialGif;
    private JLabel fundoInicial;
    private JButton instBotao;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaInicial frame = new TelaInicial();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 500); // Definir o tamanho da janela
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize(); 
                // Centralizar a janela na tela
                frame.setLocation((tela.width - frame.getSize().width) / 2,
                        (tela.height - frame.getSize().height) / 2);
                frame.setVisible(true);
            }
        });
    }

    public TelaInicial() {
        inicializarComponentes();
    }

    // Inicializa os componentes da tela inicial do jogo para 
    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        // Carrega o GIF de fundo da tela inicial
        String path1 = "FundoInicial.gif";
        fundoInicialGif = new ImageIcon(getClass().getResource(path1));

        if (fundoInicialGif.getIconWidth() != -1) { // Verificar se a imagem foi carregada corretamente
            fundoInicial = new JLabel(fundoInicialGif);
            fundoInicial.setHorizontalAlignment(JLabel.CENTER);
            fundoInicial.setVerticalAlignment(JLabel.CENTER);

            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BorderLayout());
            imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margens ao painel de imagem
            imagePanel.setBackground(Color.BLACK); // Fundo do painel de imagem
            imagePanel.add(fundoInicial, BorderLayout.CENTER);

            gbc.gridx = 0; // Painel de imagem na horizontal
            gbc.gridy = 0;  // Painel de imagem na vertical
            gbc.gridwidth = 1; // Largura do painel de imagem
            gbc.gridheight = 1; // Altura do painel de imagem
            gbc.weightx = 1.0; // Peso do painel de imagem na horizontal
            gbc.weighty = 1.0; // Peso do painel de imagem na vertical
            gbc.anchor = GridBagConstraints.CENTER; // Alinhamento do painel de imagem
            add(imagePanel, gbc); // Adiciona o painel de imagem à janela
        } else {
            System.out.println("Imagem não encontrada: " + path1);
        }

        // Define o fundo da janela como preto
        getContentPane().setBackground(Color.BLACK);

        // Botão 'INSTRUÇÕES'
        instBotao = new JButton("INSTRUÇÕES");
        instBotao.setFont(new Font("Space Mono", Font.BOLD, 20)); // Define a fonte do texto do botão
        instBotao.setPreferredSize(new Dimension(600, 200)); // Define tamanho maior do botão
        instBotao.setBackground(new Color(128, 0, 128)); // Define a cor do botão para roxo
        instBotao.setForeground(Color.WHITE); // Define a cor do texto do botão para branco
        instBotao.setBorderPainted(false); // Remove a borda ao redor do texto
        instBotao.setFocusPainted(false); // Remove a borda de foco ao redor do texto

        gbc.gridx = 0; // Posição do botão na horizontal
        gbc.gridy = 1; // Posição do botão na vertical (abaixo do GIF)
        gbc.gridwidth = 2; // Largura do botão
        gbc.gridheight = 1; // Altura do botão
        gbc.weightx = 1.0; // Peso do botão na horizontal
        gbc.weighty = 1.0; // Peso do botão na vertical
        gbc.insets = new Insets(5, 0, 0, 0); // Espaço acima do botão
        gbc.anchor = GridBagConstraints.CENTER;
        add(instBotao, gbc);

        // Chama a tela de instruções ao clicar no botão 'INSTRUÇÕES'
        instBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaInstrucoes instrucoes = new TelaInstrucoes();
                instrucoes.setVisible(true);
            }
        });
    }
}
