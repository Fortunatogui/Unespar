from xmlrpc.server import SimpleXMLRPCServer

acervo = {
    1: {
        "titulo": "Sistemas Distribuidos", 
        "edicao": 4, 
        "ano": 2007, 
        "autor": "Coulouris",
        "exemplares_total": 5, 
        "exemplares_emprestados": 2},
}

def listar_livros():
    lista = []
    for livro in acervo.values():
        disponiveis = livro["exemplares_total"] > livro["exemplares_emprestados"]
        lista.append({"Titulo": livro['titulo'], "Autor": livro['autor'], "edição": livro['edicao'], "Disponível": disponiveis})
    return lista

def buscar_por_autor(str_busca):
    resultado = []
    for l in acervo.values():
        if str_busca.lower() in l["autor"].lower():
            disponiveis = l["exemplares_total"] > l["exemplares_emprestados"]
            resultado.append({"Titulo": l['titulo'], "Autor": l['autor'], "edição": l['edicao'], "Disponível": disponiveis})
     
    return resultado


#criar o processo

server = SimpleXMLRPCServer(("localhost", 9876))
print("Servidor XML-RPC rodando na porta 9876...")

server.register_function(listar_livros, "listar_livros")
server.register_function(buscar_por_autor, "buscar_por_autor")

server.serve_forever()