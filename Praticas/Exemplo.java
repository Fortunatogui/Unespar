import java.util.Scanner;

public class Exemplo {

    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha um animal que pode ser criado em casa:");
        System.out.println("Leao\nCavalo\nHomem\nMacaco\nMorcego\nBaleia\nAvestruz\nPinguim\nPato\nAguia\nTartaruga\nCrocodilo\nCobra\n");
        System.out.println("Responda com 1 para sim e 0 para nao as perguntas a seguir.\n");

        if (isYes(scanner, "E mamifero?")) {
            if (isYes(scanner, "E quadrupede?")) {
                if (isYes(scanner, "E carnivoro?")) {
                    System.out.println("Entao o animal escolhido foi o leao");
                } else if (isYes(scanner, "E herbivoro?")) {
                    System.out.println("Entao o animal escolhido foi o cavalo");
                }
            } else if (isYes(scanner, "E bipede?")) {
                if (isYes(scanner, "E onivoro?")) {
                    System.out.println("Entao o animal escolhido foi o homem");
                } else if (isYes(scanner, "E frugivoro?")) {
                    System.out.println("Entao o animal escolhido foi o macaco");
                }
            } else if (isYes(scanner, "E voador?")) {
                System.out.println("Entao o animal escolhido foi o morcego");
            } else if (isYes(scanner, "E aquatico?")) {
                System.out.println("Entao o animal escolhido foi a baleia");
            }
        } else if (isYes(scanner, "E uma ave?")) {
            if (isYes(scanner, "E nao-voadora?")) {
                if (isYes(scanner, "E tropical?")) {
                    System.out.println("Entao o animal escolhido foi o avestruz");
                } else if (isYes(scanner, "E polar?")) {
                    System.out.println("Entao o animal escolhido foi o pinguim");
                }
            } else if (isYes(scanner, "E nadadora?")) {
                System.out.println("Entao o animal escolhido foi o pato");
            } else if (isYes(scanner, "E de rapina?")) {
                System.out.println("Entao o animal escolhido foi a aguia");
            }
        } else if (isYes(scanner, "E um reptil?")) {
            if (isYes(scanner, "Possui casco?")) {
                System.out.println("Entao o animal escolhido foi a tartaruga");
            } else if (isYes(scanner, "E carnivoro?")) {
                System.out.println("Entao o animal escolhido foi o crocodilo");
            } else if (isYes(scanner, "Sem patas?")) {
                System.out.println("Entao o animal escolhido foi a cobra");
            }
        }

        scanner.close();
    }

    private static boolean isYes(Scanner scanner, String question) {
        System.out.print(question + " ");
        int response = scanner.nextInt();
        return response == 1;
    }
}
