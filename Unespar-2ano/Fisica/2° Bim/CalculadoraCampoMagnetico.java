import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraCampoMagnetico extends JFrame {

    // Constante para permeabilidade magnética do vácuo
    private static final double MU_0 = 4 * Math.PI * 1e-7;

    public CalculadoraCampoMagnetico() {
        // Configurações da janela
        setTitle("Calculadora de Campo Magnético");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Rótulos e campos de texto
        JLabel correnteLabel = new JLabel("Corrente (i) em A:");
        correnteLabel.setBounds(20, 20, 150, 25);
        add(correnteLabel);

        JTextField correnteTextField = new JTextField();
        correnteTextField.setBounds(180, 20, 150, 25);
        add(correnteTextField);

        JLabel raio1Label = new JLabel("Raio R1 em m:");
        raio1Label.setBounds(20, 60, 150, 25);
        add(raio1Label);

        JTextField raio1TextField = new JTextField();
        raio1TextField.setBounds(180, 60, 150, 25);
        add(raio1TextField);

        JLabel raio2Label = new JLabel("Raio R2 em m:");
        raio2Label.setBounds(20, 100, 150, 25);
        add(raio2Label);

        JTextField raio2TextField = new JTextField();
        raio2TextField.setBounds(180, 100, 150, 25);
        add(raio2TextField);

        // Botão para calcular
        JButton calcularButton = new JButton("Calcular");
        calcularButton.setBounds(180, 150, 120, 25);
        add(calcularButton);

        // Rótulos para resultados
        JLabel resultadoCampoLabel = new JLabel("Campo Magnético Total:");
        resultadoCampoLabel.setBounds(20, 190, 200, 25);
        add(resultadoCampoLabel);

        JLabel resultadoCampoValor = new JLabel("");
        resultadoCampoValor.setBounds(230, 190, 200, 25);
        add(resultadoCampoValor);

        JLabel circunferenciaMaiorLabel = new JLabel("Circunferência Maior (R2):");
        circunferenciaMaiorLabel.setBounds(20, 220, 200, 25);
        add(circunferenciaMaiorLabel);

        JLabel circunferenciaMaiorValor = new JLabel("");
        circunferenciaMaiorValor.setBounds(230, 220, 200, 25);
        add(circunferenciaMaiorValor);

        JLabel quartosCircunferenciaLabel = new JLabel("Quartos Circunferência Menor (R1):");
        quartosCircunferenciaLabel.setBounds(20, 250, 250, 25);
        add(quartosCircunferenciaLabel);

        JLabel quartosCircunferenciaValor = new JLabel("");
        quartosCircunferenciaValor.setBounds(270, 250, 200, 25);
        add(quartosCircunferenciaValor);

        // Ação do botão
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtém os valores inseridos pelo usuário
                    double corrente = Double.parseDouble(correnteTextField.getText());
                    double raio1 = Double.parseDouble(raio1TextField.getText());
                    double raio2 = Double.parseDouble(raio2TextField.getText());

                    // Cálculo do campo magnético
                    double B1 = calcularCampoMagnetico(corrente, Math.PI, raio1);
                    double B2 = calcularCampoMagnetico(corrente, Math.PI / 2, raio2);

                    double campoMagneticoTotal = B1 + 2 * B2;

                    // Calcula a circunferência
                    double circunferenciaMaior = 2 * Math.PI * raio2;
                    double quartosCircunferenciaMenor = (Math.PI / 2) * raio1;

                    // Exibe os resultados
                    resultadoCampoValor.setText(String.format("%.6f T", campoMagneticoTotal));
                    circunferenciaMaiorValor.setText(String.format("%.6f m", circunferenciaMaior));
                    quartosCircunferenciaValor.setText(String.format("%.6f m", quartosCircunferenciaMenor));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.");
                }
            }
        });
    }

    // Método para calcular o campo magnético de um arco de circunferência
    private double calcularCampoMagnetico(double corrente, double angulo, double raio) {
        return (MU_0 * corrente * angulo) / (4 * Math.PI * raio);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraCampoMagnetico().setVisible(true);
            }
        });
    }
}
