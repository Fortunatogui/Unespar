#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void insertionSort(int vetor[], int tamanho, int *numComparacoes, int *numMovimentacoes) {
    for (int i = 1; i < tamanho; i++) {  // Começa a partir do segundo elemento, pois o primeiro já está "ordenado"
        int chave = vetor[i];              // O valor atual do elemento sendo considerado (a chave que será inserida na posição correta)
        int j = i - 1;                     // Variável j serve para comparar com os elementos à esquerda da chave
        while (j >= 0 && vetor[j] > chave) {  // Enquanto j for válido (>= 0) e o valor à esquerda for maior que a chave
            vetor[j + 1] = vetor[j];       // Desloca o elemento à direita, para abrir espaço para a chave
            j--;                           // Move para o próximo elemento à esquerda (para comparar com o próximo elemento)
            (*numMovimentacoes)++;         // Conta a movimentação (um deslocamento)
            (*numComparacoes)++;           // Conta a comparação feita
        }
        vetor[j + 1] = chave;              // Coloca a chave na posição correta
        (*numMovimentacoes)++;             // Conta a movimentação ao inserir a chave na posição correta
    }
}

void inicializarVetor(int vetor[], int tamanho, int tipo) {
    for (int i = 0; i < tamanho; i++) {
        if (tipo == 1) vetor[i] = rand() % 10000;         // Vetor com valores aleatórios entre 0 e 9999
        else if (tipo == 2) vetor[i] = i;                 // Vetor ordenado em ordem crescente (valores 0, 1, 2,...)
        else vetor[i] = tamanho - i - 1;                  // Vetor ordenado em ordem decrescente (valores n-1, n-2,...)
    }
}

void testarInsertionSort(int tamanho, int tipo, const char *tipoDescricao) {
    int *vetor = (int *)malloc(tamanho * sizeof(int));  // Aloca memória para o vetor de tamanho variável
    inicializarVetor(vetor, tamanho, tipo);             // Inicializa o vetor com os dados de acordo com o tipo especificado

    int numComparacoes = 0, numMovimentacoes = 0;       // Inicializa os contadores de comparações e movimentações
    clock_t inicio = clock();                            // Marca o tempo de início da execução
    insertionSort(vetor, tamanho, &numComparacoes, &numMovimentacoes);  // Chama a função de ordenação
    clock_t fim = clock();                               // Marca o tempo de fim da execução
    double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC; // Calcula o tempo de execução (em segundos)

    printf("Tipo de vetor: %s\n", tipoDescricao);          
    printf("Tamanho do vetor: %d\n", tamanho);            
    printf("Tempo de execucao: %.6f segundos\n", tempoExecucao);  
    printf("Numero de comparacoes: %d\n", numComparacoes); 
    printf("Numero de movimentacoes: %d\n\n", numMovimentacoes);  

    free(vetor);                                         // Libera a memória alocada para o vetor
}

int main() {
    srand(time(NULL));                                    // Inicializa a semente para o gerador de números aleatórios (para gerar valores diferentes a cada execução)

    int tamanhos[] = {100, 1000, 10000, 100000};          // Define diferentes tamanhos de vetores para testar (de 100 a 100000)
    int numTamanhos = sizeof(tamanhos) / sizeof(tamanhos[0]);  // Calcula o número de diferentes tamanhos de vetores

    printf("Insertion Sort\n\n");                          
    for (int i = 0; i < numTamanhos; i++) {               // Loop para percorrer os diferentes tamanhos de vetores
        int tamanho = tamanhos[i];                         // Obtém o tamanho atual do vetor
        printf("==== Resultados para Tamanho do Vetor: %d ====\n", tamanho);  
        testarInsertionSort(tamanho, 1, "Aleatorio");      // Testa o Insertion Sort com vetor aleatório
        testarInsertionSort(tamanho, 2, "Ordenado");       // Testa o Insertion Sort com vetor ordenado
        testarInsertionSort(tamanho, 3, "Inverso");        // Testa o Insertion Sort com vetor em ordem inversa

        printf("===========================================\n\n");  

    return 0;                                             
}