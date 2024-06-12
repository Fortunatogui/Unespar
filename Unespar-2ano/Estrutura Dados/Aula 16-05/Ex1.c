#include <stdio.h>
#include <stdlib.h>

int main() {
    int *valores; 
    int soma = 0;
    int n = 5; 

    valores = malloc(n * sizeof(int));
    if (valores == NULL) { 
        printf("Erro na alocacao de memoria\n");
        return 1; 
    }

    for (int i = 0; i < n; i++) {
        printf("Digite o valor %d: ", i + 1);
        scanf("%d", &valores[i]);

        soma += valores[i];
    }

    printf("Soma dos valores: %d\n", soma);

    free(valores); 
}

