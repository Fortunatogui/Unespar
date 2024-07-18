#include <stdio.h>
#include <locale.h>
#include <stdlib.h>

typedef struct No {
    int num;
    struct No *prox; // Ponteiro que aponta para o pr�ximo n�.
} no;

// Fun��es para exibir o que se pede no menu 
no *inserir_ordenado(no *lista, int dado);
void imprimir_lista(no *lista); 
void liberar_lista(no *lista);
void best_fit(no *blockSize, no *processSize);

no *inserir_ordenado(no *lista, int dado) {
    no *novoNo = (no *)malloc(sizeof(no)); // Reserva mem�ria para struct
    novoNo->num = dado;
    novoNo->prox = NULL;

    if (lista == NULL || lista->num >= dado) {
        novoNo->prox = lista; 
        lista = novoNo;
        return lista;
    }

    no *anterior = NULL;
    no *atual = lista;

    while (atual != NULL && atual->num < dado) {
        anterior = atual;
        atual = atual->prox;
    }

    novoNo->prox = atual;
    anterior->prox = novoNo;
    return lista;
}

void liberar_lista(no *lista) {
    no *aux;
    while (lista != NULL) {
        aux = lista;
        lista = lista->prox;
        free(aux);
    }
}

void imprimir_lista(no *lista) {
    no *atual = lista;
    while (atual != NULL) {
        printf("%d -> ", atual->num);
        atual = atual->prox;
    }
    printf("NULL\n");
}

void best_fit(no *blockSize, no *processSize) {
    no *processAtual = processSize;

    while (processAtual != NULL) {
        no *blockAtual = blockSize;
        no *melhorBloco = NULL;
        no *anterior = NULL, *melhorAnterior = NULL;

        while (blockAtual != NULL) {
            if (blockAtual->num >= processAtual->num) {
                if (melhorBloco == NULL || blockAtual->num < melhorBloco->num) {	
                    melhorBloco = blockAtual;
                    melhorAnterior = anterior;
                }
            }
            anterior = blockAtual;
            blockAtual = blockAtual->prox;
        }

        if (melhorBloco != NULL) {
            printf("Processo %d alocado no bloco %d\n", processAtual->num, melhorBloco->num);

            if (melhorAnterior != NULL) {
                melhorAnterior->prox = melhorBloco->prox;
            } else {
                blockSize = melhorBloco->prox;
            }
            free(melhorBloco);
        } else {
            printf("Processo %d n�o pode ser alocado\n", processAtual->num);
        }

        processAtual = processAtual->prox;
    }
}

int main() {
	
	setlocale(LC_ALL, "Portuguese");
	
    int opcao, valor, i;
    no *blockSize = NULL; // Lista para blockSize
    no *processSize = NULL; // Lista para processSize

    do {
        printf("\nMenu:\n");
        printf("1. Defina 5 valores para o blockSize.\n");
        printf("2. Defina 5 valores para o processSize\n");
        printf("3. Best Fit\n");
        printf("4. Sair\n");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                printf("Digite at� 5 valores de tamanho (separados por espa�o): ");
                for (i = 0; i < 5; i++) {
                    if (scanf("%d", &valor) != 1) {
                        break; 
                    }
                    blockSize = inserir_ordenado(blockSize, valor);
                }
                printf("Lista blockSize: ");
                imprimir_lista(blockSize);
                break;
            case 2:
                printf("Digite at� 5 valores de processo (separados por espa�o): ");
                for (i = 0; i < 5; i++) {
                    if (scanf("%d", &valor) != 1) {
                        break; 
                    }
                    processSize = inserir_ordenado(processSize, valor);
                }
                printf("Lista processSize: ");
                imprimir_lista(processSize);
                break;
            case 3:
                printf("Best Fit:\n");
                best_fit(blockSize, processSize);
                break;
            case 4:
                printf("Sair\n");
                break;
            default:
                printf("Op��o inv�lida!\n");
        }
    } while (opcao != 4);

    liberar_lista(blockSize);
    liberar_lista(processSize);

    return 0;
}