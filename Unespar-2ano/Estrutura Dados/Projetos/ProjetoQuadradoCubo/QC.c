#include<stdio.h>
#include<stdlib.h>
#include "QC.h"

	QuadradoCubo criarQuadradoCubo(int numero){
		
		QuadradoCubo qc;
		qc.numero = numero;
		
		return qc;
	}
	
	int calcularCubo(QuadradoCubo qc){
		return qc.numero * qc.numero * qc.numero;
	}
	int calcularQuadrado(QuadradoCubo qc){
		return qc.numero * qc.numero;
	}
