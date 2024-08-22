import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraCampoMagnetico extends JFrame {

    // Constante para permeabilidade magnética do vácuo
    private static final double MU_0 = 4 * Math.PI * 1e-7;

    public CalculadoraCampoMagnetico() {
        // Configurações da janela
        setTitle("Calculadora de Campo Magnético");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Rótulos e caixas de opções
        JLabel currentLabel = new JLabel("Corrente (i) em A:");
        currentLabel.setBounds(20, 20, 150, 25);
        add(currentLabel);

        String[] currentOptions = {"1.0", "2.0", "3.0", "4.0", "5.0"};
        JComboBox<String> currentComboBox = new JComboBox<>(currentOptions);
        currentComboBox.setBounds(180, 20, 150, 25);
        add(currentComboBox);

        JLabel radius1Label = new JLabel("Raio R1 em m:");
        radius1Label.setBounds(20, 60, 150, 25);
        add(radius1Label);

        String[] radius1Options = {"2.0", "3.0", "4.0", "5.0"};
        JComboBox<String> radius1ComboBox = new JComboBox<>(radius1Options);
        radius1ComboBox.setBounds(180, 60, 150, 25);
        add(radius1ComboBox);

        JLabel radius2Label = new JLabel("Raio R2 em m:");
        radius2Label.setBounds(20, 100, 150, 25);
        add(radius2Label);

        String[] radius2Options = {"1.0", "1.5", "2.0", "2.5"};
        JComboBox<String> radius2ComboBox = new JComboBox<>(radius2Options);
        radius2ComboBox.setBounds(180, 100, 150, 25);
        add(radius2ComboBox);

        // Botão para calcular
        JButton calculateButton = new JButton("Calcular");
        calculateButton.setBounds(130, 140, 120, 25);
        add(calculateButton);

        // Ação do botão
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double current = Double.parseDouble((String) currentComboBox.getSelectedItem());
                double radius1 = Double.parseDouble((String) radius1ComboBox.getSelectedItem());
                double radius2 = Double.parseDouble((String) radius2ComboBox.getSelectedItem());

                // Cálculo do campo magnético
                double B1 = calcularCampoMagnetico(current, Math.PI, radius1);
                double B2 = calcularCampoMagnetico(current, Math.PI / 2, radius2);

                double campoMagneticoTotal = B1 + 2 * B2;

                // Exibe o resultado
                JOptionPane.showMessageDialog(null, "Campo Magnético Total: " + campoMagneticoTotal + " T");
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
