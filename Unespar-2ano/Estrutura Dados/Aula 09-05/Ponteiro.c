#include <stdio.h>
#include <stdlib.h>

int main (){
	
	//valor � varivael que ser� apontado pelo ponteiro
	int valor = 27;
	//declara��o de vari�vel ponteiro
	int *ptr;
	//atribuindo o endere�o da vari�vel valor ao ponteiro
	ptr = &valor;
	
	printf("Utilzando ponteiro\n\n");
	printf("Conteudo da variavel valor: %d\n", valor);
	printf("Endereco da variavel valor: %x\n", &valor);//x mostra hexadecimal
	printf("Conteudo da variavel ponteiro ptr: %x\n", ptr);//Conteudo da variavel
	
	return 0;
	
}
