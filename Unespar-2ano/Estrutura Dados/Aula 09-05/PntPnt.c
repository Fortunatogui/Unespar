#include<stdio.h>
#include<stdlib.h>


int main(){
	
    int x, *ptrx, **pptrx; //inicializa variavel x, ponteiro e ponteiro de ponteiro
   
    x = 0;
    
    printf("Valor de x = %d\n",x); // mostra valor de x
    printf("Endereco de x: %x\n\n",&x); // mostra endereço da variavel x
   
    ptrx = &x; // declara ptrx como o endereço de x
    pptrx = &ptrx; // pptrx recebe o endereço de ptrx
    *ptrx += 10; // valor de ptrx é recebido com soma + 10
   
    printf("O valor de x = %d\n",x); 
    printf("Endereco apontado por ptrx = %x\n\n",ptrx);
    
    printf("Valor de variavel X que esta sendo apontada por ptrx %d\n",*ptrx);
    printf("Endereco de memoria da variavel ptrx: %x\n\n",&ptrx);
   
    **pptrx += 10; // adiciona mais 10 na variavel ptrx que esta apontada para x
   
    printf("Valor de x = %d\n",x);
    printf("Endereco apontado por **ptrx = %x\n\n",&pptrx);
    
    printf("Valor da variavel para a qual pptrx faz referencia = %d\n",**pptrx);
    printf("Endereco de memoria da variavel **pptrx %x\n",&pptrx);
}
