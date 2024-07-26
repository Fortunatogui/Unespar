import javax.swing.*;
import java.util.Scanner;

public class JogoInvertebrados {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        // Inicialização dos contadores
        int aranhas = 0, caranguejo = 0, formiga = 0, caracol = 0, grilo = 0, gafanhoto = 0, minhoca = 0;

        JOptionPane.showMessageDialog(null, "<html><body><p>Pense em um animal da categoria Invertebrados.</p>"
                    + "<p>Escolha entre uma das seguintes opções: Aranhas, Caranguejos eremitas, Formigas, Grilos, Gafanhoto, Caracois, Minhocas.</p>"
                    + "<p>Responda às perguntas com 'sim' ou 'não'.</p></body></html>");

        if (askQuestion(scanner, "Tenho 6 patas ou mais?")) {
            aranhas++;
            caranguejo++;
            formiga++;
            grilo++;
            gafanhoto++;
        }

        if (askQuestion(scanner, "Me movo usando contrações musculares?")) {
            caracol++;
            minhoca++;
        }

        if (askQuestion(scanner, "Sou um ótimo saltador?")) {
            grilo++;
            gafanhoto++;
        }

        if (askQuestion(scanner, "Sou onívoro?")) {
            caranguejo++;
            formiga++;
            grilo++;
        }

        if (askQuestion(scanner, "Sou carnívora?")) {
            aranhas++;
        }

        if (askQuestion(scanner, "Sou herbívoro?")) {
            gafanhoto++;
            caracol++;
        }

        if (askQuestion(scanner, "Posso ser formídavel quando se trata de caçar?")) {
            aranhas++;
            formiga++;
        }
        
        if (askQuestion(scanner, "Preciso do auxílio de algo material pra sobreviver?")) {
            caracol++;
        }

        if (askQuestion(scanner, "Posso ser usado como isca?")) {
            caranguejo++;
            caracol++;
            minhoca++;
        }

        if (askQuestion(scanner, "Posso criar túneis para uma melhor locomoção do grupo?")) {
            minhoca++;
            formiga++;
        }

        // Determinação do animal mais provável com base nas contagens
        int maxCount = Math.max(Math.max(aranhas, caranguejo), Math.max(formiga, caracol));
        maxCount = Math.max(maxCount, Math.max(Math.max(grilo, gafanhoto), minhoca));

        String result = "Desculpe, não vendemos esse animal.";

        if (aranhas == maxCount) {
            result = "Você está pensando em uma aranha!";
            JOptionPane.showMessageDialog(null, "Uma das minhas espécies mais conhecidas tem como hábito devorar o macho depois do acasalamento. Errada não tá né");
        } else if (caranguejo == maxCount) {
            result = "Você está pensando em um caranguejo eremita!\n";
            JOptionPane.showMessageDialog(null, "As conchas desempenham um papel crucial na proteção e no bem-estar dos caranguejos. Pensa bem antes de ir pra praia e pegar um monte pra dps jogar fora");
        } else if (formiga == maxCount) {
            result = "Você está pensando em uma formiga!\n";
            JOptionPane.showMessageDialog(null, "A rainha é a única fêmea reprodutora em uma colônia, ela é responsável por pôr os ovos que darão origem a novas formigas. Viva a monogamia!");
        } else if (caracol == maxCount) {
            result = "Você está pensando em um caracol!\n";
            JOptionPane.showMessageDialog(null, "Em muitas culturas europeias, especialmente na França, caracóis são considerados uma iguaria. Eles são servidos como um prato sofisticado. Hmm que delicinha um caracol");
        } else if (grilo == maxCount) {
            result = "Você está pensando em um grilo!\n";
            JOptionPane.showMessageDialog(null, "Um dos grilos mais icônicos da cultura popular é o Grilo Falante, personagem que atua como a consciência de Pinóquio. Bem que podia falar só na animação mesmo");
        } else if (gafanhoto == maxCount) {
            result = "Você está pensando em um gafanhoto!\n";
            JOptionPane.showMessageDialog(null, "Uma das 10 pragas enviadas por Deus no egito devorando todas as plantações restantes e deixando o país em ruínas. Se eu visse um enxame disso já taria em outro país");
        } else if (minhoca == maxCount) {
            result = "Você está pensando em uma minhoca!\n";
            JOptionPane.showMessageDialog(null, "As minhocas são cegas e surdas, precisam do tato e a detecção química, para navegar e interagir com o ambiente. Guerreira, periférica e humildade nem se fala");
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
