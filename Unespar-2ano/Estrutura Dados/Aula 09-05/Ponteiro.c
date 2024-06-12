#include <stdio.h>
#include <stdlib.h>

int main (){
	
	//valor é varivael que será apontado pelo ponteiro
	int valor = 27;
	//declaração de variável ponteiro
	int *ptr;
	//atribuindo o endereço da variável valor ao ponteiro
	ptr = &valor;
	
	printf("Utilzando ponteiro\n\n");
	printf("Conteudo da variavel valor: %d\n", valor);
	printf("Endereco da variavel valor: %x\n", &valor);//x mostra hexadecimal
	printf("Conteudo da variavel ponteiro ptr: %x\n", ptr);//Conteudo da variavel
	
	return 0;
	
}
