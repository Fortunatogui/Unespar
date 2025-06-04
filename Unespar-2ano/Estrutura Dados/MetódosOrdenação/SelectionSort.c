#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void selectionSort(int vet[], int tam, int *comparacoes, int *movimentacoes) {
    // Loop principal que percorre todo o vetor
    for (int i = 0; i < tam - 1; i++) {
        int min_idx = i;  // Inicializa o índice do menor elemento como o índice atual
        // Loop interno para comparar o elemento i com os próximos elementos
        for (int j = i + 1; j < tam; j++) {
            (*comparacoes)++;  // Conta a comparação entre vet[j] e vet[min_idx]
            // Se encontrar um valor menor que o atual mínimo
            if (vet[j] < vet[min_idx]) {
                min_idx = j;  // Atualiza o índice do menor valor
            }
        }
        // Se o menor valor não estiver na posição i, faz a troca
        if (min_idx != i) {
            int temp = vet[min_idx];  // Armazena o valor mínimo em uma variável temporária
            vet[min_idx] = vet[i];    // Troca o valor do índice i com o valor mínimo
            vet[i] = temp;            // Coloca o valor mínimo na posição i
            (*movimentacoes)++;       // Conta a movimentação (troca de valores)
        }
    }
}

// Função para inicializar o vetor com valores de acordo com o tipo solicitado
void inicializarVetor(int vet[], int tam, int tipo) {
    for (int i = 0; i < tam; i++) {
        if (tipo == 1) vet[i] = rand() % 1000;         
        else if (tipo == 2) vet[i] = i + 1;            
        else vet[i] = tam - i;                        
    }
}

void testarSelectionSort(int tam, int tipo, const char *tipoDescricao) {

    int *vet = (int *)malloc(tam * sizeof(int));
    
    inicializarVetor(vet, tam, tipo);

    int comparacoes = 0;
    int movimentacoes = 0;

    clock_t inicio = clock();
    selectionSort(vet, tam, &comparacoes, &movimentacoes);
    clock_t fim = clock();

    double tempo_execucao = (double)(fim - inicio) / CLOCKS_PER_SEC;

    printf("Tipo de vetor: %s\n", tipoDescricao);  
    printf("Tamanho do vetor: %d\n", tam);      
    printf("Tempo de execucao: %.6f segundos\n", tempo_execucao);  
    printf("Numero de comparacoes: %d\n", comparacoes);  
    printf("Numero de movimentacoes: %d\n\n", movimentacoes);  

    free(vet);
}

int main() {
    srand(time(NULL));  // Inicializa o gerador de números aleatórios com a semente do tempo atual

    int tamanhos[] = {100, 1000, 10000, 100000};
    int numTamanhos = sizeof(tamanhos) / sizeof(tamanhos[0]);  // Calcula o número de tamanhos definidos

    printf("Selection Sort\n\n");

    for (int i = 0; i < numTamanhos; i++) {
        int tam = tamanhos[i];  // Tamanho do vetor a ser testado
        printf("Tamanho do vetor: %d\n", tam);

        testarSelectionSort(tam, 1, "Aleatorio");
        testarSelectionSort(tam, 2, "Ordenado");
        testarSelectionSort(tam, 3, "Inverso");
        
        printf("---------------------------------\n"); 
    }

    return 0;  
}