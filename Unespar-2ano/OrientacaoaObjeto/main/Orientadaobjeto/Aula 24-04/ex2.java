import javax.swing.JOptionPane;

public class ex2 {
    public static void main(String[] args){
        double[] resistencia = new double[4];
        double RE = 0;
        double maior;
        double menor;

        for(int i = 0; i < 4; i++){
            String r = JOptionPane.showInputDialog("Insira a resistencia: ");
            resistencia[i] = Double.parseDouble(r);
            RE += resistencia[i];
        }

        maior = resistencia[0];
        menor = resistencia[0];

        for(int i = 1; i < 4; i++){
            if(maior < resistencia[i]){
                maior = resistencia[i];
            }
            if(menor > resistencia[i]){
                menor = resistencia[i];
            }
        }

        JOptionPane.showMessageDialog(null, "Resistencia fornecida: " + resistencia[0] + ", "+ resistencia[1] + ", " + resistencia[2] + ", " + resistencia[3] + "\nA maior resistencia e: " + maior + "\nA menor resistencia e: " + menor + "\nResistencia Equivalente: " + RE);
    }
}