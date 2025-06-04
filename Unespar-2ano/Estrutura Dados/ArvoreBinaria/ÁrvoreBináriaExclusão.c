#include<stdio.h>
#include<stdlib.h>

// Estrutura de um nó da árvore binária, com conteúdo e ponteiros para os filhos esquerdo e direito
typedef struct no {
    int conteudo;
    struct no *esquerda, *direita;
} No;

// Estrutura para a árvore binária que contém a raiz da árvore
typedef struct {
    No *raiz;
} ArvB;

// Função para criar um novo nó da árvore com o valor fornecido
No* criarNo(int valor) {
    No *novo = (No*) malloc(sizeof(No));  // Aloca memória para o novo nó
    novo->conteudo = valor;               // Atribui o valor ao nó
    novo->esquerda = NULL;                // Inicializa os ponteiros esquerdo e direito
    novo->direita = NULL;
    return novo;                          // Retorna o nó criado
}

// Função principal de inserção: Insere um valor na árvore de forma recursiva
void inserir(No **raiz, int valor) {
    if (*raiz == NULL) {
        *raiz = criarNo(valor);  // Se a raiz estiver vazia, cria um novo nó
    } else {
        if (valor < (*raiz)->conteudo)     // Se o valor for menor, insere à esquerda
            inserir(&(*raiz)->esquerda, valor);
        else if (valor > (*raiz)->conteudo) // Se o valor for maior, insere à direita
            inserir(&(*raiz)->direita, valor);
    }
}

// Função para imprimir a árvore em ordem crescente (in-order traversal)
void imprimir(No *raiz) {
    if (raiz != NULL) {
        imprimir(raiz->esquerda);       // Percorre recursivamente a subárvore esquerda
        printf("%d  ", raiz->conteudo); // Imprime o valor do nó atual
        imprimir(raiz->direita);        // Percorre recursivamente a subárvore direita
    }
}

// Função para encontrar o nó com o menor valor na subárvore (usado na remoção)
No* menorValor(No *no) {
    No *atual = no;
    while (atual && atual->esquerda != NULL) {
        atual = atual->esquerda;  // Percorre à esquerda até encontrar o menor valor
    }
    return atual;
}

// Função para remover um nó da árvore binária (caso sem filhos, com 1 ou 2 filhos)
No* removerNo(No *raiz, int chave) {
    if (raiz == NULL) {
        printf("Valor não encontrado!\n");  // Se o nó não existe
        return NULL;
    }

    // Busca recursiva pelo nó que contém o valor a ser removido
    if (chave < raiz->conteudo) {
        raiz->esquerda = removerNo(raiz->esquerda, chave); // Busca na subárvore esquerda
    } else if (chave > raiz->conteudo) {
        raiz->direita = removerNo(raiz->direita, chave);   // Busca na subárvore direita
    } else {
        // Caso 1: Nó sem filhos
        if (raiz->esquerda == NULL && raiz->direita == NULL) {
            printf("Removendo nó sem filhos: %d\n", raiz->conteudo);
            free(raiz);  // Remove o nó sem filhos
            return NULL;
        }

        // Caso 2: Nó com um único filho
        if (raiz->esquerda == NULL || raiz->direita == NULL) {
            No *temp = (raiz->esquerda != NULL) ? raiz->esquerda : raiz->direita; // Caso de 1 filho
            printf("Removendo no com 1 filho: %d\n", raiz->conteudo);
            free(raiz);  // Remove o nó e faz a ligação com o único filho
            return temp;
        }

        // Caso 3: Nó com dois filhos
        if (raiz->esquerda != NULL && raiz->direita != NULL) {
            No *sucessor = menorValor(raiz->direita);  // Encontra o sucessor (menor valor na subárvore direita)
            printf("Removendo no com 2 filhos: %d, substituído por %d\n", raiz->conteudo, sucessor->conteudo);
            raiz->conteudo = sucessor->conteudo;       // Substitui o valor do nó pelo sucessor
            raiz->direita = removerNo(raiz->direita, sucessor->conteudo); // Remove o sucessor da subárvore direita
        }
    }
    return raiz;
}

// Função principal que controla a inserção, impressão e remoção de nós
int main() {
    ArvB arv;
    arv.raiz = NULL;
    int op, valor;

    // Menu para inserir, imprimir e remover nós da árvore
    do {
        printf("\n0 - Sair\n1 - Inserir\n2 - Imprimir\n3 - Remover no\n");
        scanf("%d", &op);

        switch (op) {
            case 0:
                printf("\nSaindo...\n");
                break;
            case 1:
                printf("Digite um valor: ");
                scanf("%d", &valor);
                inserir(&arv.raiz, valor);
                break;
            case 2:
                printf("\nImpressao da arvore binaria:\n");
                imprimir(arv.raiz);
                printf("\n");
                break;
            case 3:
                printf("Digite o valor a ser removido: ");
                scanf("%d", &valor);
                arv.raiz = removerNo(arv.raiz, valor);
                break;
        }
    } while (op != 0);

    return 0;
}
