#include <stdio.h>
#include <stdlib.h>

#define MAX 25 

int main() { 
    int blocoTamanho[MAX], processoTamanho[MAX]; //arrays para armazenar os tamanhos dos blocos e processos
    int numBlocos, numProcessos; // variaveis para armazenar o numero de blocos e processos
    int i, j; // variaveis de controle para loops
    int blocoIndex[MAX]; // guarda o indice original dos blocos
    int blocoUsado[MAX] = {0}; // marca quais blocos foram usados
    int fragmentoInterno = 0; //soma de espaços dentro dos blocos alocados, mas não usados.
    int fragmentoExterno = 0; //soma de espaços em blocos não alocados.

    printf("\n\tGerenciamento de Memoria - Worst Fit\n");

    // Entrada de dados
    printf("\nDigite o numero de blocos de memoria: ");
    scanf("%d", &numBlocos);

    printf("Digite o numero de processos: ");
    scanf("%d", &numProcessos);

    // Solicitação de tamanho dos blocos:
    printf("\nDigite o tamanho dos blocos:\n");
    for(i = 0; i < numBlocos; i++) {
        printf("Bloco %d: ", i + 1);
        scanf("%d", &blocoTamanho[i]);
        blocoIndex[i] = i + 1; // salva o numero original do bloco
    }

    // Solicitação de tamanho dos processos:
    printf("\nDigite o tamanho dos processos:\n");
    for(i = 0; i < numProcessos; i++) {
        printf("Processo %d: ", i + 1);
        scanf("%d", &processoTamanho[i]);
    }

    // Ordena blocos em ordem decrescente (para Worst Fit)
    for(i = 0; i < numBlocos - 1; i++) { // bloco atual
        for(j = i + 1; j < numBlocos; j++) { // bloco a comparar
            if(blocoTamanho[i] < blocoTamanho[j]) { // troca os blocos se o tamanho do bloco i for menor que o tamanho do bloco j
                int temp = blocoTamanho[i]; // troca os tamanhos dos blocos
                blocoTamanho[i] = blocoTamanho[j]; // bloco i recebe o tamanho do bloco j
                blocoTamanho[j] = temp;

                // troca os indices dos blocos
                int tempIdx = blocoIndex[i]; 
                blocoIndex[i] = blocoIndex[j];
                blocoIndex[j] = tempIdx;
            }
        }
    }

    // Alocacao dos processos
    printf("\n\nProcesso\tTamanho\t\tBloco\tTamanho Bloco\tFragmento\n");
    for(i = 0; i < numProcessos; i++) {
        int alocado = 0;

        for(j = 0; j < numBlocos; j++) {
            if(processoTamanho[i] <= blocoTamanho[j]) { // verifica se o tamanho do processo cabe no bloco
                printf("%-8d\t%-8d\t%-8d\t%-14d\t%-8d\n", 
                    i + 1, processoTamanho[i], blocoIndex[j], blocoTamanho[j], blocoTamanho[j] - processoTamanho[i]);

                fragmentoInterno += blocoTamanho[j] - processoTamanho[i]; // Calculo quanto de memória vai sobrar dentro do bloco depois do processo alocado
                blocoTamanho[j] -= processoTamanho[i]; // Atualiza o tamanho do bloco após alocar o processo
                blocoUsado[j] = 1; // Marca o bloco como usado
                alocado = 1;
                break;
            }
        }

        if(!alocado) {
            printf("%-8d\t%-8d\t%-8s\t%-14s\t%-8s\n", i + 1, processoTamanho[i], "AGUARDANDO", "AGUARDANDO", "AGUARDANDO");
        }
    }

    // Fragmentacao externa = blocos nao usados
    for(i = 0; i < numBlocos; i++) {
        if(blocoUsado[i] == 0) {
            fragmentoExterno += blocoTamanho[i];
        }
    }

    printf("\nFragmentacao Interna = %d", fragmentoInterno);
    printf("\nFragmentacao Externa = %d\n", fragmentoExterno);

    return 0;
}
