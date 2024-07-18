#include <stdio.h>
#include <stdlib.h>

// Implementação de fila 
// Implementação de fila com prioridade

typedef struct no {
    int valor;
    struct no *proximo;
    int prioridade;
} No;

typedef struct {
    No *primeiro;
    No *ultimo;
    int tamanho;
} Fila;

void inicializarFila(Fila *fila){ // Inicializa uma fila
    fila->primeiro = NULL;
    fila->ultimo = NULL;
    fila->tamanho = 0;
}

void adicionarFilaPrioridade(Fila *fila, int valor, int prioridade){
    No *novoNo = malloc(sizeof(No));
    if(novoNo != NULL){
        novoNo->valor = valor;
        novoNo->proximo = NULL;
        novoNo->prioridade = prioridade;
        if (fila->primeiro == NULL){
            fila->primeiro = novoNo;
            fila->ultimo = novoNo;
        } else {
            fila->ultimo->proximo = novoNo;
            fila->ultimo = novoNo;
        }
        fila->tamanho++;
    } else {
        printf("\nErro ao alocar memória.\n");
    }
}

No* removerDaFila(Fila *fila){
    No *noRemovido = NULL;
    if(fila->primeiro != NULL){
        noRemovido = fila->primeiro;
        fila->primeiro = noRemovido->proximo;
        fila->tamanho--;
        if (fila->primeiro == NULL) {
            fila->ultimo = NULL;
        }
    } else {
        printf("\tFila Vazia!\n");
    }
    return noRemovido;
}

void mostrarFila(Fila *fila){
    No *auxiliar = fila->primeiro;
    printf("\t----FILA----\n");
    while(auxiliar != NULL){
        printf("Valor: %d Prioridade: %d\n", auxiliar->valor, auxiliar->prioridade);
        auxiliar = auxiliar->proximo;
    }
    printf("\n\t----FIM DA FILA----\n");
}

int main(){
    No *removido1, *removido2;
    Fila fila1;
    Fila fila2;
    inicializarFila(&fila1); // Inicializa uma fila
    inicializarFila(&fila2);
    int valor, opcaoUsuario, prioridade, removerOpcao, opcaoImprimir;

    do {
        printf(" 0 - Sair \n 1 - Inserir \n 2 - Remover \n 3 - Imprimir \n");
        scanf("%d", &opcaoUsuario);
        switch(opcaoUsuario)
        {
        case 1:
            printf("Digite um valor a ser inserido na fila: ");
            scanf("%d", &valor);
            printf("Digite a prioridade do valor inserido (1 ou 2): ");
            scanf("%d", &prioridade);
            if(prioridade == 1){
                adicionarFilaPrioridade(&fila1, valor, prioridade);
            } else if(prioridade == 2){
                adicionarFilaPrioridade(&fila2, valor, prioridade);
            } else {
                printf("Prioridade inválida.\n");
            }
            break;
        case 2:
            printf("Escolha qual fila deseja remover (1 ou 2): ");
            scanf("%d", &removerOpcao);
            if (removerOpcao == 1){
                removido1 = removerDaFila(&fila1);
                if (removido1 != NULL) {
                    printf("Removido: %d\n", removido1->valor);
                    free(removido1);
                }
            } else if (removerOpcao == 2){
                removido2 = removerDaFila(&fila2);
                if (removido2 != NULL) {
                    printf("Removido: %d\n", removido2->valor);
                    free(removido2);
                } 
            } else {
                printf("Prioridade inválida.\n");
            }
            break;
        case 3:
            printf("Escolha qual fila deseja imprimir (1 ou 2): ");
            scanf("%d", &opcaoImprimir);
            if (opcaoImprimir == 1){
                mostrarFila(&fila1);
            } else if (opcaoImprimir == 2){
                mostrarFila(&fila2);
            } else {
                printf("Prioridade inválida.\n");
            }
            break;
        default:
            if(opcaoUsuario != 0){
                printf("Opção inválida.\n");
            }
            break;
        }
    } while (opcaoUsuario != 0);

    return 0;
}
