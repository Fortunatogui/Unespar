import java.util.Scanner;

public class JogoAnimais {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        // Inicialização dos contadores
        int sapos = 0, ras = 0, tritoes = 0, salamandras = 0;
        int aranhas = 0, caranguejo = 0, formiga = 0, caracol = 0, grilo = 0, gafanhoto = 0, minhoca = 0;

        System.out.println("\nPense em um animal de uma das seguintes categorias: Anfíbios ou Invertebrados.");
        System.out.println("Escolha entre uma das seguintes opções (Anfíbios): Sapos, Salamandras, Rãs, Tritões");
        System.out.println("Escolha entre uma das seguintes opções (Invertebrados): Aranhas, Caranguejos eremitas, Formigas, Grilos, Gafanhoto, Caracois, Minhocas");
        System.out.println("Responda às perguntas com 'sim' ou 'não'.\n");

        if (askQuestion(scanner, "Você está pensando em um anfíbio?")) {

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
            }

            if (askQuestion(scanner, "Sou da ordem Anura (Anfíbios sem cauda)?")) {
                ras++;
                sapos++;
            }

            if (askQuestion(scanner, "Possuo a capacidade de regenerar partes do corpo perdidas?")) {
                tritoes++;
                salamandras++;
            }

        } else {

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

            if (askQuestion(scanner, "Sou herbívoro?")) {
                gafanhoto++;
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
        }

        // Determinação do animal mais provável com base nas contagens
        int maxCount = Math.max(Math.max(Math.max(sapos, ras), Math.max(tritoes, salamandras)), Math.max(Math.max(aranhas, caranguejo), Math.max(formiga, caracol)));
            maxCount = Math.max(maxCount, Math.max(Math.max(grilo, gafanhoto), minhoca));

        String result = "Desculpe, não consegui adivinhar o animal.";

        if (sapos == maxCount) {
            System.out.println("\nBeijar um sapo não é recomendado devido ao risco potencial de exposição a doenças ou substâncias tóxicas. Se quiser alguem baixa o tinder");
            result = "Você está pensando em um sapo!\n";
        } else if (ras == maxCount) {
            System.out.println("\nNa China, as rãs são associadas à boa sorte e à prosperidade. Já pode andar carregando uma rã em");
            result = "Você está pensando em uma rã!\n";
        } else if (tritoes == maxCount) {
            System.out.println("\nNão aquele ser mitológico, o animal mesmo.");
            result = "Você está pensando em um tritão!\n";
        } else if (salamandras == maxCount) {
            System.out.println("\nSou frequentemente associada ao fogo e à transformação, tendo citações em títulos como Harry Potter e Hobbit. E vc nada em");
            result = "Você está pensando em uma salamandra!\n";
        } else if (aranhas == maxCount) {
            System.out.println("\nUma das minhas espécies mais conhecidas tem como hábito devorar o macho depois do acasalamento. Errada não tá né");
            result = "Você está pensando em uma aranha!";
        } else if (caranguejo == maxCount) {
            System.out.println("\nAs conchas desempenham um papel crucial na proteção e no bem-estar dos caranguejos. Pensa bem antes de ir pra praia e pegar um monte pra dps jogar fora");
            result = "Você está pensando em um caranguejo eremita!\n";
        } else if (formiga == maxCount) {
            System.out.println("\nA rainha é a única fêmea reprodutora em uma colônia, ela é responsável por pôr os ovos que darão origem a novas formigas. Viva a monogamia!");
            result = "Você está pensando em uma formiga!\n";
        } else if (caracol == maxCount) {
            System.out.println("\nEm muitas culturas europeias, especialmente na França, caracóis são considerados uma iguaria. Eles são servidos como um prato sofisticado. Hmm que delicinha um caracol");
            result = "Você está pensando em um caracol!\n";
        } else if (grilo == maxCount) {
            System.out.println("\nUm dos grilos mais icônicos da cultura popular é o Grilo Falante, personagem que atua como a consciência de Pinóquio. Bem que podia falar só na animação mesmo");
            result = "Você está pensando em um grilo!\n";
        } else if (gafanhoto == maxCount) {
            System.out.println("\nUma das 10 pragas enviadas por Deus no egito devorando todas as plantações restantes e deixando o país em ruínas. Se eu visse um enxame disso já taria em outro país");
            result = "Você está pensando em um gafanhoto!\n";
        } else if (minhoca == maxCount) {
            System.out.println("\nAs minhocas são cegas e surdas, precisam do tato e a detecção química, para navegar e interagir com o ambiente. Guerreira, periférica e humildade nem se fala");
            result = "Você está pensando em uma minhoca!\n";
        }

        System.out.println(result);
        scanner.close();
    }

    // Método para fazer uma pergunta e obter a resposta do usuário
    private static boolean askQuestion(Scanner scanner, String question) {
        System.out.println(question);
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("sim");
    }
}
