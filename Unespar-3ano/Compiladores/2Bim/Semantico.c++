// Nome do arquivo: Semantico.cpp
// Analisador semântico que lê uma AST serializada do arquivo "ast.txt",
// reconstrói a árvore e realiza as checagens de escopo e tipo.
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <map>
#include <stack>

using namespace std;

// A estrutura do ASTNode DEVE SER IDÊNTICA à do analisador sintático.
struct ASTNode {
    string tipo;
    string valor;
    vector<ASTNode*> filhos;
    int linha;

    ASTNode(string t, string v = "", int l = -1) : tipo(t), valor(v), linha(l) {}

    // Destrutor para liberar a memória da árvore reconstruída.
    ~ASTNode() {
        for (ASTNode* filho : filhos) {
            delete filho;
        }
    }
};

// --- ESTRUTURAS PARA ANÁLISE SEMÂNTICA ---

// Representa um símbolo (variável, função, tipo, etc.) na tabela.
struct Symbol {
    string nome;
    string tipo; // ex: "integer", "boolean", "array", "funcao"
    string categoria; // ex: "variavel", "procedimento"
    // Poderia ser estendido para incluir parâmetros de função, tipo de array, etc.
};

// Gerencia as tabelas de símbolos encadeadas para diferentes escopos.
class SymbolTable {
private:
    // Uma pilha de tabelas. Cada tabela (map) representa um escopo.
    stack<map<string, Symbol>> scopes;

public:
    SymbolTable() {
        // Inicia com o escopo global.
        enterScope();
    }

    // Entra em um novo escopo (ex: início de um bloco ou função).
    void enterScope() {
        scopes.push(map<string, Symbol>());
    }

    // Sai do escopo atual (ex: fim de um bloco ou função).
    void exitScope() {
        if (!scopes.empty()) {
            scopes.pop();
        }
    }

    // Adiciona um símbolo ao escopo atual. Retorna 'false' se já existir.
    bool addSymbol(const Symbol& sym) {
        if (scopes.top().count(sym.nome)) {
            // Erro: Declaração duplicada no mesmo escopo.
            return false;
        }
        scopes.top()[sym.nome] = sym;
        return true;
    }

    // Procura por um símbolo do escopo atual para o mais externo (global).
    Symbol* findSymbol(const string& nome) {
        // Cria uma cópia da pilha para não alterar a original durante a busca.
        stack<map<string, Symbol>> temp_scopes = scopes;
        while (!temp_scopes.empty()) {
            map<string, Symbol>& currentScope = temp_scopes.top();
            if (currentScope.count(nome)) {
                // Para evitar retornar um ponteiro para um dado temporário,
                // vamos buscar na pilha original e retornar o ponteiro de lá.
                return &scopes.top().at(nome);
            }
            temp_scopes.pop();
        }
        return nullptr; // Símbolo não encontrado.
    }
};


// --- CLASSE DO ANALISADOR SEMÂNTICO ---

class AnalisadorSemantico {
private:
    SymbolTable tabelaSimbolos;

    // Função principal que percorre a árvore (Visitor Pattern).
    void visitar(ASTNode* no);

    // Funções específicas para cada tipo de nó da AST.
    void visitarDeclaracaoVariaveis(ASTNode* no);
    void visitarAtribuicao(ASTNode* no);
    void visitarIf(ASTNode* no);
    void visitarWhile(ASTNode* no);
    void visitarChamadaSubrotina(ASTNode* no);

    // Função para determinar o tipo de uma expressão.
    string deduzirTipoExpressao(ASTNode* no);

public:
    // Ponto de entrada público para iniciar a análise.
    void analisar(ASTNode* raiz);
};

void AnalisadorSemantico::analisar(ASTNode* raiz) {
    if (!raiz) return;
    cout << "\n--- Iniciando Analise Semantica ---\n";
    visitar(raiz);
    cout << "--- Analise semantica concluida, seu codigo foi compilado ---\n";
}

// O "dispatcher" que chama a função correta baseada no tipo do nó.
void AnalisadorSemantico::visitar(ASTNode* no) {
    if (!no) return;

    // Ações pré-ordem (antes de visitar os filhos).
    if (no->tipo == "bloco")          tabelaSimbolos.enterScope();
    if (no->tipo == "declaracao_variavel") visitarDeclaracaoVariaveis(no);
    if (no->tipo == "atribuicao")     visitarAtribuicao(no);
    if (no->tipo == "if")             visitarIf(no);
    if (no->tipo == "while")          visitarWhile(no);
    if (no->tipo == "chamada_subrotina") visitarChamadaSubrotina(no);
    
    // Visita recursivamente os filhos.
    for (ASTNode* filho : no->filhos) {
        visitar(filho);
    }

    // Ações pós-ordem (depois de visitar os filhos).
    if (no->tipo == "bloco")          tabelaSimbolos.exitScope();
}

void AnalisadorSemantico::visitarDeclaracaoVariaveis(ASTNode* no) {
    ASTNode* listaIds = no->filhos[0];
    ASTNode* tipoNode = no->filhos[1];
    string tipo = tipoNode->valor; // "integer", "boolean", etc.

    for (ASTNode* idNode : listaIds->filhos) {
        Symbol sym = {idNode->valor, tipo, "variavel"};
        if (!tabelaSimbolos.addSymbol(sym)) {
            cout << "Erro Semantico na linha " << idNode->linha << ": Variavel '" << idNode->valor << "' ja foi declarada neste escopo." << endl;
        }
    }
}

string AnalisadorSemantico::deduzirTipoExpressao(ASTNode* no) {
    if (no->tipo == "numero") return "integer"; // Simplificação: todos os números são inteiros.
    if (no->tipo == "identificador") {
        Symbol* sym = tabelaSimbolos.findSymbol(no->valor);
        if (!sym) {
            cout << "Erro Semantico na linha " << no->linha << ": Variavel '" << no->valor << "' nao foi declarada." << endl;
            return "tipo_erro";
        }
        return sym->tipo;
    }
    if (no->tipo == "operador_binario") {
        string tipoEsq = deduzirTipoExpressao(no->filhos[0]);
        string tipoDir = deduzirTipoExpressao(no->filhos[1]);

        if (tipoEsq == "tipo_erro" || tipoDir == "tipo_erro") return "tipo_erro";

        string op = no->valor;
        if (op == "or" || op == "and") {
            if (tipoEsq != "boolean" || tipoDir != "boolean") {
                cout << "Erro Semantico na linha " << no->linha << ": Operador '" << op << "' exige operandos do tipo 'boolean', mas recebeu '" << tipoEsq << "' e '" << tipoDir << "'." << endl;
                return "tipo_erro";
            }
            return "boolean";
        }
        if (op == "=" || op == "<>" || op == "<" || op == "<=" || op == ">" || op == ">=") {
            if (tipoEsq != tipoDir) {
                 cout << "Aviso na linha " << no->linha << ": Comparando tipos diferentes ('" << tipoEsq << "' e '" << tipoDir << "')." << endl;
            }
            return "boolean";
        }
        if (op == "+" || op == "-" || op == "*" || op == "/" || op == "div" || op == "mod") {
            if (tipoEsq != "integer" || tipoDir != "integer") { // Simplificação: apenas int/int
                cout << "Erro Semantico na linha " << no->linha << ": Operador '" << op << "' exige operandos numericos, mas recebeu '" << tipoEsq << "' e '" << tipoDir << "'." << endl;
                return "tipo_erro";
            }
            return "integer";
        }
    }
    return "tipo_desconhecido";
}

void AnalisadorSemantico::visitarAtribuicao(ASTNode* no) {
    ASTNode* varNode = no->filhos[0];
    ASTNode* exprNode = no->filhos[1];

    Symbol* sym = tabelaSimbolos.findSymbol(varNode->valor);
    if (!sym) {
        cout << "Erro Semantico na linha " << varNode->linha << ": Variavel de atribuicao '" << varNode->valor << "' nao foi declarada." << endl;
        return;
    }

    string tipoVariavel = sym->tipo;
    string tipoExpressao = deduzirTipoExpressao(exprNode);

    if (tipoExpressao != "tipo_erro" && tipoVariavel != tipoExpressao) {
        cout << "Erro Semantico na linha " << varNode->linha << ": Incompatibilidade de tipos. Nao e possivel atribuir uma expressao do tipo '" << tipoExpressao << "' a uma variavel do tipo '" << tipoVariavel << "'." << endl;
    }
}

void AnalisadorSemantico::visitarIf(ASTNode* no) {
    ASTNode* condNode = no->filhos[0];
    string tipoCondicao = deduzirTipoExpressao(condNode);
    if (tipoCondicao != "boolean" && tipoCondicao != "tipo_erro") {
        cout << "Erro Semantico na linha " << condNode->linha << ": A expressao da condicao 'if' deve ser 'boolean', mas e '" << tipoCondicao << "'." << endl;
    }
}

void AnalisadorSemantico::visitarWhile(ASTNode* no) {
    ASTNode* condNode = no->filhos[0];
    string tipoCondicao = deduzirTipoExpressao(condNode);
    if (tipoCondicao != "boolean" && tipoCondicao != "tipo_erro") {
        cout << "Erro Semantico na linha " << condNode->linha << ": A expressao da condicao 'while' deve ser 'boolean', mas e '" << tipoCondicao << "'." << endl;
    }
}

void AnalisadorSemantico::visitarChamadaSubrotina(ASTNode* no){
    // Simplificação: apenas verifica se o identificador existe.
    // Uma implementação completa verificaria se é um procedimento ou função
    // e analisaria os argumentos.
    Symbol* sym = tabelaSimbolos.findSymbol(no->valor);
    if (!sym && no->valor != "read" && no->valor != "write") {
         cout << "Erro Semantico na linha " << no->linha << ": Funcao ou procedimento '" << no->valor << "' nao foi declarado." << endl;
    }
}

// --- FUNÇÕES DE RECONSTRUÇÃO DA AST ---

/**
 * @brief Imprime a AST de forma hierárquica (útil para depuração).
 */
void imprimirAST(ASTNode* node, int nivel = 0) {
    if (!node) return;
    for (int i = 0; i < nivel; ++i) cout << "   ";
    cout << "+--" << node->tipo;
    if (!node->valor.empty()) cout << " (" << node->valor << ")";
    cout << " [L" << node->linha << "]" << "\n";
    for (ASTNode* filho : node->filhos) {
        imprimirAST(filho, nivel + 1);
    }
}

/**
 * @brief Reconstrói a AST a partir de um arquivo de texto.
 * @param nomeArquivo O caminho para o arquivo com a AST serializada.
 * @return ASTNode* Um ponteiro para a raiz da árvore reconstruída, ou nullptr se falhar.
 */
ASTNode* reconstruirAST(const string& nomeArquivo) {
    ifstream arquivo(nomeArquivo);
    if (!arquivo.is_open()) {
        cout << "Erro: Nao foi possivel abrir o arquivo '" << nomeArquivo << "' para ler a AST.\n";
        return nullptr;
    }

    string linha;
    vector<ASTNode*> pilha_pais;
    ASTNode* raiz = nullptr;

    while (getline(arquivo, linha)) {
        stringstream ss(linha);
        int profundidade, linha_no;
        string tipo, valor;
        char aspa;

        if (!(ss >> profundidade >> tipo)) continue;

        // Lê o valor que está entre aspas
        ss >> ws >> aspa; // Pula espaço e lê a primeira aspa
        getline(ss, valor, '"'); // Lê o conteúdo até a próxima aspa
        
        if (!(ss >> linha_no)) linha_no = -1; // Lê o número da linha

        if (valor == "_") {
            valor = "";
        }

        ASTNode* novoNo = new ASTNode(tipo, valor, linha_no);

        if (profundidade == 0) {
            raiz = novoNo;
            pilha_pais.assign(1, raiz);
        } else {
            while (pilha_pais.size() > (size_t)profundidade) {
                pilha_pais.pop_back();
            }
            if (pilha_pais.empty() || pilha_pais.size() != (size_t)profundidade) {
                cout << "Erro de formato no arquivo AST: profundidade invalida na linha: " << linha << endl;
                delete novoNo;
                delete raiz;
                return nullptr;
            }
            pilha_pais.back()->filhos.push_back(novoNo);
            pilha_pais.push_back(novoNo);
        }
    }

    arquivo.close();
    return raiz;
}


int main() {

    ASTNode* raiz = reconstruirAST("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\2Bim\\ast.txt");

    if (raiz) {
        cout << "AST reconstruida com sucesso a partir de 'ast.txt'.\n";
        
        // cout << "\n--- AST Reconstruida ---\n";
        // imprimirAST(raiz);
        
        // Inicia a fase de análise semântica.
        AnalisadorSemantico analisador;
        analisador.analisar(raiz);

        // Libera a memória alocada para a árvore.
        delete raiz;
    } else {
        cout << "Falha ao reconstruir a AST. O programa sera encerrado.\n";
        return 1;
    }

    return 0;
}