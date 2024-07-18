import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CadastroDeContas extends JFrame {
    private JLabel label1, label2, label3, label4;
    private JButton btConsultar, btGravar, btLimpar;
    private JTextField tfNumeroConta, tfTitular, tfSaldo;
    private JRadioButton rbContaCorrente, rbPoupanca;
    private ButtonGroup buttonGroup;

    public static void main(String[] args){
        JFrame frame = new CadastroDeContas();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public CadastroDeContas(){
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes(){
        setTitle("Cadastro de Contas");
        setBounds(250, 50, 800, 400);
        setBackground(new Color(150, 150, 150));
        label1 = new JLabel("Numero da conta");
        label2 = new JLabel("Titular");
        label3 = new JLabel("Saldo");
        label4 = new JLabel("Tipo de conta: ");
        btConsultar = new JButton("Consultar");
        btGravar = new JButton("Gravar");
        btLimpar = new JButton("Limpar");
        tfNumeroConta = new JTextField();
        tfSaldo = new JTextField();
        tfTitular = new JTextField();
        label4 = new JLabel("Selecione o tipo de conta:");
        rbContaCorrente = new JRadioButton("Conta Corrente");
        rbPoupanca = new JRadioButton("Poupança");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rbContaCorrente);
        buttonGroup.add(rbPoupanca);
        setLayout(null);
        label1.setBounds(10, 15, 120, 20);
        label2.setBounds(10, 40, 60, 20);
        label3.setBounds(10, 65, 60, 20);
        btConsultar.setBounds(10, 150, 90, 30);
        btGravar.setBounds(105,150, 75, 30);
        btLimpar.setBounds(185, 150, 75, 30);
        tfNumeroConta.setBounds(120, 15, 255, 20);
        tfSaldo.setBounds(120, 65, 255, 20);
        tfTitular.setBounds(120, 40, 255, 20);
        rbContaCorrente.setBounds(10, 100, 140, 30);
        label4.setBounds(10, 80, 180, 40);
        rbPoupanca.setBounds(10, 120, 140, 40);
        rbContaCorrente.setSelected(true);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(btConsultar);
        add(btGravar);
        add(btLimpar);
        add(tfNumeroConta);
        add(tfSaldo);
        add(tfTitular);
        add(rbContaCorrente);
        add(rbPoupanca);
    }

    public void definirEventos(){     
        btLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                tfNumeroConta.setText(" ");
                tfSaldo.setText(" ");
                tfTitular.setText(" ");
                rbContaCorrente.setSelected(true);
            }
        });
        
        btGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    String ct_tipo = " ";
                    if(tfNumeroConta.getText().equals(" ")){
                        JOptionPane.showMessageDialog(null,"O campo não pode estar vazio");
                        tfNumeroConta.requestFocus();
                    } else if (tfTitular.getText().equals(" ")){
                        JOptionPane.showMessageDialog(null, "O campo não pode estar vazio");
                        tfTitular.requestFocus();
                    } else if (tfSaldo.getText().equals(" ")){
                        JOptionPane.showMessageDialog(null, "O campo não pode estar vazio");
                        tfSaldo.requestFocus();
                    } else {
                        if(rbContaCorrente.isSelected()){
                            ct_tipo = "Conta corrente";
                        } else if (rbPoupanca.isSelected()){
                            ct_tipo = "Poupança";
                        }
                    }
                JOptionPane.showMessageDialog(null, tfNumeroConta.getText() + " " + tfTitular.getText() + " " + ct_tipo + " " + tfSaldo.getText());
            }
        });

        btConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String resp = JOptionPane.showInputDialog(null, "Digite o codigo da conta: ");
                JOptionPane.showMessageDialog(null, "Código a ser consultado: " + resp);
                
            }
        });
    }
}