#ifndef RETAN_H_INCLUDED
#define RETAN_H_INCLUDED

typedef struct{
    float comprimento;
    float largura;
}Retangulo;

Retangulo criarRetangulo(float comprimento, float largura);
float calcularArea(Retangulo ret);

#endif // RETAN_H_INCLUDED
