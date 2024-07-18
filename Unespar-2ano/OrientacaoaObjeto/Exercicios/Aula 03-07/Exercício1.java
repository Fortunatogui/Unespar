import java.awt.event.*;
import javax.swing.*;

public class Exercício1 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Cadastro de Produtos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.add(new ProdutoPanel());
        frame.setVisible(true);
    }

    public static class ProdutoPanel extends JPanel {

        private JTextField tfCodigo;
        private JTextField tfDescricao;
        private JTextField tfPreco;
        private JButton btConsultar;
        private JButton btGravar;
        private JButton btLimpar;

        public ProdutoPanel() {
            inicializarComponentes();
            definirEventos();
        }

        private void inicializarComponentes() {
            setLayout(null);

            JLabel lbCodigo = new JLabel("Código do Produto:");
            JLabel lbDescricao = new JLabel("Descrição do Produto:");
            JLabel lbPreco = new JLabel("Preço:");

            tfCodigo = new JTextField();
            tfDescricao = new JTextField();
            tfPreco = new JTextField();

            btConsultar = new JButton("Consultar");
            btGravar = new JButton("Gravar");
            btLimpar = new JButton("Limpar");

            lbCodigo.setBounds(25, 20, 150, 25);
            tfCodigo.setBounds(175, 20, 150, 25);
            lbDescricao.setBounds(25, 60, 150, 25);
            tfDescricao.setBounds(175, 60, 150, 25);
            lbPreco.setBounds(25, 100, 150, 25);
            tfPreco.setBounds(175, 100, 150, 25);
            btConsultar.setBounds(25, 140, 100, 25);
            btGravar.setBounds(140, 140, 100, 25);
            btLimpar.setBounds(255, 140, 100, 25);

            add(lbCodigo);
            add(tfCodigo);
            add(lbDescricao);
            add(tfDescricao);
            add(lbPreco);
            add(tfPreco);
            add(btConsultar);
            add(btGravar);
            add(btLimpar);
        }

        private void definirEventos() {
            btGravar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (tfCodigo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Código do Produto não preenchido.");
                        tfCodigo.requestFocus();
                    } else if (tfDescricao.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Descrição do Produto não preenchida.");
                        tfDescricao.requestFocus();
                    } else if (tfPreco.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preço não preenchido.");
                        tfPreco.requestFocus();
                    } else {
                        String codigo = tfCodigo.getText();
                        String descricao = tfDescricao.getText();
                        String preco = tfPreco.getText();
                        JOptionPane.showMessageDialog(null, "Produto cadastrado:\nCódigo: " + codigo + "\nDescrição: " + descricao + "\nPreço: " + preco);
                    }
                }
            });

            btLimpar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tfCodigo.setText("");
                    tfDescricao.setText("");
                    tfPreco.setText("");
                }
            });

            btConsultar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String codigo = JOptionPane.showInputDialog("Digite o código do produto a ser consultado:");
                    JOptionPane.showMessageDialog(null, "Código consultado: " + codigo);
                }
            });
        }
    }
}
