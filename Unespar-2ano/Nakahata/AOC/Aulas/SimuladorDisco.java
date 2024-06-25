import java.util.*;

public class SimuladorDisco {

    static class Requisicao {
        int cilindro;

        public Requisicao(int cilindro) {
            this.cilindro = cilindro;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o tamanho do cilindro:");
        int tamanhoCilindro = scanner.nextInt();

        List<Requisicao> requisicoes = lerRequisicoes(scanner);
        scanner.close();

        System.out.println("\nInício FCFS");
        FCFS(new ArrayList<>(requisicoes), tamanhoCilindro);
        System.out.println("Final FCFS");

        System.out.println("\nInício SSF");
        SSF(new ArrayList<>(requisicoes), tamanhoCilindro);
        System.out.println("Final SSF");
    }

    static List<Requisicao> lerRequisicoes(Scanner scanner) {
        List<Requisicao> requisicoes = new ArrayList<>();
        System.out.println("Informe a quantidade de pedidos de cilindros:");
        int qtdRequisicoes = scanner.nextInt();

        for (int i = 0; i < qtdRequisicoes; i++) {
            System.out.print("Informe o " + (i + 1) + "º pedido de cilindro: ");
            int cilindro = scanner.nextInt();
            requisicoes.add(new Requisicao(cilindro));
        }

        System.out.println("\nPedidos de Acesso:");
        for (Requisicao requisicao : requisicoes) {
            System.out.print(requisicao.cilindro + " ");
        }
        System.out.println();

        return requisicoes;
    }

    static void FCFS(List<Requisicao> requisicoes, int tamanhoCilindro) {
        Set<Integer> cilindrosVisitados = new HashSet<>();
        mostrarCilindros(cilindrosVisitados, tamanhoCilindro);

        for (Requisicao requisicao : requisicoes) {
            cilindrosVisitados.add(requisicao.cilindro);
            mostrarCilindros(cilindrosVisitados, tamanhoCilindro);
        }
    }

    static void SSF(List<Requisicao> requisicoes, int tamanhoCilindro) {
        Set<Integer> cilindrosVisitados = new HashSet<>();
        int posicaoAtual = 0;
        mostrarCilindros(cilindrosVisitados, tamanhoCilindro);

        while (!requisicoes.isEmpty()) {
            Requisicao proximaRequisicao = null;
            int menorDistancia = Integer.MAX_VALUE;

            for (Requisicao requisicao : requisicoes) {
                int distancia = Math.abs(requisicao.cilindro - posicaoAtual);
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    proximaRequisicao = requisicao;
                }
            }

            if (proximaRequisicao != null) {
                cilindrosVisitados.add(proximaRequisicao.cilindro);
                requisicoes.remove(proximaRequisicao);
                mostrarCilindros(cilindrosVisitados, tamanhoCilindro);
                posicaoAtual = proximaRequisicao.cilindro;
            }
        }
    }

    static void mostrarCilindros(Set<Integer> cilindrosVisitados, int tamanhoCilindro) {
        System.out.print("Cilindros: ");
        for (int i = 0; i < tamanhoCilindro; i++) {
            if (cilindrosVisitados.contains(i)) {
                System.out.print("[XX] ");
            } else {
                System.out.print("[  ] ");
            }
        }
        System.out.println();
    }
}
