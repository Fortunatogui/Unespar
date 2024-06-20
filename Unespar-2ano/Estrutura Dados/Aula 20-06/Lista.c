#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    int num;
    struct No *prox; // Ponteiro que aponta para o próximo nó.
} no;

no *criar_no() { // Função para alocar memória dinamicamente.
    no *novo = (no*)malloc(sizeof(no)); // Alocando memória dinâmica para o ponteiro nó.
    return novo;
}

no *inserir_no_inicio(no *lista, int dado) { // Função para inserir no início da lista.
    no *novo_no = criar_no(); // Criar o novo nó.
    novo_no->num = dado; // Atribuindo um número (dado) ao nó.

    if (lista == NULL) { // Verificando se a lista está vazia.
        novo_no->prox = NULL; // Se a lista está vazia, o próximo nó será NULL.
    } else { // Lista não está vazia.
        novo_no->prox = lista; // O novo nó aponta para o início da lista atual.
    }
    lista = novo_no; // A lista agora aponta para o novo nó.
    return lista;
}

no *inserirElementoFim(no *lista, int dado) {
    no *novoFim = criar_no();
    novoFim->num = dado;

    if (lista == NULL) { // Lista vazia.
        lista = novoFim;
        novoFim->prox = NULL;
    } else {
        no *aux = lista;
        while (aux->prox != NULL) {
            aux = aux->prox;
        }
        novoFim->prox = NULL;
        aux->prox = novoFim;
    }

    return lista;
}

void imprimir_lista(no *lista) {
    no *aux = lista; // O auxiliar percorre a lista para que nenhum dado da lista seja perdido.
    while (aux != NULL) {
        printf("%d ", aux->num); // Imprimindo o número armazenado no nó.
        aux = aux->prox; // Avançando para o próximo nó.
    }
    printf("\n"); // Para adicionar uma nova linha após a impressão da lista.
}

no *remover(no *lista, int dado) {
    if (lista == NULL) { // Se a lista está vazia.
        return NULL;
    }

    no *temp = lista;
    no *prev = NULL;

    if (temp != NULL && temp->num == dado) { // Se o nó a ser removido é o primeiro.
        lista = temp->prox; // Atualizando o início da lista.
        free(temp); // Liberando a memória do nó removido.
        return lista;
    }

    while (temp != NULL && temp->num != dado) { // Procurando o nó a ser removido.
        prev = temp;
        temp = temp->prox;
    }

    if (temp == NULL) { // Se o nó não foi encontrado.
        return lista;
    }

    prev->prox = temp->prox; // Removendo o nó.
    free(temp); // Liberando a memória do nó removido.

    return lista;
}

int main() {

    int x;
    
    no *lista = NULL; // Criando um ponteiro de lista que aponta para NULL.

    for(int i = 0; i < 20; i++){
    lista = inserir_no_inicio(lista, i + i);
    }
    lista = inserirElementoFim(lista, 30);

    imprimir_lista(lista);

    printf("Digite o numero que vc quer eliminar: \n");
    scanf("%d", &x);

    lista = remover(lista, x);
    imprimir_lista(lista);

    return 0;
}
