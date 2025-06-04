import java.util.*;

public class Dijkstra {

    // Algoritmo de Dijkstra
    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> grafo, String origem) {
        // Inicializa distâncias
        Map<String, Integer> distancias = new HashMap<>();
        for (String vertice : grafo.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);  // Inicializa com infinito
        }
        distancias.put(origem, 0);

        Set<String> visitados = new HashSet<>();

        // Repete até todos os vértices serem visitados
        while (visitados.size() < grafo.size()) {
            String atual = null;
            int menorDistancia = Integer.MAX_VALUE;

            // Encontra o vértice com menor distância não visitado
            for (String vertice : grafo.keySet()) {
                if (!visitados.contains(vertice) && distancias.get(vertice) < menorDistancia) {
                    menorDistancia = distancias.get(vertice);
                    atual = vertice;
                }
            }

            // Atualiza as distâncias dos vizinhos
            for (Map.Entry<String, Integer> vizinho : grafo.get(atual).entrySet()) {
                String destino = vizinho.getKey();
                int peso = vizinho.getValue();
                int novaDistancia = distancias.get(atual) + peso;

                if (novaDistancia < distancias.get(destino)) {
                    distancias.put(destino, novaDistancia);
                }
            }

            visitados.add(atual);
        }

        return distancias;
    }

    // Função para imprimir o grafo
    public static void imprimirGrafo(Map<String, Map<String, Integer>> grafo) {
        System.out.println("\nEstrutura do Grafo:");
        for (Map.Entry<String, Map<String, Integer>> entrada : grafo.entrySet()) {
            System.out.print(entrada.getKey() + " -> ");
            for (Map.Entry<String, Integer> vizinho : entrada.getValue().entrySet()) {
                System.out.print(vizinho.getKey() + "(" + vizinho.getValue() + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Grafo com arestas e seus pesos
        Map<String, Map<String, Integer>> grafo = new HashMap<>();
        
        // Adiciona as arestas e seus pesos
        grafo.put("A", Map.of("B", 1, "C", 4));
        grafo.put("B", Map.of("A", 1, "C", 2, "D", 5));
        grafo.put("C", Map.of("A", 4, "B", 2, "D", 1));
        grafo.put("D", Map.of("B", 5, "C", 1, "E", 3));
        grafo.put("E", Map.of("D", 3, "A", 7));

        // Imprime o grafo
        imprimirGrafo(grafo);

        // Solicita o vértice de origem
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o vértice de origem: ");
        String origem = scanner.nextLine();

        // Executa o algoritmo de Dijkstra
        Map<String, Integer> distancias = dijkstra(grafo, origem);

        // Exibe as distâncias mínimas para cada vértice
        System.out.println("\nDistâncias mínimas a partir de " + origem + ":");
        for (Map.Entry<String, Integer> entry : distancias.entrySet()) {
            System.out.println(entry.getKey() + " : " + (entry.getValue() == Integer.MAX_VALUE ? "Infinito" : entry.getValue()));
        }

        scanner.close();
    }
}
