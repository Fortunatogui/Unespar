#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void bubbleSort(int vet[], int tam, int *comparacoes, int *movimentacoes) {
    for (int i = 0; i < tam - 1; i++) {  // Laço externo que percorre todo o vetor (n-1 vezes)
        for (int j = 0; j < tam - i - 1; j++) {  // Laço interno para comparar os elementos adjacentes
            (*comparacoes)++;  // Conta a comparação feita entre vet[j] e vet[j + 1]
            if (vet[j] > vet[j + 1]) {  // Se o elemento da esquerda for maior que o da direita, troque-os
                int temp = vet[j];      // Variável temporária para armazenar vet[j]
                vet[j] = vet[j + 1];    // Atribui o valor de vet[j + 1] à posição de vet[j]
                vet[j + 1] = temp;      // Coloca o valor de vet[j] na posição de vet[j + 1]
                (*movimentacoes)++;     // Conta a movimentação realizada na troca de elementos
            }
        }
    }
}

void inicializarVetor(int vet[], int tam, int tipo) {
    for (int i = 0; i < tam; i++) {
        if (tipo == 1) vet[i] = rand() % 10000;  // Vetor aleatório entre 0 e 9999
        else if (tipo == 2) vet[i] = i;          // Vetor ordenado em ordem crescente (0, 1, 2,...)
        else vet[i] = tam - i - 1;               // Vetor ordenado em ordem decrescente (tamanho-1, tamanho-2,...)
    }
}

void testarBubbleSort(int tam, int tipo, const char *tipoDescricao) {
    int *vet = (int *)malloc(tam * sizeof(int));  // Aloca memória dinamicamente para o vetor
    inicializarVetor(vet, tam, tipo);             // Inicializa o vetor com o tipo de dados especificado

    int comparacoes = 0;  // Inicializa o contador de comparações
    int movimentacoes = 0; // Inicializa o contador de movimentações

    clock_t inicio = clock();  // Marca o tempo de início da execução
    bubbleSort(vet, tam, &comparacoes, &movimentacoes);  // Chama a função de ordenação Bubble Sort
    clock_t fim = clock();  // Marca o tempo de fim da execução

    double tempo_execucao = (double)(fim - inicio) / CLOCKS_PER_SEC;  // Calcula o tempo total de execução em segundos

    printf("Tipo de vetor: %s\n", tipoDescricao);
    printf("Tamanho do vetor: %d\n", tam);
    printf("Tempo de execucao: %.6f segundos\n", tempo_execucao);
    printf("Numero de comparacoes: %d\n", comparacoes);
    printf("Numero de movimentacoes: %d\n\n", movimentacoes);

    free(vet);  
}

int main() {
    srand(time(NULL));  // Inicializa a semente para o gerador de números aleatórios

    int tamanhos[] = {100, 1000, 10000, 100000};  // Define diferentes tamanhos de vetores para testar
    int numTamanhos = sizeof(tamanhos) / sizeof(tamanhos[0]);  // Calcula quantos tamanhos de vetor temos para testar

    printf("Bubble Sort\n\n");  // Exibe o título do algoritmo

    // Loop para percorrer os diferentes tamanhos de vetores
    for (int i = 0; i < numTamanhos; i++) {
        int tam = tamanhos[i];  // Obtém o tamanho atual do vetor
        printf("==== Resultados para Tamanho do Vetor: %d ====\n", tam);

        testarBubbleSort(tam, 1, "Aleatorio");  // Testa com vetor aleatório
        testarBubbleSort(tam, 2, "Ordenado");   // Testa com vetor ordenado
        testarBubbleSort(tam, 3, "Inverso");    // Testa com vetor em ordem inversa

        printf("===========================================\n\n");
    }

    return 0;  
}