import javax.swing.*;
import java.util.Scanner;

public class JogoAnfibios {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        // Inicialização dos contadores
        int sapos = 0, ras = 0, tritoes = 0, salamandras = 0;

        JOptionPane.showMessageDialog(null, "<html><body><p>Pense em um animal da categoria Anfíbios.</p>"
                    + "<p>Escolha entre uma das seguintes opções: Sapos, Salamandras, Rãs, Tritões.</p>"
                    + "<p>Responda às perguntas com 'sim' ou 'não'.</p></body></html>");

        if (askQuestion(scanner, "Possuo uma pele sensível?")) {
            sapos++;
            ras++;
            tritoes++;
            salamandras++;
        }

        if (askQuestion(scanner, "Já apareci em um filme da Disney?")) {
            sapos++;
            salamandras++;
        }

        if (askQuestion(scanner, "Possuo uma pele lisa, úmida e muitas vezes brilhante?")) {
            ras++;
            tritoes++;
            salamandras ++;
        }

        if (askQuestion(scanner, "Posso ser confundido com um ser mitológico?")) {
            tritoes++;
        }

        if (askQuestion(scanner, "Sou da ordem Anura (Anfíbios sem cauda)?")) {
            ras++;
            sapos++;
        }

        if (askQuestion(scanner, "Possuo a capacidade de regenerar partes do corpo perdidas?")) {
            tritoes++;
            salamandras++;
        }

        // Determinação do animal mais provável com base nas contagens
        int maxCount = Math.max(Math.max(sapos, ras), Math.max(tritoes, salamandras));

        String result = "Desculpe, não vendemos esse animal.";

        if (sapos == maxCount) {
            result = "Você está pensando em um sapo!\n";
            JOptionPane.showMessageDialog(null, "Beijar um sapo não é recomendado devido ao risco potencial de exposição a doenças ou substâncias tóxicas. Se quiser alguem baixa o tinder");
        } else if (ras == maxCount) {
            result = "Você está pensando em uma rã!\n";
            JOptionPane.showMessageDialog(null, "Na China, as rãs são associadas à boa sorte e à prosperidade. Já pode andar carregando uma rã em");
        } else if (tritoes == maxCount) {
            result = "Você está pensando em um tritão!\n";
            JOptionPane.showMessageDialog(null, "Não aquele ser mitológico, o animal mesmo.");
        } else if (salamandras == maxCount) {
            result = "Você está pensando em uma salamandra!\n";
            JOptionPane.showMessageDialog(null, "Sou frequentemente associada ao fogo e à transformação, tendo citações em títulos como Harry Potter e Hobbit. E vc nada em");
        }

        JOptionPane.showMessageDialog(null, result);
        scanner.close();
    }

    // Método para fazer uma pergunta e obter a resposta do usuário
    private static boolean askQuestion(Scanner scanner, String question) {
        int response = JOptionPane.showConfirmDialog(null, question, "Pergunta", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }
}
