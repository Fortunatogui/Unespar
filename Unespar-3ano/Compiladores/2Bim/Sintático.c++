#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <regex>

using namespace std;

// Estrutura para representar um token
struct Token {
    string lexema;
    string tipo;  // Ex: "Palavra reservada", "Identificador", etc.
};

// Lista de tokens e posição atual
vector<Token> tokens;
int pos = 0;

// Retorna o token atual ou um token vazio se acabarem os tokens
Token atual() {
    if (pos < tokens.size()) return tokens[pos];
    return {"", "", -1};
}

// Avança se o token atual bate com o esperado (por lexema ou tipo)
bool aceitar(string esperado) {
    if (atual().lexema == esperado || atual().tipo == esperado) {
        pos++;
        return true;
    }
    return false;
}

bool fimTokens() {
    return pos >= tokens.size();
}
int main() {
    ifstream arquivo("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\1Bim\\saida.txt");
    if (!arquivo.is_open()) {
        cout << "Erro ao abrir o arquivo." << endl;
        return 1;
    }

    string linha;
    getline(arquivo, linha); // Pula cabeçalho "Lexema Tipo Linha"
    getline(arquivo, linha); // Pula linha de separação

    // Expressão regular para extrair lexema e tipo (sem coluna de linha)
    regex padrao(R"(^\s*(\S+)\s+(.+?)\s*$)");
    smatch match;

    int linhaContador = 1;

    while (getline(arquivo, linha)) {
        if (regex_match(linha, match, padrao)) {
            string lexema = match[1];
            string tipo = match[2];

            tokens.push_back({lexema, tipo, linhaContador});
        }

        // Avança linha de código-fonte mesmo que não haja token
        linhaContador++;
    }

    arquivo.close();

    if (programa()) {
        cout << "Análise sintática concluída com sucesso!" << endl;
    } else {
        cout << "Erro na análise sintática." << endl;
    }

    return 0;
}
int main() {
    ifstream arquivo("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\1Bim\\saida.txt");
    if (!arquivo.is_open()) {
        cout << "Erro ao abrir o arquivo." << endl;
        return 1;
    }

    string linha;
    getline(arquivo, linha); // Pula cabeçalho "Lexema Tipo Linha"
    getline(arquivo, linha); // Pula linha de separação

    // Expressão regular para extrair lexema e tipo (sem coluna de linha)
    regex padrao(R"(^\s*(\S+)\s+(.+?)\s*$)");
    smatch match;

    int linhaContador = 1;

    while (getline(arquivo, linha)) {
        if (regex_match(linha, match, padrao)) {
            string lexema = match[1];
            string tipo = match[2];

            tokens.push_back({lexema, tipo, linhaContador});
        }

        // Avança linha de código-fonte mesmo que não haja token
        linhaContador++;
    }

    arquivo.close();

    if (programa()) {
        cout << "Análise sintática concluída com sucesso!" << endl;
    } else {
        cout << "Erro na análise sintática." << endl;
    }

    return 0;
}int main() {
    ifstream arquivo("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\1Bim\\saida.txt");
    if (!arquivo.is_open()) {
        cout << "Erro ao abrir o arquivo." << endl;
        return 1;
    }

    string linha;
    getline(arquivo, linha); // Pula cabeçalho "Lexema Tipo Linha"
    getline(arquivo, linha); // Pula linha de separação

    // Expressão regular para extrair lexema e tipo (sem coluna de linha)
    regex padrao(R"(^\s*(\S+)\s+(.+?)\s*$)");
    smatch match;

    int linhaContador = 1;

    while (getline(arquivo, linha)) {
        if (regex_match(linha, match, padrao)) {
            string lexema = match[1];
            string tipo = match[2];

            tokens.push_back({lexema, tipo, linhaContador});
        }

        // Avança linha de código-fonte mesmo que não haja token
        linhaContador++;
    }

    arquivo.close();

    if (programa()) {
        cout << "Análise sintática concluída com sucesso!" << endl;
    } else {
        cout << "Erro na análise sintática." << endl;
    }

    return 0;
}int main() {
    ifstream arquivo("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\1Bim\\saida.txt");
    if (!arquivo.is_open()) {
        cout << "Erro ao abrir o arquivo." << endl;
        return 1;
    }

    string linha;
    getline(arquivo, linha); // Pula cabeçalho "Lexema Tipo Linha"
    getline(arquivo, linha); // Pula linha de separação

    // Expressão regular para extrair lexema e tipo (sem coluna de linha)
    regex padrao(R"(^\s*(\S+)\s+(.+?)\s*$)");
    smatch match;

    int linhaContador = 1;

    while (getline(arquivo, linha)) {
        if (regex_match(linha, match, padrao)) {
            string lexema = match[1];
            string tipo = match[2];

            tokens.push_back({lexema, tipo, linhaContador});
        }

        // Avança linha de código-fonte mesmo que não haja token
        linhaContador++;
    }

    arquivo.close();

    if (programa()) {
        cout << "Análise sintática concluída com sucesso!" << endl;
    } else {
        cout << "Erro na análise sintática." << endl;
    }

    return 0;
}
bool expressao() {
    if (atual().tipo == "Identificador" || atual().tipo == "Numero") {
        pos++;
        return true;
    }
    return false;
}

bool atribuicao() {
    if (atual().tipo != "Identificador") return false;

    pos++; // consome identificador

    if (!aceitar(":=")) {
        cout << "Erro na linha " << atual().linha << ": esperado ':=' após identificador" << endl;
        return false;
    }

    if (!expressao()) {
        cout << "Erro na linha " << atual().linha << ": expressão inválida após ':='" << endl;
        return false;
    }

    return true;
}

bool comando() {
    return atribuicao();
}

bool lista_comandos() {
    while (comando()) {
        // Aceita vários comandos (um após o outro)
    }
    return true;
}

bool bloco() {
    if (!aceitar("begin")) {
        cout << "Erro na linha " << atual().linha << ": esperado 'begin'" << endl;
        return false;
    }

    if (!lista_comandos()) {
        cout << "Erro na linha " << atual().linha << ": lista de comandos inválida" << endl;
        return false;
    }

    if (!aceitar("end")) {
        cout << "Erro na linha " << atual().linha << ": esperado 'end'" << endl;
        return false;
    }

    return true;
}

bool programa() {
    if (!aceitar("Program")) {
        cout << "Erro na linha " << atual().linha << ": esperado 'Program'" << endl;
        return false;
    }

    if (atual().tipo != "Identificador") {
        cout << "Erro na linha " << atual().linha << ": esperado identificador após 'Program'" << endl;
        return false;
    }
    pos++; // consome identificador

    if (!bloco()) return false;

    if (!fimTokens()) {
        cout << "Erro na linha " << atual().linha << ": tokens inesperados após 'end'" << endl;
        return false;
    }

    return true;
}

// ---------- Função principal ----------

int main() {
    ifstream arquivo("C:\\Users\\fortu\\Downloads\\Unespar\\Unespar\\Unespar-3ano\\Compiladores\\1Bim\\saida.txt");
    if (!arquivo.is_open()) {
        cout << "Erro ao abrir o arquivo." << endl;
        return 1;
    }

    string linha;
    getline(arquivo, linha); // Pula cabeçalho "Lexema Tipo Linha"
    getline(arquivo, linha); // Pula linha de separação

    // Expressão regular para extrair lexema e tipo (sem coluna de linha)
    regex padrao(R"(^\s*(\S+)\s+(.+?)\s*$)");
    smatch match;

    int linhaContador = 1;

    while (getline(arquivo, linha)) {
        if (regex_match(linha, match, padrao)) {
            string lexema = match[1];
            string tipo = match[2];

            tokens.push_back({lexema, tipo, linhaContador});
        }

        // Avança linha de código-fonte mesmo que não haja token
        linhaContador++;
    }

    arquivo.close();

    if (programa()) {
        cout << "Análise sintática concluída com sucesso!" << endl;
    } else {
        cout << "Erro na análise sintática." << endl;
    }

    return 0;
}