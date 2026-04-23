from collections import Counter

texto = """udg t jxtve mq fimq jyn vkepq gex ox jjet gjntv x sni jplirq jyn c fsavx hjsnmuq
xq zwx eltxhrvh rjq fi lcei j ceqj pxq j htpj rhvzwx e vgmemg wi vkf i x sni nw zvrvh
qju t sdvke vgmemg x gjnfe lntvj sni j onwret udg xwlwms jq eswix wnlt prpwe jkghj
sni jotvpwke zwx e vwelnt jyn gn evq lisc tqjft qnufs jhtwccwe yqkudg t qnvthn fx qro
x tjtmmmc feb c hyctt qnvthn g ledfthn eeeac jyn cl tjntzacl udg xy oces wch wnltq
jtfemcl gxoh tagvi wgf eattwccweb ehq ogkzxt ttnptw pwtvmcweb pt euot vjtt txtjyn
c ficcwi mg fmv g h udg xy lntqx otw j qnxac ficcwi n c iei sni jotvac jyn c oswvthn fx
ma gffxtt wn vkewuysaox rj etpvc vpjtt i zwx e jplmj sni vg tvaclxj c veapx wnlt yv
fbe jrtkjft txtjyn c ficcwi mg fmv g h udg iiwuh qju t sdvke vgmemg x gqcfe lntvj sni x
oxhx ft wxnbhjq li jhtwcg x udg h ekttgx ehqrih qnufs bg msapx ex oxrxu tgnkmeege
udg h ibrxpqq mvjit rj htgn wf facghx ttwcth hn ceiptbe yclwjft txtjyn c ficcwi mg fmv
g eivdkewet e xwmvj oxxjfx i jnfe lntvj sni wch wnlt tagvmbq feru ws zwx yvc tpnikmj
rtvj cjyrgmea c tpvc tjukme n sni x vxy bkeiwebs vg tfacvi lcwe mkt txtjyn c ficcwi
mg fmv g tfakzs vcl e xwmvj oxxjfx i lcgwjeh hj etqrpaemc jyn c tvcg fi jdke j glxacwe
vglqx sni wcwe bcbfj g jyn pbrpwxq j cmvjrtpqg isasni j oxxjfx hn obq n reecgbe n c
hyctt qnvthn g vewets acke n sni j obrqc esdenvj uxnj cftjtthj rhvzwx e vgmemg wi
vkf i johv n c hyctt qnvthn vtqkgf i ljtqj eeeac"""

# Remove espaços e quebras de linha para ter um texto contínuo
texto_limpo = "".join([c for c in texto if c.isalpha()])

# Define o número de grupos que queremos analisar (o provável comprimento da chave)
NUMERO_DE_GRUPOS = 4

# Itera para criar e analisar cada grupo
for i in range(NUMERO_DE_GRUPOS):
    # O "+1" é apenas para a exibição ficar mais intuitiva (Grupo 1, 2, 3, 4)
    print(f"\n--- ANÁLISE DO GRUPO {i + 1} ---")
    print(f"(Letras nos índices {i}, {i + NUMERO_DE_GRUPOS}, {i + 2*NUMERO_DE_GRUPOS}, etc.)")
    
    # Seleciona as letras para o grupo atual usando fatiamento (slicing)
    # Ex: texto_limpo[0::4] pega todas as letras começando do índice 0, pulando de 4 em 4
    letras_do_grupo = texto_limpo[i::NUMERO_DE_GRUPOS]
    
    # Conta as frequências das letras nesse grupo
    contagem = Counter(letras_do_grupo)
    
    # Mostra o resultado em ordem decrescente de frequência
    for letra, freq in contagem.most_common():
        print(f"{letra} -> {freq}")