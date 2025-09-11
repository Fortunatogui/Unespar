from conversoes import *
ascii = "jx enwlnmxa jb kjcjcjb. nbcn yarwlryrx, zdn yjanln edupja, cnv dvj pajwmn brpwrorljljx oruxbxorlj. n j unr mj ermj. xb oxacnb nbvjpjv xb oajlxb, xb onurinb mnexajv xb carbcnb, xb yadmnwcnb udmrkarjv xb rwpnwdxb, n jbbrv ljvrwqj j qdvjwrmjmn."

def cesar_enc(byte_array, deslocamento):
    res = []
    for b in byte_array:
        if (b >= 97 and b<=122):
            letra = b - 97
            nova_letra = (letra + deslocamento) % 26
            res.append(97 + nova_letra)
        else:
            res.append(b)
    return res

def cesar_dec(frase, deslocamento):
     byte_array = string_para_ascii(frase)
     res = []
     for b in byte_array:
        if (b >= 97 and b<=122):
            letra = b - 97
            nova_letra = (letra - deslocamento) % 26
            res.append(97 + nova_letra)
        else:
            res.append(b)
     return res

def test_cesar():
    for i in range(1): 
     bytes_originais = cesar_dec(ascii, 9)
     print(ascii_para_string(bytes_originais))
     print("Deslocamento: " + str(9))

test_cesar()