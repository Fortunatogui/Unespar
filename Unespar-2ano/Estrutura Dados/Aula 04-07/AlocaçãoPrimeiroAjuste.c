#include <stdio.h>
#include <stdlib.h>

//Definição da estrutura de um bloco de memória
typedef struct bloco {
    int tamanhoTotal; //Tamanho total do bloco
    int memoriaOcupada; //Quantidade de memória já ocupada no bloco
    struct bloco *prox; //Ponteiro para o próximo bloco na lista encadeada
} Bloco;

//Função para criar um novo bloco de memória
Bloco *criarBloco(int tamanho) {
    Bloco *novoBloco = (Bloco *)malloc(sizeof(Bloco)); //Aloca memória para o novo bloco
    novoBloco->tamanhoTotal = tamanho; //Define o tamanho total do bloco
    novoBloco->memoriaOcupada = 0; //Inicializa a memória ocupada como 0
    novoBloco->prox = NULL; //Inicializa o próximo bloco como NULL
    return novoBloco; //Retorna o novo bloco criado
}

//Função para adicionar um novo bloco à lista de blocos.
void adicionarBloco(Bloco **inicio, int tamanho) {
    Bloco *novoBloco = criarBloco(tamanho); //Cria um novo bloco com o tamanho especificado
    if (*inicio == NULL) { //Se a lista estiver vazia, insere o novo bloco no início
        *inicio = novoBloco;
    } else {               //caso contrário, percorre a lista até o final e insere o novo bloco
        Bloco *atual = *inicio;
        while (atual->prox != NULL) {
            atual = atual->prox;
        }
        atual->prox = novoBloco;
    }
}

//Função para tentar inserir uma quantidade de memória em um dos blocos
void inserirMemoria(Bloco *inicio, int quantidade) {
    Bloco *atual = inicio;
    while (atual != NULL) { //Percorre todos os blocos
        int espacoLivre = atual->tamanhoTotal - atual->memoriaOcupada; //Calcula o espaço livre no bloco atual
        if (quantidade <= espacoLivre) { //Se houver espaço suficiente, aloca a memória e retorna
            atual->memoriaOcupada += quantidade;
            printf("\nMemoria de %d inserida no bloco de %d de memoria. Espaco livre restante: %d\n", quantidade, atual->tamanhoTotal, espacoLivre - quantidade);
            return;
        }
        atual = atual->prox; //Move para o próximo bloco
    }
    //Se nenhum bloco tiver espaço suficiente, exibe uma mensagem
    printf("Nao ha espaco suficiente em nenhum bloco para %d.\n", quantidade);
}

//Função para mostrar o estado atual de todos os blocos.
void mostrarBlocos(Bloco *inicio) {
    Bloco *atual = inicio;
    printf("----------------------------------------------------------------\n");
    printf("| Bloco | Tamanho Total | Memoria Ocupada | Espaco Livre |\n");
    printf("-----------------------------------------------------------------\n");
    int contador = 1;
    while (atual != NULL) { //Percorre todos os blocos e exibe suas informações
        printf("|   %d   |      %d       |       %d        |     %d       |\n",
               contador++, atual->tamanhoTotal, atual->memoriaOcupada, atual->tamanhoTotal - atual->memoriaOcupada);
        atual = atual->prox;
    }
    printf("-------------------------------------------------\n");
}

//Função para liberar toda a memória alocada para os blocos
void liberarBlocos(Bloco **inicio) {
    Bloco *atual = *inicio;
    while (atual != NULL) { //Percorre todos os blocos e libera a memória alocada
        Bloco *temp = atual;
        atual = atual->prox;
        free(temp);
    }
    *inicio = NULL; //Define o início da lista como NULL
}

int main() {
    Bloco *inicio = NULL; //Inicializa a lista de blocos como vazia
    int nBlocos, tamanhoTotal, nQuantidades, quantidade;

    //Solicitação do número de blocos e seus tamanhos
    printf("Digite a quantidade de blocos: ");
    scanf("%d", &nBlocos);
    for (int i = 0; i < nBlocos; i++) {
        printf("\nDigite o tamanho total do bloco %d: ", i + 1);
        scanf("%d", &tamanhoTotal);
        adicionarBloco(&inicio, tamanhoTotal);
    }
    printf("\n");

    //Solicitaçao do número de quantidades de memória a serem alocadas e alocação delas
    printf("Digite o numero de memorias a serem alocadas: ");
    scanf("%d", &nQuantidades);
    for (int i = 0; i < nQuantidades; i++) {
        printf("\nDigite a quantidade de memoria que quer armazenar (%d): ", i + 1);
        scanf("%d", &quantidade);
        inserirMemoria(inicio, quantidade);
    }
    printf("\n");

    mostrarBlocos(inicio); //Mostra o estado final de todos os blocos.
    liberarBlocos(&inicio); //Libera a memória alocada para os blocos.

    return 0;
}