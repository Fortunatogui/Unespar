import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelefericoSwing extends JFrame {

    // Constantes
    private static final double GRAVIDADE = 9.8;
    private static final double PESO_MEDIO = 80;
    private static final int MAX_PESSOAS = 10;
    private static final double PESO_TELEFERICO = 200;

    // Componentes da interface gráfica
    private JTextField numPessoasField;
    private JTextField anguloField;
    private JTextField aceleracaoField;
    private JTextField diferencaTensoesField;
    private JTextField tensaoMaximaField;
    private JTextField aceleracaoMaximaField;

    // Construtor
    public TelefericoSwing() {
        setTitle("Calculadora de Teleférico");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel contextoLabel = new JLabel("Exercicio");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(contextoLabel, gbc);

        gbc.gridwidth = 1;

        JLabel numPessoasLabel = new JLabel("Número de Pessoas:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(numPessoasLabel, gbc);

        numPessoasField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(numPessoasField, gbc);

        JLabel anguloLabel = new JLabel("θ:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(anguloLabel, gbc);

        anguloField = new JTextField(5);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(anguloField, gbc);

        JLabel aceleracaoLabel = new JLabel("Aceleração constante:");
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(aceleracaoLabel, gbc);

        aceleracaoField = new JTextField(5);
        gbc.gridx = 5;
        gbc.gridy = 1;
        add(aceleracaoField, gbc);

        JLabel diferencaTensoesLabel = new JLabel("Diferença entre as trações de trecho contíguo:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(diferencaTensoesLabel, gbc);

        diferencaTensoesField = new JTextField(15);
        diferencaTensoesField.setEditable(false);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(diferencaTensoesField, gbc);

        JButton resetButton = new JButton("Reset");
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(resetButton, gbc);

        JLabel tensaoMaximaLabel = new JLabel("Tensão Máxima:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(tensaoMaximaLabel, gbc);

        tensaoMaximaField = new JTextField(10);
        tensaoMaximaField.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        add(tensaoMaximaField, gbc);

        JLabel aceleracaoMaximaLabel = new JLabel("Aceleração Máxima:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(aceleracaoMaximaLabel, gbc);

        aceleracaoMaximaField = new JTextField(10);
        aceleracaoMaximaField.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        add(aceleracaoMaximaField, gbc);

        JButton finalizarButton = new JButton("Finalizar");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        add(finalizarButton, gbc);

        resetButton.addActionListener(e -> resetFields());
        finalizarButton.addActionListener(e -> System.exit(0));

        JButton calcularButton = new JButton("Calcular");
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        add(calcularButton, gbc);

        calcularButton.addActionListener(e -> calcularTensoesEDiferencas());
    }

    private void resetFields() {
        numPessoasField.setText("");
        anguloField.setText("");
        aceleracaoField.setText("");
        diferencaTensoesField.setText("");
        tensaoMaximaField.setText("");
        aceleracaoMaximaField.setText("");
    }

    private void calcularTensoesEDiferencas() {
        try {
            int numPessoas = Integer.parseInt(numPessoasField.getText());
            double angulo = Double.parseDouble(anguloField.getText());
            double aceleracao = Double.parseDouble(aceleracaoField.getText());

            if (numPessoas < 0 || numPessoas > MAX_PESSOAS) {
                throw new IllegalArgumentException("Número de pessoas deve estar entre 0 e " + MAX_PESSOAS);
            }
            if (angulo <= 0 || angulo >= 81) {
                throw new IllegalArgumentException("Ângulo deve estar entre 0 e 81 graus");
            }

            angulo = Math.toRadians(angulo);
            double massaCabine = PESO_TELEFERICO + numPessoas * PESO_MEDIO;
            double T1 = calcularTensao(massaCabine, aceleracao, angulo);
            double T2 = calcularTensao(massaCabine + PESO_MEDIO, aceleracao, angulo); // Adiciona o peso médio de uma pessoa para variar a massa
            double T3 = calcularTensao(massaCabine + 2 * PESO_MEDIO, aceleracao, angulo); // Adiciona o peso médio de duas pessoas para variar a massa

            double diferencaTensao1 = T2 - T1;
            double diferencaTensao2 = T3 - T2;
            double aceleracaoMaxima = calcularAceleracaoMaxima(T3, massaCabine, angulo);

            diferencaTensoesField.setText(String.format("%.2f / %.2f", diferencaTensao1, diferencaTensao2));
            tensaoMaximaField.setText(String.format("%.2f", T3));
            aceleracaoMaximaField.setText(String.format("%.2f", aceleracaoMaxima));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formatação: " + ex.getMessage(), "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calcularTensao(double massa, double aceleracao, double angulo) {
        return massa * (aceleracao + GRAVIDADE * Math.sin(angulo));
    }

    private double calcularAceleracaoMaxima(double tensaoMaxima, double massaTotal, double angulo) {
        return (tensaoMaxima / massaTotal) - GRAVIDADE * Math.sin(angulo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelefericoSwing().setVisible(true));
    }
}
