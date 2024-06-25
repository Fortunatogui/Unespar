import java.util.*;

public class SimuladorEscalonamentoDisco {

    static class Requisicao {
        int cilindro;
        public Requisicao(int cilindro) {
            this.cilindro = cilindro;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================================================");
        System.out.println("ALGORITMO DE ESCALONAMENTO DE BRAÇO DE DISCO");
        System.out.println("Atividade 2 - AOC - CCOMP - UNESPAR");
        System.out.println("==================================================================================");
        System.out.println("\nInforme o tamanho do cilindro:");
        int tamanhoCilindro = scanner.nextInt();

        List<Requisicao> requisicoes = lerRequisicoes(scanner, tamanhoCilindro);
        scanner.close();
    
        final int posicaoInicial = 0;

        System.out.println("\n==================================================================================");
        System.out.println("Início FCFS");
        System.out.println("==================================================================================");
        FCFS(new ArrayList<>(requisicoes), posicaoInicial, tamanhoCilindro);
        System.out.println("==================================================================================");
        System.out.println("Final FCFS");
        System.out.println("==================================================================================");
        
        List<Requisicao> requisicoesSSF = new ArrayList<>(requisicoes);
        System.out.println("\n==================================================================================");
        System.out.println("Início SSF");
        System.out.println("==================================================================================");
        SSF(new ArrayList<>(requisicoesSSF), posicaoInicial, tamanhoCilindro);
        System.out.println("==================================================================================");
        System.out.println("Final SSF");
        System.out.println("==================================================================================");
    }

    static List<Requisicao> lerRequisicoes(Scanner scanner, int tamanhoCilindro) {
        List<Requisicao> requisicoes = new ArrayList<>();
        System.out.println("\nInforme a quantidade de pedidos de cilindros:");
        int qtdRequisicoes = scanner.nextInt();
        System.out.println();
        
        for (int i = 0; i < qtdRequisicoes; i++) { 
            System.out.print("Informe o " + (i + 1) + "º pedido de cilindro: ");
            int cilindro = scanner.nextInt();
            requisicoes.add(new Requisicao(cilindro));
        }
        System.out.println("\n==========================================================");
        System.out.println("Representação inicial do Cilindro:");
        System.out.println("----------------------------------------------------------");
        System.out.print("|| ");

        for (int i = 0; i < tamanhoCilindro; i++) {
            System.out.printf("%2d || ", i + 1);
        }
    
        char[] acessoCilindro = new char[tamanhoCilindro];
        Arrays.fill(acessoCilindro, '-');
    
        System.out.print("\n|| ");
        for (int i = 0; i < tamanhoCilindro; i++) { 
            System.out.printf("%2c || ", acessoCilindro[i]);
        }
    
        System.out.println("\n==========================================================");
        System.out.println("\nVetor de Pedidos");
        System.out.println("==========================================================");
        System.out.print("||");
        for (Requisicao requisicao : requisicoes) {
            System.out.printf(" %2d ||", requisicao.cilindro); 
        }  
        return requisicoes;
    }

    static void FCFS(List<Requisicao> requisicoes, int posicaoAtual, int tamanhoCilindro) {
        Set<Integer> cilindrosVisitados = new HashSet<>();

        for (Requisicao requisicao : requisicoes) {
            cilindrosVisitados.add(requisicao.cilindro);
            mostraRepresentacao(cilindrosVisitados, tamanhoCilindro);
        }
    }

    static void SSF(List<Requisicao> requisicoes, int posicaoAtual, int tamanhoCilindro) {
        Set<Integer> cilindrosVisitados = new HashSet<>();
        int posicaoAtualFinal = posicaoAtual;
    
        while (!requisicoes.isEmpty()) {
            Requisicao proxima = null;
            int menorDistancia = Integer.MAX_VALUE;
    
            for (Requisicao requisicao : requisicoes) {
                int distancia = Math.abs(requisicao.cilindro - posicaoAtualFinal);
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    proxima = requisicao;
                }
            }
    
            if (proxima != null) {
                cilindrosVisitados.add(proxima.cilindro);
                requisicoes.remove(proxima);
                mostraRepresentacao(cilindrosVisitados, tamanhoCilindro);
                posicaoAtualFinal = proxima.cilindro;
            }
        }
    }

    static void mostraRepresentacao(Set<Integer> cilindrosVisitados, int tamanhoCilindro) {
        
        System.out.println("\nRepresentação do Cilindro:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for(int i = 0; i < tamanhoCilindro; i++) {
            if(i < 10) {
                System.out.printf("|0%d|\t", i);
            } else {
                System.out.printf("|%d|\t", i);
            }
        }
        System.out.println();
        
        for(int i = 0; i < tamanhoCilindro; i++) {
            System.out.printf("|--|\t");
        }
        System.out.println();

        for(int i = 0; i < tamanhoCilindro; i++) {
            if(cilindrosVisitados.contains(i)) {
                System.out.printf("|XX|\t");
            } else {
                System.out.printf("|  |\t");
            }
        }
        System.out.println("\n");
    }
}
