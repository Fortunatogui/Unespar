import javax.swing.JOptionPane;

public class Ex4{

    public static void main(String[] args) {
     
        String valor = JOptionPane.showInputDialog("Forneca um numero");
        int valor1 = Integer.parseInt(valor);

        for(int i = 0; i <= 10; i++){

        int tabuada = valor1 * i;
        JOptionPane.showMessageDialog(null, "4 * " + i + " = " + tabuada); 

        }
    }
}
