#include <stdio.h>
#include <stdlib.h>
#include "QC.h"

int main(){
	
	QuadradoCubo cuboQuadrado = criarQuadradoCubo(5.0);
	
	int cubo = calcularCubo(cuboQuadrado);
	printf("Numero ao cubo: %d\n", cubo);
	
	int quadrado = calcularQuadrado(cuboQuadrado);
	printf("Numero ao quadrdo: %d\n", quadrado);
	
	return 0;
}
