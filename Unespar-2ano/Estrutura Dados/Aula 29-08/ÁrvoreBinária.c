#include <stdio.h>
#include <stdlib.h>

typedef struct no{
    int conteudo;
    struct no *esquerda, *direita;
} No;

typedef struct ArvB{
    No *raiz;
} ArvB;

//Insere o nó na esquerda
void inserirEsquerda(No *no, int valor){
    if(no->esquerda == NULL){ 
        No novo = (No)malloc(sizeof(No));
        novo->conteudo = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        no->esquerda = novo;
    } else {
        if(valor < no->esquerda->conteudo){ 
            inserirEsquerda(no->esquerda, valor); 
        } else {
            inserirDireita(no->esquerda, valor);
        }
    }
}

void inserirDireita(No *no, int valor){
    if(no->direita == NULL){ 
        No novo = (No)malloc(sizeof(No));
        novo->conteudo = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        no->direita = novo;
    } else {
        if(valor < no->direita->conteudo){ 
            inserirEsquerda(no->direita, valor); 
        } else {
            inserirDireita(no->direita, valor);
        }
    }
}

void inserir(ArvB *arv, int valor){
    if(arv->raiz == NULL){ 
        No novo = (No)malloc(sizeof(No));
        novo->conteudo = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        arv->raiz = novo; 
    } else { 
        if(valor < arv->raiz->conteudo){
            inserirEsquerda(arv->raiz, valor);
        } else {
            inserirDireita(arv->raiz, valor); 
        }
    }
}

void imprimir(No *raiz){
    if(raiz != NULL){
        imprimir(raiz->esquerda);
        printf("%d ", raiz->conteudo);
        imprimir(raiz->direita);
    }
}

int main(){
    int op, valor;
    ArvB arv = (ArvB)malloc(sizeof(ArvB));
    arv->raiz = NULL;

    do{
        printf("1 - Inserir\n2 - Imprimir\n0 - Sair\n");
        scanf("%d", &op);
        switch(op){
            case 1:
                printf("\nDigite o valor: ");
                scanf("%d", &valor);
                printf("\n");
                inserir(arv, valor);
                break;
            case 2:
                imprimir(arv->raiz);
                printf("\n"); 
                break;
        }
    } while(op != 0);

   
    free(arv);
    return 0;
}