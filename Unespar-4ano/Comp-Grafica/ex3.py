import glfw
from OpenGL.GL import *

formas = []

modo_atual = "P"
cor_atual = (1.0, 1.0, 1.0)  # Branco por padrão

def converter_para_opengl(x_mouse, y_mouse, window):
    largura, altura = glfw.get_window_size(window)
    x = (x_mouse / largura) * 2 - 1
    y = 1 - (y_mouse / altura) * 2
    return (x, y)

def atualizar_viewport(window):
    largura, altura = glfw.get_framebuffer_size(window)
    glViewport(0, 0, largura, altura)

def mouse_button_callback(window, button, action, mods):
    global formas, modo_atual, cor_atual
    if button == glfw.MOUSE_BUTTON_LEFT and action == glfw.PRESS:
        x_mouse, y_mouse = glfw.get_cursor_pos(window)
        x, y = converter_para_opengl(x_mouse, y_mouse, window)
        
        # Salva a forma junto com a cor atual selecionada
        formas.append({
            "tipo": modo_atual,
            "x": x,
            "y": y,
            "cor": cor_atual
        })

def key_callback(window, key, scancode, action, mods):
    global modo_atual, cor_atual, formas
    if action == glfw.PRESS or action == glfw.REPEAT:
        if key == glfw.KEY_ESCAPE:
            glfw.set_window_should_close(window, True)
            
        # Funcionalidade de Limpeza
        elif key == glfw.KEY_C:
            formas.clear()
            print("Tela limpa! Todos os objetos foram removidos.")
            
        # Controles de Forma
        elif key == glfw.KEY_P:
            modo_atual = "P"
            print("Modo alterado para: PONTO")
        elif key == glfw.KEY_L:
            modo_atual = "L"
            print("Modo alterado para: LINHA")
        elif key == glfw.KEY_T:
            modo_atual = "T"
            print("Modo alterado para: TRIÂNGULO")
        elif key == glfw.KEY_Q:
            modo_atual = "Q"
            print("Modo alterado para: QUADRADO")
            
        # Controles de Cor
        elif key == glfw.KEY_R:
            cor_atual = (1.0, 0.0, 0.0)
            print("Cor alterada para: VERMELHO")
        elif key == glfw.KEY_G:
            cor_atual = (0.0, 1.0, 0.0)
            print("Cor alterada para: VERDE")
        elif key == glfw.KEY_B:
            cor_atual = (0.0, 0.0, 1.0)
            print("Cor alterada para: AZUL")
        elif key == glfw.KEY_Y:
            cor_atual = (1.0, 1.0, 0.0)
            print("Cor alterada para: AMARELO")
        elif key == glfw.KEY_M:
            cor_atual = (1.0, 0.0, 1.0)
            print("Cor alterada para: MAGENTA")
        elif key == glfw.KEY_W:
            cor_atual = (1.0, 1.0, 1.0)
            print("Cor alterada para: BRANCO")

def renderizar_formas():
    tamanho = 0.08

    for forma in formas:
        cx, cy = forma["x"], forma["y"]
        r, g, b = forma["cor"]
        
        # Aplica a cor específica desta forma antes de desenhá-la
        glColor3f(r, g, b)

        if forma["tipo"] == "P":
            glPointSize(10)
            glBegin(GL_POINTS)
            glVertex2f(cx, cy)
            glEnd()

        elif forma["tipo"] == "L":
            glLineWidth(3)
            glBegin(GL_LINES)
            glVertex2f(cx - tamanho, cy) # Linha horizontal centralizada
            glVertex2f(cx + tamanho, cy)
            glEnd()

        elif forma["tipo"] == "T":
            glBegin(GL_TRIANGLES)
            glVertex2f(cx, cy + tamanho)          # Topo
            glVertex2f(cx - tamanho, cy - tamanho) # Base esquerda
            glVertex2f(cx + tamanho, cy - tamanho) # Base direita
            glEnd()

        elif forma["tipo"] == "Q":
            glBegin(GL_QUADS)
            glVertex2f(cx - tamanho, cy - tamanho) # Inferior Esquerdo
            glVertex2f(cx + tamanho, cy - tamanho) # Inferior Direito
            glVertex2f(cx + tamanho, cy + tamanho) # Superior Direito
            glVertex2f(cx - tamanho, cy + tamanho) # Superior Esquerdo
            glEnd()

def main():
    if not glfw.init():
        print("Erro ao inicializar o GLFW.")
        return
    
    window = glfw.create_window(800, 600, "Selecao de Formas Geometricas e Cores", None, None)

    if not window:
        print("Erro ao criar a janela principal.")
        return

    glfw.make_context_current(window)
    glfw.set_mouse_button_callback(window, mouse_button_callback)
    glfw.set_key_callback(window, key_callback)

    print("--- Controles de Formas ---")
    print("P: Ponto | L: Linha | T: Triângulo | Q: Quadrado")
    print("--- Controles de Cores ---")
    print("R: Vermelho | G: Verde | B: Azul")
    print("Y: Amarelo | M: Magenta | W: Branco")
    print("--- Utilitários ---")
    print("C: Limpar a tela")

    while not glfw.window_should_close(window):
        atualizar_viewport(window)

        glClear(GL_COLOR_BUFFER_BIT)
        
        renderizar_formas()

        glfw.swap_buffers(window)
        glfw.poll_events()
    
    glfw.terminate()

if __name__ == "__main__":
    main()