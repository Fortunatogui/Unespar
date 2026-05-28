import glfw
from OpenGL.GL import *

triangulos_completos = [] # Armazena as listas de triângulos finalizados. Cada elemento é uma lista de 3 tuplas (x, y)
pontos_temporarios = [] # Armazena os pontos do triângulo em construção, assim que o 3º ponto é adicionado, os pontos formam um triângulo e a lista é limpa

# Variáveis globais para armazenar as dimensões da janela (usadas na conversão)
largura_janela = 800
altura_janela = 600

# Converte as coordenadas do pixel clicado (espaço de tela) para as coordenadas do OpenGL (-1.0 a 1.0).
def converter_coordenadas(x_tela, y_tela): 
    # X da tela: 0 (esquerda) a largura (direita) -> OpenGL: -1.0 a 1.0
    x_gl = (x_tela / largura_janela) * 2.0 - 1.0 
    # Y da tela: 0 (topo) a altura (base) -> OpenGL: 1.0 a -1.0 (Y é invertido)
    y_gl = 1.0 - (y_tela / altura_janela) * 2.0 
    
    return x_gl, y_gl

# Captura o clique do mouse para definir os vértices
def evento_clique_mouse(janela, botao, acao, modificadores):


    # Só processa quando o botão esquerdo for pressionado (evita registrar o "soltar" do botão)
    if botao == glfw.MOUSE_BUTTON_LEFT and acao == glfw.PRESS:
        x_tela, y_tela = glfw.get_cursor_pos(janela)  # Pega a posição do cursor
        
        x_gl, y_gl = converter_coordenadas(x_tela, y_tela) # Converte para coordenadas do OpenGL
        
        pontos_temporarios.append((x_gl, y_gl)) # Armazena o ponto temporário
        
        # Verifica o controle de estado: se completou 3 pontos, forma o triângulo
        if len(pontos_temporarios) == 3:
            triangulos_completos.append(list(pontos_temporarios)) # Adiciona uma cópia da lista atual aos triângulos completos
            pontos_temporarios.clear() # Limpa o estado temporário para o próximo triângulo

# Permite limpar a tela pressionando a tecla 'C'
def evento_teclado(janela, tecla, codigo_tecla, acao, modificadores):
    global pontos_temporarios, triangulos_completos
    if tecla == glfw.KEY_C and acao == glfw.PRESS:
        triangulos_completos.clear()
        pontos_temporarios.clear()

# Atualiza as variáveis de dimensão caso o usuário redimensione a janela
def evento_redimensionar_janela(janela, largura, altura):
    global largura_janela, altura_janela
    largura_janela = largura
    altura_janela = altura
    glViewport(0, 0, largura, altura)


# Redesenha todos os elementos da cena a cada iteração do loop principal
def desenhar_cena():
    glClear(GL_COLOR_BUFFER_BIT)
    
    # Desenha todos os triângulos já concluídos
    if triangulos_completos:
        glColor3f(0.2, 0.6, 1.0) # Azul claro
        glBegin(GL_TRIANGLES)
        for triangulo in triangulos_completos:
            for vertice in triangulo:
                glVertex2f(vertice[0], vertice[1])
        glEnd()
        
        # Desenha o contorno dos triângulos pra melhor visualização
        glColor3f(1.0, 1.0, 1.0) # Branco
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE)
        glBegin(GL_TRIANGLES)
        for triangulo in triangulos_completos:
            for vertice in triangulo:
                glVertex2f(vertice[0], vertice[1])
        glEnd()
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL) # Volta pro modo de preenchimento normal

    # Desenha os pontos temporários
    if pontos_temporarios:
        glColor3f(1.0, 0.2, 0.2) # Vermelho
        glPointSize(8.0) # Aumenta o tamanho do ponto para ser visível
        glBegin(GL_POINTS)
        for p in pontos_temporarios:
            glVertex2f(p[0], p[1])
        glEnd()
        
        # Se já tiver 2 pontos, desenha uma linha conectando eles
        if len(pontos_temporarios) == 2:
            glBegin(GL_LINES)
            glVertex2f(pontos_temporarios[0][0], pontos_temporarios[0][1])
            glVertex2f(pontos_temporarios[1][0], pontos_temporarios[1][1])
            glEnd()


def main():
    if not glfw.init():
        return
    
    # Cria a janela
    janela = glfw.create_window(largura_janela, altura_janela, "Exercicio 2 - Triangulos (Aperte 'C' para limpar)", None, None)
    if not janela:
        glfw.terminate()
        return

    glfw.make_context_current(janela)
    
    # Registra as funções de callback (eventos)
    glfw.set_mouse_button_callback(janela, evento_clique_mouse)
    glfw.set_key_callback(janela, evento_teclado)
    glfw.set_window_size_callback(janela, evento_redimensionar_janela) 
    
    # Cor de fundo (Cinza escuro)
    glClearColor(0.1, 0.1, 0.1, 1.0)

    # Loop da aplicação
    while not glfw.window_should_close(janela):
        glfw.poll_events() # Processa eventos (cliques e teclado)
        
        desenhar_cena() # Redesenha tudo
        
        glfw.swap_buffers(janela) # Troca os buffers

    glfw.terminate()

if __name__ == "__main__":
    main()