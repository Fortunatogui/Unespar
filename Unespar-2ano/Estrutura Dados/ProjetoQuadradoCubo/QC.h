#ifndef QC_H_INCLUDED
#define QC_H_INCLUDED

	typedef struct {
		int numero;
	}QuadradoCubo;
	
	QuadradoCubo criarQuadradoCubo(int numero);
	int calcularCubo(QuadradoCubo qc);
	int calcularQuadrado(QuadradoCubo qc);
	
	#endif //QC_H_INCLUDED
