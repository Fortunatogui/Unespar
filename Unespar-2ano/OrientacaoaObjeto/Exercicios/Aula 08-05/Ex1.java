import javax.swing.JOptionPane;

public class Ex1 {

    public static void main(String[] args) {

        int[] notas = new int[5];

        for (int i = 0; i < notas.length; i++) {
            String notaStr = JOptionPane.showInputDialog("Digite a " + (i + 1) + "ª nota:");
            notas[i] = Integer.parseInt(notaStr);
        }

        for (int i = 0; i < notas.length; i++) {
            for (int j = i + 1; j < notas.length; j++) {
                if (notas[i] < notas[j]) {
                    int temp = notas[i];
                    notas[i] = notas[j];
                    notas[j] = temp;
                }
            }
        }

        double media = 0;
        for (int nota : notas) {
            media += nota;
        }
        media /= notas.length;

        System.out.println("Notas em ordem decrescente: ");
        for (int nota : notas) {
            System.out.println(String.valueOf(nota));
        }
        System.out.println("Media aritmetica: " + media);
    }
}
