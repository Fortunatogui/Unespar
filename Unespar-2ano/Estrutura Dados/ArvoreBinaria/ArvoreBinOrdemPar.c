#include <stdio.h>
#include <stdlib.h>

typedef struct no {
    int conteudo;
    struct no *esquerda, *direita;
} No;

typedef struct ArvB {
    No *raiz;
} ArvB;

//Insere o nó na esquerda
void inserirEsquerda(No *no, int valor) {
    if (no->esquerda == NULL) {
        No *novo = (No *)malloc(sizeof(No));
        novo->conteudo = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        no->esquerda = novo;
    } else {
        if (valor < no->esquerda->conteudo) {
            inserirEsquerda(no->esquerda, valor);
        } else {
            inserirDireita(no->esquerda, valor);
        }
    }
}

//Insere o nó na direita
void inserirDireita(No *no, int valor) {
    if (no->direita == NULL) {
        No *novo = (No *)malloc(sizeof(No));
        novo->conteudo = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        no->direita = novo;
    } else {
        if (valor < no->direita->conteudo) {
            inserirEsquerda(no->direita, valor);
        } else {
            inserirDireita(no->direita, valor);
        }
    }
}

//Insere o nó na arvore, considerando direita ou esquerda
void inserir(ArvB *arv, int valor) {
    if (arv->raiz == NULL) {
        No *novo = (No *)malloc(sizeof(No));
        novo->conteudo = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        arv->raiz = novo;
    } else {
        if (valor < arv->raiz->conteudo) {
            inserirEsquerda(arv->raiz, valor);
        } else {
            inserirDireita(arv->raiz, valor);
        }
    }
}

//Função para imprimir a arvore em ordem
void imprimirOrdem(No *raiz) {
    if (raiz != NULL) {
        imprimirOrdem(raiz->esquerda);
        printf("%d ", raiz->conteudo);
        imprimirOrdem(raiz->direita);
    }
}

//Função para imprimir a arvore em pré-ordem
void imprimirPre(No *raiz) {
    if (raiz != NULL) {
        printf("%d ", raiz->conteudo);
        imprimirPre(raiz->esquerda);
        imprimirPre(raiz->direita);
    }
}

//Função para imprimir a arvore em pós-ordem
void imprimirPos(No *raiz) {
    if (raiz != NULL) {
        imprimirPos(raiz->esquerda);
        imprimirPos(raiz->direita);
        printf("%d ", raiz->conteudo);
    }
}

// Função para contar e imprimir números pares
int qtdPares(No *raiz) {
    if (raiz == NULL) {
        return 0;
    }

    int count = 0;

    // Verifica se o nó atual é par
    if (raiz->conteudo % 2 == 0) {
        printf("%d ", raiz->conteudo);
        count = 1;
    }

    // Conta os números pares na subárvore esquerda e direita
    return count + qtdPares(raiz->esquerda) + qtdPares(raiz->direita);
}

int main() {
    int op, valor;
    ArvB *arv = (ArvB *)malloc(sizeof(ArvB));
    arv->raiz = NULL;

    do {
        printf("\n1 - Inserir\n2 - Imprimir em ordem\n3 - Imprimir em pre-ordem\n4 - Imprimir em pos-ordem\n0 - Sair\n");
        scanf("%d", &op);
        switch (op) {
            case 1:
                printf("\nDigite o valor: ");
                scanf("%d", &valor);
                inserir(arv, valor);
                break;
            case 2:
                printf("Imprimindo em ordem: ");
                imprimirOrdem(arv->raiz);
                printf("\nPares: ");
                qtdPares(arv->raiz);
                printf("\n");
                break;
            case 3:
                printf("Imprimindo em pre-ordem: ");
                imprimirPre(arv->raiz);
                printf("\nPares: ");
                qtdPares(arv->raiz);
                printf("\n");
                break;
            case 4:
                printf("Imprimindo em pos-ordem: ");
                imprimirPos(arv->raiz);
                printf("\nPares: ");
                qtdPares(arv->raiz);
                printf("\n");
                break;
        }
    } while (op != 0);

    //Libera a memória alocada
    free(arv);
    return 0;
}
