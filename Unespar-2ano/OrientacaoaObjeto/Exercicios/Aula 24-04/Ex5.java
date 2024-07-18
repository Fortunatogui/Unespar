import javax.swing.JOptionPane;

public class Ex5 {

    public static void main(String[] args) {
        // Recebe a frase do usuário
        String frase = JOptionPane.showInputDialog("Digite uma frase:");

        // Inverte a frase
        String fraseInvertida = "";
        for (int i = frase.length() - 1; i >= 0; i--) {
            fraseInvertida += frase.charAt(i);
        }

        // Exibe a frase invertida
        JOptionPane.showMessageDialog(null, "Frase invertida: " + fraseInvertida);
    }
}

    

