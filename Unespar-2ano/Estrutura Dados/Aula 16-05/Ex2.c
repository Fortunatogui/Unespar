#include <stdio.h>
#include <stdlib.h>

int main() {
    int *valores; 
    int soma = 0;
    int n = 5; 
    float media = 0;

    valores = malloc(n * sizeof(int));

    for (int i = 0; i < n; i++) {
        printf("Digite o valor %d: ", i + 1);
        scanf("%d", &valores[i]);

        soma += valores[i];
    }
    	
    	media = soma/5;
    	
    printf("Media dos valores: %f", media);
    printf("Soma dos valores: %d\n", soma);

    free(valores); 
    return 0;
}

