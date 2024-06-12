
import java.util.Scanner;

public class ex3 {

    public static void main(String[] args) {
        // Declaração das variáveis
        String loginOriginal = "java8";
        String senhaOriginal = "java8";
        int tentativas = 3;

        // Criação do objeto Scanner para leitura de dados do usuário
        Scanner scanner = new Scanner(System.in);

        // Loop para solicitar login e senha até que o usuário acerte ou acabem as tentativas
        while (tentativas > 0) {
            // Solicita o login ao usuário
            System.out.println("Digite seu login:");
            String login = scanner.nextLine();

            // Solicita a senha ao usuário
            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();

            // Verifica se o login e a senha estão corretos
            if (login.equals(loginOriginal) && senha.equals(senhaOriginal)) {
                // Login e senha corretos, acesso liberado
                System.out.println("Acesso liberado!");
                break;
            } else {
                // Login ou senha incorretos, diminui o número de tentativas
                tentativas--;
                System.out.println("Dados incorretos. Você tem mais " + tentativas + " tentativas.");
            }
        }

        // Verifica se o usuário esgotou as tentativas
        if (tentativas == 0) {
            // Tentativas esgotadas, acesso negado
            System.out.println("Tentativas esgotadas. Acesso negado.");
        }
    }
}

