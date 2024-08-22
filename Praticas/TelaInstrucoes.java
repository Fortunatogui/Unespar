import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Classe que exibe as instruções do jogo e chama a classe PetShop ao clicar em 'JOGAR'

public class TelaInstrucoes extends JFrame {
    private ImageIcon fundoInstGif;
    private JLabel fundoInst;
    private JButton jogarBotao;

    public TelaInstrucoes() {
        setTitle(" INSTRUÇÕES DO JOGO "); // Define o título da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tornar a janela sem bordas
        setUndecorated(true);

        // Abrir a janela em tela cheia
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints(); // Define o layout da janela
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        // Carrega o GIF de fundo da tela de instruções
        String path2 = "FundoInst.gif"; // Caminho para o novo GIF
        fundoInstGif = new ImageIcon(getClass().getResource(path2));

        if (fundoInstGif.getIconWidth() != -1) { // Verificar se a imagem foi carregada corretamente
            fundoInst = new JLabel(fundoInstGif);
            fundoInst.setHorizontalAlignment(JLabel.CENTER); // Alinhamento horizontal do GIF
            fundoInst.setVerticalAlignment(JLabel.CENTER); // Alinhamento vertical do GIF

            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BorderLayout());
            imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margens ao painel de imagem
            imagePanel.setBackground(Color.BLACK); // Define o fundo do painel de imagem como preto
            imagePanel.add(fundoInst, BorderLayout.CENTER); // Adiciona o GIF ao painel de imagem

            gbc.gridx = 0; // Posição do GIF na horizontal
            gbc.gridy = 0; // Posição do GIF na vertical
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0; 
            gbc.anchor = GridBagConstraints.CENTER;
            add(imagePanel, gbc);
        } else {
            System.out.println("Imagem não encontrada: " + path2);
        }

        // Define o fundo da janela como preto
        getContentPane().setBackground(Color.BLACK);

        // Botão 'JOGAR'
        jogarBotao = new JButton("JOGAR"); // Define o texto do botão
        jogarBotao.setFont(new Font("Space Mono", Font.BOLD, 20)); // Define a fonte do texto do botão
        jogarBotao.setPreferredSize(new Dimension(200, 100)); // Define o tamanho do botão
        jogarBotao.setBackground(new Color(128, 0, 128)); // Define a cor do botão para roxo
        jogarBotao.setForeground(Color.WHITE); // Define a cor do texto do botão para branco
        jogarBotao.setBorderPainted(false); // Remove a borda ao redor do texto
        jogarBotao.setFocusPainted(false); // Remove a borda de foco ao redor do texto

        gbc.gridx = 0; // Posição do botão na horizontal
        gbc.gridy = 1; // Posição do botão na vertical (abaixo do GIF)
        gbc.gridwidth = 2; // Largura do botão
        gbc.gridheight = 1; // Altura do botão
        gbc.weightx = 1.0; // Peso do botão na horizontal
        gbc.weighty = 1.0; // Peso do botão na vertical
        gbc.insets = new Insets(5, 0, 0, 0); // Espaço acima do botão
        gbc.anchor = GridBagConstraints.CENTER;
        add(jogarBotao, gbc);

        // Chama a classe do PetShop ao clicar em'JOGAR'
        jogarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de instruções
                PetShop.main(null); // Inicia o jogo chamando a classe PetShop
            }
        });
    }
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaInstrucoes().setVisible(true);
            }
        });
    }
}
