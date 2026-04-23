import base64

def ascii_para_string(array):
    s = ""
    for a in array:
        s = s + chr(a)
    return s

def string_para_ascii(string):
    a = []
    for s in string:
        a.append(ord(s))
    return a

def hex_para_bytes(hex_string):
    return bytes.fromhex(hex_string)

def bytes_para_hex(byte_array):
    return bytearray(byte_array).hex()

def bytes_para_base64(byte_array):
    return base64.b64encode(bytearray(byte_array))

def base64_para_bytes(base64_string):
    return base64.b64decode(base64_string)

def teste_conversoes():
    string = "um texto apenas com caracteres minusculos e sem acentos, caracteres que nao sao minusculos ou possuem acentos sao desconsiderados nas cifras de cesar e vigenere"
    print("String original: " + string)
    print()
    bytes_ascii = string_para_ascii(string)
    print("Versão em bytes (ascii): " + str(bytes_ascii))
    print()
    hex = bytes_para_hex(bytes_ascii)
    print("Versão hexadecimal: " + str(hex))
    print()
    base_64 = bytes_para_base64(bytes_ascii)
    print("Versão base64: " + str(base_64))
    print()
    print("Recuperando a versão original dos bytes (ascii): " + ascii_para_string(bytes_ascii))
    print()
    print("Recuperando a versão original da representação hexadecimal: " + ascii_para_string(hex_para_bytes(hex)))
    print()
    print("Recuperando a versão original da representação base64: " + ascii_para_string(base64_para_bytes(base_64)))

#teste_conversoes()