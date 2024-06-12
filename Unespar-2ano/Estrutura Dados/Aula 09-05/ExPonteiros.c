#include <stdio.h>
#include <stdlib.h>

int main(){
	
	int a, b; // cria as variaveis 
	a = 10, b = 20; // declara um valor inteiro as variavies
	
	int *p, *q; // cria os ponteiros
	
	p = &a; // p recebe o endereço de a
	q = &b; // q recebe o endereço de b
	
	*p = 30; // altera a variavel pelo endereço de a
	*q = 40; // altera a variavel pelo endereço de b
	
	printf("%p %d\n", &a, a);//&a fornece o endereço da variável, a é o valor da variável a;
	printf("%p %d\n", &b, b);//&b fornece o endereço da variável, b é o valor da variável b;
	printf("%p %p %d\n", &p, p, *p);//&p fornece o endereço da variável p , p é o valor da variável p ,*p fornece o valor que p esta enxergando(a);
	printf("%p %p %d\n", &q, q, *q);//&q fornece o endereço da variável q, q é o valor da variável q ,*q fornece o valor que p esta enxergando(a);

}

