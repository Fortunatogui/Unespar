import glfw
from OpenGL.GL import *

posicao_clique = None # Armazena a posição onde o usuário clicou

# Variáveis globais para armazenar as dimensões da janela
largura_janela = 800
altura_janela = 600

# Converte as coordenadas de pixel para as coordenadas do OpenGL (-1.0 a 1.0)
def converter_coordenadas(x_tela, y_tela): 
    x_gl = (x_tela / largura_janela) * 2.0 - 1.0 
    y_gl = 1.0 - (y_tela / altura_janela) * 2.0 
    return x_gl, y_gl

# Callback para eventos de clique do mouse
def evento_clique_mouse(janela, botao, acao, modificadores):
    global posicao_clique

    # Atualiza a posição da figura quando o botão esquerdo é pressionado
    if botao == glfw.MOUSE_BUTTON_LEFT and acao == glfw.PRESS:
        x_tela, y_tela = glfw.get_cursor_pos(janela) 
        posicao_clique = converter_coordenadas(x_tela, y_tela)

# Callback para eventos de redimensionamento da janela
def evento_redimensionar_janela(janela, largura, altura):
    global largura_janela, altura_janela
    largura_janela = largura
    altura_janela = altura
    glViewport(0, 0, largura, altura)

# Desenha um foguete com coordenadas relativas ao ponto central (cx, cy) definido pelo clique do mouse
def desenhar_foguete(cx, cy):

    # Proporções base do foguete
    metade_largura = 0.1 
    metade_altura = 0.2 

    glLineWidth(2.0) # Para garantir que as linhas fiquem bem visíveis

    # PARTE 1: Corpo do foguete (GL_LINE_LOOP cria uma forma fechada - 4 segmentos)
    glColor3f(1.0, 1.0, 1.0) # Branco
    glBegin(GL_LINE_LOOP)
    glVertex2f(cx - metade_largura, cy - metade_altura) # Inferior esq
    glVertex2f(cx + metade_largura, cy - metade_altura) # Inferior dir
    glVertex2f(cx + metade_largura, cy + metade_altura) # Superior dir
    glVertex2f(cx - metade_largura, cy + metade_altura) # Superior esq
    glEnd()

    # PARTE 2: Bico do foguete (GL_LINE_STRIP conecta os pontos em sequência - 2 segmentos)
    glColor3f(1.0, 0.2, 0.2) # Vermelho
    glBegin(GL_LINE_STRIP)
    glVertex2f(cx - metade_largura, cy + metade_altura) # Base esquerda do bico
    glVertex2f(cx, cy + metade_altura + 0.15)           # Ponta do bico
    glVertex2f(cx + metade_largura, cy + metade_altura) # Base direita do bico
    glEnd()

    # PARTE 3: Asas direcionais e fogo (GL_LINES desenha pares de vértices isolados)
    glBegin(GL_LINES)
    
    # --- Asas (4 segmentos no total) ---
    glColor3f(0.2, 0.6, 1.0) # Azul

    # Asa esquerda (2 linhas)
    glVertex2f(cx - metade_largura, cy - metade_altura + 0.1)
    glVertex2f(cx - metade_largura - 0.1, cy - metade_altura - 0.05)
    glVertex2f(cx - metade_largura - 0.1, cy - metade_altura - 0.05)
    glVertex2f(cx - metade_largura, cy - metade_altura)

    # Asa direita (2 linhas)
    glVertex2f(cx + metade_largura, cy - metade_altura + 0.1)
    glVertex2f(cx + metade_largura + 0.1, cy - metade_altura - 0.05)
    glVertex2f(cx + metade_largura + 0.1, cy - metade_altura - 0.05)
    glVertex2f(cx + metade_largura, cy - metade_altura)

    # --- Fogo da propulsão (3 segmentos no total) ---
    glColor3f(1.0, 0.5, 0.0) # Laranja

    # Chama 1 (Esquerda)
    glVertex2f(cx - 0.05, cy - metade_altura)
    glVertex2f(cx - 0.08, cy - metade_altura - 0.15)
    # Chama 2 (Central - mais longa)
    glVertex2f(cx, cy - metade_altura)
    glVertex2f(cx, cy - metade_altura - 0.2)
    # Chama 3 (Direita)
    glVertex2f(cx + 0.05, cy - metade_altura)
    glVertex2f(cx + 0.08, cy - metade_altura - 0.15)
    
    glEnd()
    
    # Restaura a espessura da linha padrão
    glLineWidth(1.0)

# Limpa a tela e reseta as variáveis para começar um novo desenho
def desenhar_cena():
    glClear(GL_COLOR_BUFFER_BIT)
    
    # Se o usuário já clicou em algum lugar, desenha a figura naquela posição
    if posicao_clique is not None:
        desenhar_foguete(posicao_clique[0], posicao_clique[1])


def main():
    if not glfw.init():
        return
    
    # Cria a janela
    janela = glfw.create_window(largura_janela, altura_janela, "Exercicio 1 - Foguete", None, None)
    if not janela:
        glfw.terminate()
        return

    glfw.make_context_current(janela)
    
    # Registra os callbacks
    glfw.set_mouse_button_callback(janela, evento_clique_mouse)
    glfw.set_window_size_callback(janela, evento_redimensionar_janela) 
    
    glClearColor(0.05, 0.05, 0.1, 1.0) # Fundo (azul escuro)

    # Loop da aplicação
    while not glfw.window_should_close(janela):
        glfw.poll_events() # Processa os eventos
        desenhar_cena()
        glfw.swap_buffers(janela)

    glfw.terminate()

if __name__ == "__main__":
    main()