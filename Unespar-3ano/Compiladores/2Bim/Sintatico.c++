// Nome do arquivo: Sintatico.cpp
// Analisador sintático que gera uma Árvore Sintática Abstrata (AST)
// e a serializa para um arquivo de texto chamado "ast.txt".
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <regex>
#include <map>
#include <set>
#include <iomanip>
#include <sstream>

using namespace std;

// A estrutura do Token permanece a mesma.
struct Token {
    string lexema;
    string tipo;
    int linha;
};

// A estrutura do ASTNode permanece a mesma.
// Contém o tipo de nó (ex: "programa", "atribuicao"), um valor opcional
// (ex: nome de uma variável) e um vetor de filhos.
struct ASTNode {
    string tipo;
    string valor;
    vector<ASTNode*> filhos;
    int linha; // Adicionado para melhor rastreamento de erros no semântico.

    ASTNode(string t, string v = "", int l = -1) : tipo(t), valor(v), linha(l) {}

    // Destrutor que libera a memória de todos os nós filhos recursivamente.
    ~ASTNode() {
        for (ASTNode* filho : filhos) {
            delete filho;
        }
    }
};

// Variáveis globais para o processo de parsing.
vector<Token> tokens; // Armazena todos os tokens lidos do arquivo.
int pos = 0;          // Posição atual no vetor de tokens.
set<string> declaredLabels; // Armazena rótulos declarados para validação do 'goto'.
map<string, string> TabelaSimbolos; // Tabela de símbolos simples para o parser.

// Protótipos das funções de parsing para permitir chamadas mútuas.
ASTNode* programa();
ASTNode* bloco();
ASTNode* declaracao_rotulos();
ASTNode* declaracao_tipos();
ASTNode* declaracao_variaveis();
ASTNode* lista_identificadores();
ASTNode* tipo();
ASTNode* declaracao_funcao();
ASTNode* declaracao_procedimento();
ASTNode* parametros();
ASTNode* lista_comandos();
ASTNode* comando();
ASTNode* variavel();
ASTNode* expressao();
ASTNode* termo_logico();
ASTNode* expressao_relacional();
ASTNode* expressao_aritmetica();
ASTNode* termo_aritmetico();
ASTNode* fator();
ASTNode* chamada_subrotina();
ASTNode* lista_argumentos();

// --- FUNÇÕES UTILITÁRIAS DO PARSER ---

// Retorna o token na posição atual sem consumi-lo.
Token atual() {
    if (pos < (int)tokens.size()) return tokens[pos];
    return {"", "FIM_DE_ARQUIVO", -1};
}

// Verifica se o parser chegou ao final do vetor de tokens.
bool fimTokens() {
    return pos >= (int)tokens.size();
}

// Lança um erro sintático e encerra a execução.
ASTNode* syntaxError(const string& mensagem) {
    if (!fimTokens()) {
        cout << "Erro na linha " << atual().linha << ": " << mensagem << ". Token encontrado: '" << atual().lexema << "' do tipo '" << atual().tipo << "'\n";
    } else {
        cout << "Erro: " << mensagem << " no final do arquivo.\n";
    }
    exit(1);
    return nullptr;
}

// Consome o token atual se ele corresponder ao esperado (por lexema ou por tipo).
Token expect(const string& esperado, bool isType = false) {
    if (fimTokens()) return {"", "", -1};
    if (isType) {
        if (atual().tipo == esperado) {
            return tokens[pos++];
        }
    } else {
        if (atual().lexema == esperado) {
            return tokens[pos++];
        }
    }
    return {"", "", -1};
}


// --- FUNÇÕES DE PARSING (REGRAS DA GRAMÁTICA) ---

ASTNode* programa() {
    if (expect("Program").lexema.empty()) { return syntaxError("um programa deve comecar com a palavra-chave 'Program'"); }
    Token idToken = expect("Identificador", true);
    if (idToken.lexema.empty()) { return syntaxError("esperado um nome de identificador para o programa"); }
    string nomePrograma = idToken.lexema;
    if (atual().lexema == "(") {
        pos++;
        while (atual().lexema != ")" && !fimTokens()) {
            if(expect("Identificador", true).lexema.empty()) { return syntaxError("esperado identificador na lista de parametros do programa"); }
            if(atual().lexema == ",") pos++;
        }
        if (expect(")").lexema.empty()) { return syntaxError("esperado ')' para fechar a lista de parametros do programa"); }
    }
    if (expect(";").lexema.empty()) { return syntaxError("esperado ';' apos o cabecalho do programa"); }
    ASTNode* noBloco = bloco();
    if (!noBloco) return nullptr;
    if (expect(".").lexema.empty()) { return syntaxError("esperado '.' no final do programa"); }
    if (atual().tipo != "FIM_DE_ARQUIVO" && !fimTokens()) { return syntaxError("tokens inesperados '" + atual().lexema + "' apos o final do programa"); }
    ASTNode* noPrograma = new ASTNode("programa", nomePrograma, idToken.linha);
    noPrograma->filhos.push_back(noBloco);
    return noPrograma;
}

ASTNode* bloco() {
    ASTNode* noBloco = new ASTNode("bloco", "", atual().linha);
    if (atual().lexema == "label") { noBloco->filhos.push_back(declaracao_rotulos()); }
    if (atual().lexema == "type") { noBloco->filhos.push_back(declaracao_tipos()); }
    if (atual().lexema == "var") { noBloco->filhos.push_back(declaracao_variaveis()); }
    while (atual().lexema == "function" || atual().lexema == "procedure") {
        ASTNode* noSubRotina = nullptr;
        if (atual().lexema == "function") { noSubRotina = declaracao_funcao(); }
        else { noSubRotina = declaracao_procedimento(); }
        if (!noSubRotina) return nullptr;
        noBloco->filhos.push_back(noSubRotina);
    }
    if (expect("begin").lexema.empty()) { return syntaxError("esperado 'begin' para iniciar o bloco de comandos"); }
    noBloco->filhos.push_back(lista_comandos());
    if (expect("end").lexema.empty()) { return syntaxError("esperado 'end' para finalizar o bloco"); }
    return noBloco;
}

ASTNode* declaracao_rotulos() {
    Token labelToken = expect("label");
    ASTNode* node = new ASTNode("declaracao_rotulos", "", labelToken.linha);
    do {
        Token numToken = expect("Numero", true);
        if (numToken.lexema.empty()) return syntaxError("esperado um numero de rotulo na declaracao 'label'");
        declaredLabels.insert(numToken.lexema);
        node->filhos.push_back(new ASTNode("rotulo", numToken.lexema, numToken.linha));
        if (atual().lexema != ",") break;
        pos++;
    } while (true);
    if (expect(";").lexema.empty()) return syntaxError("esperado ';' para finalizar a declaracao de rotulos");
    return node;
}

ASTNode* declaracao_tipos() {
    Token typeToken = expect("type");
    ASTNode* node = new ASTNode("declaracao_tipos", "", typeToken.linha);
    while (atual().tipo == "Identificador") {
        Token idToken = expect("Identificador", true);
        TabelaSimbolos[idToken.lexema] = "tipo";
        if (expect("=").lexema.empty()) return syntaxError("esperado '=' na declaracao de tipo");
        ASTNode* noTipo = tipo();
        if (!noTipo) return nullptr;
        ASTNode* declTipo = new ASTNode("declaracao_tipo", idToken.lexema, idToken.linha);
        declTipo->filhos.push_back(noTipo);
        node->filhos.push_back(declTipo);
        if (expect(";").lexema.empty()) return syntaxError("esperado ';' apos cada declaracao de tipo");
    }
    return node;
}

ASTNode* declaracao_variaveis() {
    Token varToken = expect("var");
    ASTNode* noVars = new ASTNode("declaracao_variaveis", "", varToken.linha);
    while (atual().tipo == "Identificador") {
        ASTNode* noLista = lista_identificadores();
        if (!noLista) return nullptr;
        for(auto& filho : noLista->filhos) { TabelaSimbolos[filho->valor] = "variavel"; }
        if (expect(":").lexema.empty()) return syntaxError("esperado ':' entre os nomes das variaveis e o seu tipo");
        ASTNode* noTipo = tipo();
        if (!noTipo) return nullptr;
        if (expect(";").lexema.empty()) return syntaxError("esperado ';' ao final da declaracao de variavel");
        ASTNode* noDecl = new ASTNode("declaracao_variavel", "", noLista->linha);
        noDecl->filhos.push_back(noLista);
        noDecl->filhos.push_back(noTipo);
        noVars->filhos.push_back(noDecl);
    }
    return noVars;
}

ASTNode* lista_identificadores() {
    ASTNode* noLista = new ASTNode("lista_identificadores", "", atual().linha);
    do {
        Token idToken = expect("Identificador", true);
        if (idToken.lexema.empty()) return syntaxError("esperado um identificador na lista");
        noLista->filhos.push_back(new ASTNode("identificador", idToken.lexema, idToken.linha));
        if (atual().lexema != ",") break;
        pos++;
    } while (true);
    return noLista;
}

ASTNode* tipo() {
    Token tipoToken = atual();
    string lex = tipoToken.lexema;
    if (lex == "integer" || lex == "boolean" || lex == "string" || lex == "real") {
        pos++;
        return new ASTNode("tipo_primitivo", lex, tipoToken.linha);
    }
    if (lex == "array") {
        pos++;
        ASTNode* noArray = new ASTNode("tipo_array", "", tipoToken.linha);
        if (expect("[").lexema.empty()) return syntaxError("esperado '[' apos a palavra 'array'");
        Token inicio = expect("Numero", true);
        if(inicio.lexema.empty()) return syntaxError("esperado numero para indice inicial do array");
        noArray->filhos.push_back(new ASTNode("numero", inicio.lexema, inicio.linha));
        if (expect("..").lexema.empty()) return syntaxError("esperado '..' para separar os indices do array");
        Token fim = expect("Numero", true);
        if(fim.lexema.empty()) return syntaxError("esperado numero para indice final do array");
        noArray->filhos.push_back(new ASTNode("numero", fim.lexema, fim.linha));
        if (expect("]").lexema.empty()) return syntaxError("esperado ']' apos os indices do array");
        if (expect("of").lexema.empty()) return syntaxError("esperado 'of' na declaracao do array");
        ASTNode* tipoElem = tipo();
        if (!tipoElem) return nullptr;
        noArray->filhos.push_back(tipoElem);
        return noArray;
    }
    if(atual().tipo == "Identificador") {
        string idTipo = atual().lexema;
        pos++;
        return new ASTNode("tipo_identificador", idTipo, tipoToken.linha);
    }
    return syntaxError("esperado um tipo valido");
}

ASTNode* declaracao_funcao() {
    expect("function");
    Token idToken = expect("Identificador", true);
    if (idToken.lexema.empty()) return syntaxError("esperado um nome de identificador para a funcao");
    TabelaSimbolos[idToken.lexema] = "funcao";
    ASTNode* noFunc = new ASTNode("funcao", idToken.lexema, idToken.linha);
    ASTNode* noParams = nullptr;
    if (atual().lexema == "(") {
        pos++;
        if (atual().lexema != ")") noParams = parametros();
        if (expect(")").lexema.empty()) return syntaxError("esperado ')' para fechar a lista de parametros");
    }
    if (expect(":").lexema.empty()) return syntaxError("esperado ':' antes do tipo de retorno da funcao");
    ASTNode* noTipoRet = tipo();
    if (!noTipoRet) return nullptr;
    if (expect(";").lexema.empty()) return syntaxError("esperado ';' apos a assinatura da funcao");
    ASTNode* noBloco = bloco();
    if (!noBloco) return nullptr;
    if (expect(";").lexema.empty()) return syntaxError("esperado ';' apos o 'end' do bloco da funcao");
    noFunc->filhos.push_back(noParams ? noParams : new ASTNode("parametros_vazios", "", idToken.linha));
    noFunc->filhos.push_back(noTipoRet);
    noFunc->filhos.push_back(noBloco);
    return noFunc;
}

ASTNode* declaracao_procedimento() {
    expect("procedure");
    Token idToken = expect("Identificador", true);
    if (idToken.lexema.empty()) return syntaxError("esperado um nome de identificador para o procedimento");
    TabelaSimbolos[idToken.lexema] = "procedimento";
    ASTNode* noProc = new ASTNode("procedimento", idToken.lexema, idToken.linha);
    ASTNode* noParams = nullptr;
    if (atual().lexema == "(") {
        pos++;
        if (atual().lexema != ")") noParams = parametros();
        if (expect(")").lexema.empty()) return syntaxError("esperado ')' para fechar a lista de parametros");
    }
    if (expect(";").lexema.empty()) return syntaxError("esperado ';' apos a assinatura do procedimento");
    ASTNode* noBloco = bloco();
    if (!noBloco) return nullptr;
    if (expect(";").lexema.empty()) return syntaxError("esperado ';' apos o 'end' do bloco do procedimento");
    noProc->filhos.push_back(noParams ? noParams : new ASTNode("parametros_vazios", "", idToken.linha));
    noProc->filhos.push_back(noBloco);
    return noProc;
}

ASTNode* parametros() {
    ASTNode* noLista = new ASTNode("lista_parametros", "", atual().linha);
    do {
        Token varToken = atual();
        bool porReferencia = !expect("var").lexema.empty();
        ASTNode* idLista = lista_identificadores();
        if(!idLista) return nullptr;
        if(expect(":").lexema.empty()) return syntaxError("esperado ':' na declaracao de parametro");
        ASTNode* tipoParam = tipo();
        if(!tipoParam) return nullptr;
        ASTNode* paramGroup = new ASTNode(porReferencia ? "grupo_parametro_ref" : "grupo_parametro_valor", "", varToken.linha);
        paramGroup->filhos.push_back(idLista);
        paramGroup->filhos.push_back(tipoParam);
        noLista->filhos.push_back(paramGroup);
        if (atual().lexema != ";") break;
        pos++;
    } while (true);
    return noLista;
}

ASTNode* lista_comandos() {
    ASTNode* lista = new ASTNode("lista_comandos", "", atual().linha);
    while (true) {
        if (atual().lexema == "end" || atual().lexema == "else" || atual().tipo == "FIM_DE_ARQUIVO" || fimTokens()) { break; }
        ASTNode* cmd = comando();
        if (!cmd) {
            if (atual().lexema != "end" && atual().lexema != "else") { return syntaxError("comando invalido ou inesperado"); }
            break;
        }
        lista->filhos.push_back(cmd);
        if (atual().lexema == ";") {
            pos++;
            if (atual().lexema == "end") { break; }
        } else if (atual().lexema == "end" || atual().lexema == "else" || atual().tipo == "FIM_DE_ARQUIVO" || fimTokens()) {
            break;
        } else {
            return syntaxError("esperado ';' apos o comando ou um terminador de bloco (end/else)");
        }
    }
    return lista;
}

ASTNode* comando() {
    Token cmdToken = atual();
    if (cmdToken.tipo == "Identificador" && static_cast<size_t>(pos) + 1 < tokens.size() && tokens[pos + 1].lexema == ":") {
        if (declaredLabels.find(cmdToken.lexema) == declaredLabels.end()) { return syntaxError("uso de rotulo '" + cmdToken.lexema + "' que nao foi declarado na secao 'label'"); }
        pos += 2;
        cmdToken = atual(); // Atualiza o token após o rótulo
    }
    string lex = cmdToken.lexema;
    if (lex == "begin") {
        pos++;
        ASTNode* noComandosBloco = lista_comandos();
        if (!noComandosBloco) return nullptr;
        if (expect("end").lexema.empty()) return syntaxError("esperado 'end' apos bloco de comando aninhado");
        return noComandosBloco;
    }
    if (lex == "if") {
        pos++;
        ASTNode* cond = expressao();
        if (!cond) return nullptr;
        if (expect("then").lexema.empty()) return syntaxError("esperado 'then' apos a condicao do 'if'");
        ASTNode* noThen = comando();
        if (!noThen) return syntaxError("esperado um comando apos 'then'");
        ASTNode* noIf = new ASTNode("if", "", cmdToken.linha);
        noIf->filhos.push_back(cond);
        noIf->filhos.push_back(noThen);
        if (atual().lexema == "else") {
            pos++;
            ASTNode* noElse = comando();
            if (!noElse) return syntaxError("esperado um comando apos 'else'");
            noIf->filhos.push_back(noElse);
        }
        return noIf;
    }
    if (lex == "while") {
        pos++;
        ASTNode* cond = expressao();
        if (!cond) return nullptr;
        if (expect("do").lexema.empty()) return syntaxError("esperado 'do' apos a condicao do 'while'");
        ASTNode* noCmd = comando();
        if (!noCmd) return nullptr;
        ASTNode* noWhile = new ASTNode("while", "", cmdToken.linha);
        noWhile->filhos.push_back(cond);
        noWhile->filhos.push_back(noCmd);
        return noWhile;
    }
    if (lex == "goto") {
        pos++;
        Token label = expect("Numero", true);
        if (label.lexema.empty()) return syntaxError("esperado um numero de rotulo para o 'goto'");
        if (declaredLabels.find(label.lexema) == declaredLabels.end()) { return syntaxError("uso de 'goto' para rotulo nao declarado: '" + label.lexema + "'"); }
        return new ASTNode("goto", label.lexema, cmdToken.linha);
    }
    int backtrack_pos = pos;
    ASTNode* lhs = variavel();
    if (lhs) {
        if (atual().lexema == ":=") {
            pos++;
            ASTNode* rhs = expressao();
            if (!rhs) return syntaxError("expressao invalida apos ':='");
            ASTNode* assignNode = new ASTNode("atribuicao", "", lhs->linha);
            assignNode->filhos.push_back(lhs);
            assignNode->filhos.push_back(rhs);
            if (lhs->tipo == "identificador" && TabelaSimbolos[lhs->valor] == "funcao") { assignNode->tipo = "retorno_funcao"; }
            return assignNode;
        } else {
            pos = backtrack_pos;
            delete lhs;
            return chamada_subrotina();
        }
    }
    return nullptr;
}

ASTNode* variavel() {
    if (atual().tipo != "Identificador") { return nullptr; }
    Token idToken = tokens[pos];
    pos++;
    ASTNode* varNode = new ASTNode("identificador", idToken.lexema, idToken.linha);
    if (atual().lexema == "[") {
        pos++;
        ASTNode* indexExpr = expressao();
        if (!indexExpr) return syntaxError("expressao de indice de array invalida");
        if (expect("]").lexema.empty()) return syntaxError("esperado ']' para fechar o indice do array");
        ASTNode* accessNode = new ASTNode("acesso_array", idToken.lexema, idToken.linha);
        accessNode->filhos.push_back(indexExpr);
        delete varNode;
        return accessNode;
    }
    return varNode;
}

ASTNode* expressao() {
    ASTNode* noEsq = termo_logico();
    if (!noEsq) return nullptr;
    while (atual().lexema == "or") {
        Token opToken = atual();
        pos++;
        ASTNode* noDir = termo_logico();
        if (!noDir) return syntaxError("esperado expressao apos operador '" + opToken.lexema + "'");
        ASTNode* noExp = new ASTNode("operador_binario", opToken.lexema, opToken.linha);
        noExp->filhos.push_back(noEsq);
        noExp->filhos.push_back(noDir);
        noEsq = noExp;
    }
    return noEsq;
}

ASTNode* termo_logico() {
    ASTNode* noEsq = expressao_relacional();
    if (!noEsq) return nullptr;
    while (atual().lexema == "and") {
        Token opToken = atual();
        pos++;
        ASTNode* noDir = expressao_relacional();
        if (!noDir) return syntaxError("esperado expressao apos operador '" + opToken.lexema + "'");
        ASTNode* noExp = new ASTNode("operador_binario", opToken.lexema, opToken.linha);
        noExp->filhos.push_back(noEsq);
        noExp->filhos.push_back(noDir);
        noEsq = noExp;
    }
    return noEsq;
}

ASTNode* expressao_relacional() {
    ASTNode* noEsq = expressao_aritmetica();
    if (!noEsq) return nullptr;
    static const set<string> operadoresRelacionais = {"=", "<>", "<", "<=", ">", ">="};
    if (operadoresRelacionais.count(atual().lexema)) {
        Token opToken = atual();
        pos++;
        ASTNode* noDir = expressao_aritmetica();
        if (!noDir) return syntaxError("esperado expressao apos operador relacional '" + opToken.lexema + "'");
        ASTNode* noExp = new ASTNode("operador_binario", opToken.lexema, opToken.linha);
        noExp->filhos.push_back(noEsq);
        noExp->filhos.push_back(noDir);
        return noExp;
    }
    return noEsq;
}

ASTNode* expressao_aritmetica() {
    ASTNode* noEsq = nullptr;
    string op_unario = "";
    Token unarioToken = atual();
    if (atual().lexema == "+" || atual().lexema == "-") {
        op_unario = atual().lexema;
        pos++;
    }
    noEsq = termo_aritmetico();
    if (!noEsq) return nullptr;
    if (!op_unario.empty()) {
        ASTNode* noUnario = new ASTNode("operador_unario", op_unario, unarioToken.linha);
        noUnario->filhos.push_back(noEsq);
        noEsq = noUnario;
    }
    while (atual().lexema == "+" || atual().lexema == "-") {
        Token opToken = atual();
        pos++;
        ASTNode* noDir = termo_aritmetico();
        if (!noDir) return syntaxError("esperado termo apos operador '" + opToken.lexema + "'");
        ASTNode* noExp = new ASTNode("operador_binario", opToken.lexema, opToken.linha);
        noExp->filhos.push_back(noEsq);
        noExp->filhos.push_back(noDir);
        noEsq = noExp;
    }
    return noEsq;
}

ASTNode* termo_aritmetico() {
    ASTNode* noEsq = fator();
    if (!noEsq) return nullptr;
    while (atual().lexema == "*" || atual().lexema == "/" || atual().lexema == "div" || atual().lexema == "mod") {
        Token opToken = atual();
        pos++;
        ASTNode* noDir = fator();
        if (!noDir) return syntaxError("esperado fator apos operador '" + opToken.lexema + "'");
        ASTNode* noExp = new ASTNode("operador_binario", opToken.lexema, opToken.linha);
        noExp->filhos.push_back(noEsq);
        noExp->filhos.push_back(noDir);
        noEsq = noExp;
    }
    return noEsq;
}

ASTNode* fator() {
    Token fatorToken = atual();
    if (fatorToken.lexema == "not") {
        pos++;
        ASTNode* noNot = new ASTNode("operador_unario", "not", fatorToken.linha);
        ASTNode* noFator = fator();
        if (!noFator) return syntaxError("esperado uma expressao ou fator apos 'not'");
        noNot->filhos.push_back(noFator);
        return noNot;
    }
    if (fatorToken.tipo == "Numero") {
        pos++;
        return new ASTNode("numero", fatorToken.lexema, fatorToken.linha);
    }
    if (fatorToken.tipo == "Identificador") {
        if (static_cast<size_t>(pos) + 1 < tokens.size() && tokens[pos + 1].lexema == "(") {
            // REMOVIDO: A verificação semântica prematura que checava se o identificador era uma função.
            // string id = fatorToken.lexema;
            // if (TabelaSimbolos.count(id) && TabelaSimbolos[id] != "funcao" && id != "read" && id != "write") {
            //     return syntaxError("identificador '" + id + "' e um " + TabelaSimbolos[id] + ", nao uma funcao, e nao pode ser usado em uma expressao");
            // }
            return chamada_subrotina();
        } else {
            return variavel();
        }
    }
    if (expect("(").lexema.empty() == false) {
        ASTNode* noExp = expressao();
        if (!noExp) return nullptr;
        if (expect(")").lexema.empty()) { return syntaxError("esperado ')' para fechar a expressao entre parenteses"); }
        return noExp;
    }
    return syntaxError("fator invalido: esperado numero, identificador, 'not', ou expressao com '()'");
}

ASTNode* chamada_subrotina() {
    Token idToken = expect("Identificador", true);
    if(idToken.lexema.empty()) return nullptr;
    ASTNode* noCall = new ASTNode("chamada_subrotina", idToken.lexema, idToken.linha);
    if (atual().lexema == "(") {
        pos++;
        if (atual().lexema != ")") {
            ASTNode* noArgs = lista_argumentos();
            if (!noArgs) return nullptr;
            noCall->filhos.push_back(noArgs);
        }
        if (expect(")").lexema.empty()) { return syntaxError("esperado ')' para fechar a lista de argumentos da chamada de '" + idToken.lexema + "'"); }
    }
    return noCall;
}

ASTNode* lista_argumentos() {
    ASTNode* noLista = new ASTNode("lista_argumentos", "", atual().linha);
    do {
        ASTNode* noExpr = expressao();
        if (!noExpr) return syntaxError("argumento invalido na lista de argumentos");
        noLista->filhos.push_back(noExpr);
        if (atual().lexema != ",") break;
        pos++;
    } while (true);
    return noLista;
}

/**
 * @brief Serializa a AST para um arquivo de saida.
 * Formato: profundidade tipo "valor" linha
 * Onde 'valor' é "_" se estiver vazio.
 * @param out O stream do arquivo de saida.
 * @param node O nó atual a ser serializado.
 * @param nivel A profundidade atual do nó na árvore.
 */
void serializarAST(ofstream& out, ASTNode* node, int nivel = 0) {
    if (!node) return;
    
    // Escreve a profundidade, tipo, valor (entre aspas) e linha do nó.
    out << nivel << " " << node->tipo << " \"" << (node->valor.empty() ? "_" : node->valor) << "\" " << node->linha << "\n";
    
    // Chama recursivamente para cada filho, incrementando o nível.
    for (ASTNode* filho : node->filhos) {
        serializarAST(out, filho, nivel + 1);
    }
}


int main() {
    // ATENÇÃO: Altere o caminho do arquivo para o local correto no seu sistema.
    ifstream arquivo("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\1Bim\\saida.txt");
    if (!arquivo.is_open()) {
        cout << "Erro ao abrir o arquivo 'saida.txt'. Certifique-se de que o caminho esta correto.\n";
        return 1;
    }

    string linha;
    int linhaDoCodigoFonte = 1; // Usado para atribuir linha aos tokens
    
    // Pula o cabeçalho do arquivo de tokens.
    if (getline(arquivo, linha) && getline(arquivo, linha)) {
        // Cabeçalho pulado com sucesso.
    } else {
        cout << "Arquivo de tokens 'saida.txt' parece estar vazio ou com formato de cabecalho invalido.\n";
        arquivo.seekg(0); // Volta ao início para tentar ler mesmo assim.
    }

    while (getline(arquivo, linha)) {
        if (linha.find_first_not_of(" \t\r\n") == string::npos) {
            continue; // Pula linhas em branco
        }

        stringstream ss(linha);
        Token t;

        if (!(ss >> t.lexema)) continue;

        string tipoCompleto;
        getline(ss, tipoCompleto);

        // Limpa espaços em branco do tipo
        size_t first = tipoCompleto.find_first_not_of(" \t");
        if (string::npos != first) {
            size_t last = tipoCompleto.find_last_not_of(" \t");
            t.tipo = tipoCompleto.substr(first, (last - first + 1));
        } else {
            t.tipo = "";
        }
        
        // Reclassifica palavras-chave que podem ser usadas como identificadores
        if (t.lexema == "read" || t.lexema == "write") {
            t.tipo = "Identificador";
        }

        t.linha = linhaDoCodigoFonte++; // Atribui número da linha ao token
        tokens.push_back(t);
    }
    arquivo.close();

    if (tokens.empty()) {
        cout << "Nenhum token foi lido do arquivo. Verifique o conteudo e o formato de 'saida.txt'.\n";
        return 1;
    }

    ASTNode* raiz = nullptr;
    try {
        raiz = programa();
        if (raiz) {
            cout << "Analise sintatica concluida com sucesso.\n";
            
            // Abre o arquivo de saida para a AST.
            ofstream ast_file("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\2Bim\\ast.txt");
            if (!ast_file.is_open()) {
                cout << "Erro ao criar o arquivo de saida 'ast.txt'.\n";
                delete raiz;
                return 1;
            }

            // Serializa a AST para o arquivo.
            serializarAST(ast_file, raiz);
            ast_file.close();
            
            cout << "AST foi salva com sucesso no arquivo 'ast.txt'.\n";
            delete raiz;
        } else if (!fimTokens()) {
            syntaxError("Codigo fonte invalido ou o parser nao consumiu todos os tokens.");
        }
    } catch (...) {
        cout << "\nUma excecao nao tratada ocorreu durante a analise.\n";
        if (raiz) delete raiz;
    }
    
    return 0;
}