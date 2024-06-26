import java.util.*;

public class Raid {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Character> disco1 = new ArrayList<>();
        List<Character> disco2 = new ArrayList<>();

        System.out.println("Digite a frase a ser recorrida: ");
        String frase = sc.nextLine();

        char[] letras = frase.toCharArray();

        for (int i = 0; i < letras.length; i++) {

            if (i % 2 == 0) {
                disco1.add(letras[i]);
            } else {
                disco2.add(letras[i]);
            }
        }

        System.out.println(disco1);
        System.out.println(disco2);

    }
    }
        