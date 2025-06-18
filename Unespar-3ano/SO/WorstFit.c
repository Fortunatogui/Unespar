#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#define max 25

int main()
{
    setlocale(LC_ALL, "Portuguese");

    int frag[max], b[max], f[max], i, j, nb, nf, temp, maior = 0; // Variáveis para armazenar os tamanhos dos blocos e processos
    static int bf[max], ff[max]; // bf para Best Fit, ff para First Fit usados no algoritmo de alocação
    int flag, fragmentacaoInterna = 0; // Variável para controle de alocação e fragmentação interna

    printf("\n\tGerenciamento de memoria - Worst Fit\n");
    printf("\nDigite o numero de blocos de memoria: "); // Blocos de memória
    scanf("%d", &nb);
    printf("\nDigite o numero de processos: "); // Processos a serem alocados
    scanf("%d", &nf);

    printf("\nDigite o tamanho dos blocos:\n"); // Tamanhos dos blocos de memória
    for(i = 1; i <= nb; i++) {
        printf("Bloco %d: ", i);
        scanf("%d", &b[i]);
        ff[i] = i; // Armazena o índice do bloco para First Fit 
    }

    printf("\nDigite o tamanho dos processos:\n");
    // Tamanhos dos processos a serem alocados 
    for(i = 1; i <= nf; i++) { 
        printf("Processo %d: ", i);
        scanf("%d", &f[i]); 
    }

    int y, z, temp1; // Variáveis auxiliares para ordenação dos blocos
    // Ordena os blocos de memória em ordem decrescente para aplicar o algoritmo Worst Fit
    for(y = 1; y <= nb; y++) {
        // Ordena os blocos de memória
        for(z = y; z <= nb; z++) { 
            // Compara os tamanhos dos blocos e troca se necessário
            if(b[y] < b[z]) {
                temp = b[y]; // Armazena o tamanho do bloco para troca
                b[y] = b[z]; // Troca os tamanhos dos blocos
                b[z] = temp; // Atualiza o tamanho do bloco
                temp1 = ff[y]; // Armazena o índice do bloco para First Fit
                ff[y] = ff[z]; // Troca os índices dos blocos
                ff[z] = temp1; // Atualiza o índice do bloco
            }
        }
    }

    int alocado[max]; // Array para controle de alocação dos blocos
    int fragmentacaoExterna = 0; 

    printf("\n\nProcesso\tTamanho do Processo\tBloco\tTamanho do Bloco\tFragmento\n");
    for(i = 1; i <= nf; i++) {
        flag = 1; // Variável para verificar se o processo foi alocado 
        // Verifica se o processo pode ser alocado em algum bloco
        for(j = 1; j <= nb; j++) {
            // Se o processo cabe no bloco e o bloco não foi alocado
            if(f[i] <= b[j]) {
                alocado[j] = 1; // Marca o bloco como alocado
                printf("%-15d\t%-20d\t%-10d\t%-17d\t", i, f[i], ff[j], b[j]);
                b[j] = b[j] - f[i]; // Atualiza o tamanho do bloco após alocação
                fragmentacaoInterna += b[j]; // Calcula a fragmentação interna
                printf("%-10d\n", b[j]); // Exibe o tamanho do fragmento restante
                break;
            } else {
                alocado[j] = 0;
                flag++;
            }
        }
        if(flag > nb) // Se o processo não couber em nenhum bloco ele marca como aguardando
            printf("%-15d\t%-20d\t%-10s\t%-17s\t%-10s\n", i, f[i], "AGUARDANDO...", "AGUARDANDO...", "AGUARDANDO...");
    }

    printf("\nFragmentacao Interna = %d", fragmentacaoInterna);
    // Calcula a fragmentação externa
    for(j = 1; j <= nb; j++) {
        if(alocado[j] != 1) // Se o bloco não foi alocado, conta como fragmentação externa
            fragmentacaoExterna += b[j]; 
    }
    printf("\nFragmentacao Externa = %d\n", fragmentacaoExterna);

    return 0;
}