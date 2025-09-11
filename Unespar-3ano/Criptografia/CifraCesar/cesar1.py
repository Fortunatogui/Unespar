from conversoes import *
ascii = "o jwro gsa ziho s ia aof acfhc bc qsbhfc rc qcfoqoc."

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
    for i in range(1, 26): 
     bytes_originais = cesar_dec(ascii, i)
     print(ascii_para_string(bytes_originais))
     print("Deslocamento: " + str(i))

test_cesar()