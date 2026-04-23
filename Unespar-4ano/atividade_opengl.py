from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *
import os  # Necessário para o comando os._exit(0)

# Variável para controlar o ângulo de rotação # [cite: 14, 16]
angulo_rotacao = 0.0

def inicializar():
    """Define a cor de fundo e as configurações iniciais # [cite: 10]"""
    glClearColor(0.2, 0.3, 0.3, 1.0)  # Cor de fundo (cinza azulado) # [cite: 43]
    gluOrtho2D(-2.0, 2.0, -2.0, 2.0)  # Define a área de visualização

def desenhar_cena():
    """Função principal de desenho # [cite: 34]"""
    global angulo_rotacao
    
    # Limpa a tela corretamente # [cite: 11, 44]
    glClear(GL_COLOR_BUFFER_BIT)
    
    glPushMatrix()  # Salva o estado atual da matriz
    
    # Aplica a transformação de rotação # [cite: 14, 16, 49, 57]
    glRotatef(angulo_rotacao, 0, 0, 1)
    
    # 1. Desenho do corpo da casa (Primitiva: Quadrado/GL_QUADS) 
    glColor3f(0.8, 0.5, 0.2)  # Cor marrom # [cite: 13, 48, 54]
    glBegin(GL_QUADS) # [cite: 45]
    glVertex2f(-0.7, -0.7)  # Inferior esquerdo # [cite: 47, 53]
    glVertex2f(0.7, -0.7)   # Inferior direito
    glVertex2f(0.7, 0.3)    # Superior direito
    glVertex2f(-0.7, 0.3)   # Superior esquerdo
    glEnd() # [cite: 46]

    # 2. Desenho do telhado (Primitiva: Triângulo/GL_TRIANGLES) # [cite: 12, 52]
    glColor3f(0.9, 0.1, 0.1)  # Cor vermelha 
    glBegin(GL_TRIANGLES)
    glVertex2f(-0.8, 0.3)   # Ponta esquerda
    glVertex2f(0.8, 0.3)    # Ponta direita
    glVertex2f(0.0, 0.9)    # Topo
    glEnd()

    # 3. Desenho da porta (Primitiva: Retângulo/GL_QUADS) # [cite: 12, 52]
    glColor3f(0.4, 0.2, 0.1)  # Marrom escuro 
    glBegin(GL_QUADS)
    glVertex2f(-0.2, -0.7)
    glVertex2f(0.2, -0.7)
    glVertex2f(0.2, -0.1)
    glVertex2f(-0.2, -0.1)
    glEnd()

    glPopMatrix()  # Restaura o estado da matriz
    glutSwapBuffers()  # Atualiza a exibição

def teclado(tecla, x, y):
    """Responde a comandos do teclado # [cite: 17, 41, 60]"""
    global angulo_rotacao
    
    # Se a tecla 'Espaço' (ASCII 32) for pressionada, rotaciona # [cite: 62]
    if tecla == b' ':
        angulo_rotacao += 10.0  # Incrementa o ângulo # [cite: 63]
    
    # Se a tecla 'ESC' for pressionada, fecha o programa
    if tecla == b'\x1b':
        os._exit(0)
        
    glutPostRedisplay()  # Solicita que a tela seja redesenhada # [cite: 64]

def principal():
    """Configuração da janela e loop principal # [cite: 31]"""
    glutInit()  # Inicializa o GLUT # [cite: 38]
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB)
    glutInitWindowSize(600, 600)  # Define o tamanho da janela
    glutCreateWindow(b"Atividade OpenGL - Casa com Rotacao") # [cite: 39]
    
    inicializar()
    
    # Define as funções de callback # [cite: 34, 40, 41]
    glutDisplayFunc(desenhar_cena)
    glutKeyboardFunc(teclado)
    
    # Inicia a execução contínua # [cite: 35, 42]
    glutMainLoop()

if __name__ == "__main__":
    principal()