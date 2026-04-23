import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://localhost:9876/")

def listar_livros():
    lista = proxy.listar_livros()
    print("Titulo | Autor | Edição | Disponível?")
    for l in lista:
        disponiveis = "Sim" if l["Disponível"] else "Não"
        print(f"{l['Titulo']} | {l['Autor']} | {l['edição']} | {disponiveis}")
    input("Digite uma tecla para continuar...")

def buscar_autor():
    str_busca = input("Digite o nome do autor: ")
    resultado = proxy.buscar_por_autor(str_busca)

    if len(resultado) == 0:
        print("Nenhum livro encontrado para o autor informado.")
    else:
        for l in resultado:
            disponivel = "Sim" if l["Disponível"] else "Não"
            print(f"{l['Titulo']} | {l['Autor']} | {l['edição']} | {disponivel}")

while True:
    print("\n\n===Biblioteca da Unespar===")
    print("[1] Listar Livros")
    print("[2] Buscar por Autor")
    print("[3] Sair")

    opcao = input("Escolha uma opção: ")

    if opcao == "1":
        listar_livros()
    elif opcao == "2":
        buscar_autor()
    elif opcao == "3":
        print("Saindo...")
        break
    else: 
        print("Opção inválida. Tente novamente.")