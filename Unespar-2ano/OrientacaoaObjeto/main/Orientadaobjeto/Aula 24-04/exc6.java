import javax.swing.JOptionPane;

public class exc6 {

    private static boolean palavraImpropria(String frase) {
        frase = frase.toLowerCase(); 
        return frase.contains("sexo") || frase.contains("sexual"); 
    }
    
    public static void main(String[] args) {
        String frase = JOptionPane.showInputDialog("Forneça uma frase:");
    
        if (palavraImpropria(frase)) {
            JOptionPane.showMessageDialog(null, "Conteúdo impróprio"); 
        } else {
            JOptionPane.showMessageDialog(null, "Conteúdo liberado"); 
        }
    }
}