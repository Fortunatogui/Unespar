import java.util.*;

class No {
    String nome;
    List<No> filhos;

    No(String nome) {
        this.nome = nome;
        this.filhos = new ArrayList<>();
    }
}

public class Buscas {

    // Função para Imprimir a Árvore (não é requisito do exercício)
    public static void imprimirArvore(No raiz, int nivel) {
        if (raiz == null) return;
        
        // Indentação e nome do nó
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(raiz.nome);

        // Chama recursivamente para os filhos
        for (No filho : raiz.filhos) {
            imprimirArvore(filho, nivel + 1);
        }
    }

    // Função para Busca em Profundidade (DFS)
    public static boolean profundidade(No raiz, String alvo) {
        if (raiz == null) return false;

        System.out.print(raiz.nome + " ");  // Exibe o nó atual
        if (raiz.nome.equals(alvo)) return true;

        // Recursivamente busca nos filhos
        for (No filho : raiz.filhos) {
            if (profundidade(filho, alvo)) return true;
        }
        return false;
    }

    // Função para Busca em Largura (BFS)
    public static boolean largura(No raiz, String alvo) {
        if (raiz == null) return false;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        // Processa a fila até encontrar o alvo
        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.nome + " ");  // Exibe o nó atual
            if (atual.nome.equals(alvo)) return true;

            // Adiciona os filhos à fila
            fila.addAll(atual.filhos);
        }
        return false;
    }

    public static void main(String[] args) {
        // Exemplo de árvore de nós
        No raiz = new No("1");
        raiz.filhos.add(new No("2"));
        raiz.filhos.add(new No("3"));
        raiz.filhos.add(new No("4"));
        raiz.filhos.get(0).filhos.add(new No("5"));
        raiz.filhos.get(0).filhos.add(new No("6"));
        raiz.filhos.get(2).filhos.add(new No("7"));
        raiz.filhos.get(2).filhos.add(new No("8"));

        // Imprime a árvore (opcional, não é exigido no exercício)
        System.out.println("Estrutura da árvore:");
        imprimirArvore(raiz, 0);

        // Exemplo de busca
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o nó que deseja buscar: ");
        String alvo = scanner.nextLine();

        // Busca por profundidade
        System.out.println("\nBusca em Profundidade:");
        if (!profundidade(raiz, alvo)) {
            System.out.println("\nNó não encontrado.");
        }

        // Busca por largura
        System.out.println("\nBusca em Largura:");
        if (!largura(raiz, alvo)) {
            System.out.println("\nNó não encontrado.");
        }

        scanner.close();
    }
}
