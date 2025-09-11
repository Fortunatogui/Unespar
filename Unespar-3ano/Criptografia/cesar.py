from conversoes import *

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

def cesar_dec(byte_array, deslocamento):
    return cesar_enc(byte_array, (26 - deslocamento) % 26)

def test_cesar():
    string = "um texto apenas com caracteres minusculos e sem acentos, caracteres que nao sao minusculos ou possuem acentos sao desconsiderados nas cifras de cesar e vigenere"
    bytes_cifrados = cesar_enc(string_para_ascii(string), 18)
    print(ascii_para_string(bytes_cifrados))
    print()
    bytes_originais = cesar_dec(bytes_cifrados, 18)
    print(ascii_para_string(bytes_originais))

test_cesar()