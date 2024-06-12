#include <stdio.h>
#include <stdlib.h>
#include "retan.h"

int main(){
    //Exemplo de uso do TAD Retângulo
    Retangulo meuRetangulo = criarRetangulo(5.0,3.0); //retângulo com essas dimensões

    //cálculo e exibição da área
    float area = calcularArea(meuRetangulo);
    printf("A area do retangulo e: %.2f", area);
    return 0;
}
