# Assumindo que as funções auxiliares string_para_ascii e ascii_para_string
# estão definidas no módulo Cesar.py da seguinte forma:
def string_para_ascii(s):
    return [ord(c) for c in s]

def ascii_para_string(byte_array):
    return "".join([chr(b) for b in byte_array])

# --- Código do usuário fornecido na pergunta ---
def vigenere_enc(byte_array, chave):
    res = []
    i = 0
    # Adicionado um teste para evitar divisão por zero se a chave estiver vazia
    if not chave:
        return byte_array
    for b in byte_array:
        if (b >= 97 and b <= 122):
            letra = b - 97
            nova_letra = (letra + chave[i]) % 26
            res.append(97 + nova_letra)
            i = (i + 1) % len(chave)
        else:
            res.append(b)
    return res

def vigenere_dec(byte_array, chave):
    chave_inversa = []
    for b in chave:
        chave_inversa.append((26 - b) % 26)
    return vigenere_enc(byte_array, chave_inversa)

# --- Função de força bruta corrigida ---
def teste_vig_corrigido():
    string = "udg t jxtve mq fimq jyn vkepq gex ox jjet gjntv x sni jplirq jyn c fsavx hjsnmuq xq zwx eltxhrvh rjq fi lcei j ceqj pxq j htpj rhvzwx e vgmemg wi vkf i x sni nw zvrvh qju t sdvke vgmemg x gjnfe lntvj sni j onwret udg xwlwms jq eswix wnlt prpwe jkghj sni jotvpwke zwx e vwelnt jyn gn evq lisc tqjft qnufs jhtwccwe yqkudg t qnvthn fx qro x tjtmmmc feb c hyctt qnvthn g ledfthn eeeac jyn cl tjntzacl udg xy oces wch wnltq jtfemcl gxoh tagvi wgf eattwccweb ehq ogkzxt ttnptw pwtvmcweb pt euot vjtt txtjyn c ficcwi mg fmv g h udg xy lntqx otw j qnxac ficcwi n c iei sni jotvac jyn c oswvthn fx ma gffxtt wn vkewuysaox rj etpvc vpjtt i zwx e jplmj sni vg tvaclxj c veapx wnlt yv fbe jrtkjft txtjyn c ficcwi mg fmv g h udg iiwuh qju t sdvke vgmemg x gqcfe lntvj sni x oxhx ft wxnbhjq li jhtwcg x udg h ekttgx ehqrih qnufs bg msapx ex oxrxu tgnkmeege udg h ibrxpqq mvjit rj htgn wf facghx ttwcth hn ceiptbe yclwjft txtjyn c ficcwi mg fmv g eivdkewet e xwmvj oxxjfx i jnfe lntvj sni wch wnlt tagvmbq feru ws zwx yvc tpnikmj rtvj cjyrgmea c tpvc tjukme n sni x vxy bkeiwebs vg tfacvi lcwe mkt txtjyn c ficcwi mg fmv g tfakzs vcl e xwmvj oxxjfx i lcgwjeh hj etqrpaemc jyn c tvcg fi jdke j glxacwe vglqx sni wcwe bcbfj g jyn pbrpwxq j cmvjrtpqg isasni j oxxjfx hn obq n reecgbe n c hyctt qnvthn g vewets acke n sni j obrqc esdenvj uxnj cftjtthj rhvzwx e vgmemg wi vkf i johv n c hyctt qnvthn vtqkgf i ljtqj eeeac"
    ascii_bytes = string_para_ascii(string)

    print("Procurando por uma chave de 3 letras...")
    # Itera por todas as 17.576 chaves possíveis de 3 letras
    for i in range(26):
        for j in range(26):
            for k in range(26):
                chave = [i, j, k]
                resultado_bytes = vigenere_dec(ascii_bytes, chave)
                resultado_string = ascii_para_string(resultado_bytes)
                
                # Verifica se o resultado contém uma palavra comum em português (" que ")
                if " que " in resultado_string:
                    key_string = ascii_para_string([c + 97 for c in chave])
                    print(f"\n Possível chave encontrada: '{key_string}' que é {chave}")
                    print("\n--- Texto Decifrado ---")
                    print(resultado_string)
                    return # Encerra após encontrar uma chave plausível

# Execute a função corrigida
teste_vig_corrigido()