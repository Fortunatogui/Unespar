import javax.swing.JOptionPane;

public class Ex1{

    public static void main(String[] args) {
        
        String nome = JOptionPane.showInputDialog("Insira o nome do Produto");
        String x = JOptionPane.showInputDialog("Insira o valor do Produto");

        if(x != null) {

            try {

                int valor = Integer.parseInt(x);

                if(valor >= 50 && valor < 200){

                    double desconto = (valor - (valor * 0.05));
                   JOptionPane.showMessageDialog(null, "O nome do produto era: \n" + nome + "\n O valor original era de: " + valor + "\n O valor com desconto aplicado foi de: " + desconto); 
                }else 

                if(valor >= 200 && valor < 500){

                    double desconto = (valor - (valor * 0.06));
                    JOptionPane.showMessageDialog(null, "O nome do produto era: " + nome + " O valor original era de" + valor + " O valor com desconto aplicado foi de: " + desconto); 

                }else 

                if(valor >= 500 && valor < 1000){
                    double desconto = (valor - (valor * 0.07));
                    JOptionPane.showMessageDialog(null, "O nome do produto era: " + nome + " O valor original era de" + valor + " O valor com desconto aplicado foi de: " + desconto); 

                }

                else {
                    double desconto = (valor - (valor * 0.08));
                    JOptionPane.showMessageDialog(null, "O nome do produto era: " + nome + " O valor original era de" + valor + " O valor com desconto aplicado foi de: " + desconto); 

                }

            } catch (NumberFormatException erro) {

                JOptionPane.showMessageDialog(null, "Digite apenas valores inteiros" + erro);
                
            }
        } else {

            JOptionPane.showMessageDialog(null, "Operacao Cancelada");

        }

        System.exit(0);

    }

}
