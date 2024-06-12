#include<stdio.h>
#include<stdlib.h>

	//Guilherme Fortunato Da Silva

	typedef struct Aluno{
		char nome[40];
		float nota, nota2;
	}aluno;
	
	
	int main(){
		
		aluno a[4];
		
		for(int i = 0 ; i < 4; i++){
			
			fflush(stdin);
			
			printf("Digite o nome do %d aluno: \n", i + 1);
			fgets(a[i].nome,sizeof(a[i].nome),stdin);
			
			printf("Digite a nota do %d aluno: \n", i + 1);
			scanf("%f", &a[i].nota);
			
			printf("Digite a nota 2 do %d aluno: \n", i + 1);
			scanf("%f", &a[i].nota2);
		}
		
		for(int i = 0 ; i < 4; i++){
		
			printf("O nome do %d aluno e: %s", i + 1, a[i].nome);
			printf("A nota do %d aluno e: %.1f\n", i + 1, a[i].nota);
			printf("A nota do %d aluno e: %.1f\n", i + 1, a[i].nota2);
		}
		
		return 0;
	}
