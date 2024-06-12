#include <stdio.h>
#include <stdlib.h>

int main(){
	
	int a, b; // cria as variaveis 
	a = 10, b = 20; // declara um valor inteiro as variavies
	
	int *p, *q; // cria os ponteiros
	
	p = &a; // p recebe o endere�o de a
	q = &b; // q recebe o endere�o de b
	
	*p = 30; // altera a variavel pelo endere�o de a
	*q = 40; // altera a variavel pelo endere�o de b
	
	printf("%p %d\n", &a, a);//&a fornece o endere�o da vari�vel, a � o valor da vari�vel a;
	printf("%p %d\n", &b, b);//&b fornece o endere�o da vari�vel, b � o valor da vari�vel b;
	printf("%p %p %d\n", &p, p, *p);//&p fornece o endere�o da vari�vel p , p � o valor da vari�vel p ,*p fornece o valor que p esta enxergando(a);
	printf("%p %p %d\n", &q, q, *q);//&q fornece o endere�o da vari�vel q, q � o valor da vari�vel q ,*q fornece o valor que p esta enxergando(a);

}

