#include <stdio.h>
#include <stdlib.h>

int main() {
    int *v;  
    int n;  
    
    printf("Digite a quantidade de vetores: \n");
    scanf("%d", &n);  
    
    v = malloc(n * sizeof(int)); 
    
    printf("--------------------\n");
    printf("Insercao de vetores \n");
    
    for(int i = 0; i < n; i++) {
        scanf("%d", &v[i]);  
    }
    
    printf("--------------------\n");
    printf("Impressao de vetores \n");
    
    for(int i = 0; i < n; i++) {
        printf("%d\n", v[i]);  
    }
    
    free(v);  
    return 0;
}

