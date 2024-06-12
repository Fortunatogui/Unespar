import java.util.*;

public class AFD {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); //objeto Scanner para entrada de dados

        System.out.println("AUTOMATO FINITO DETERMINISTICO\n"); //mensagem de inicio do programa

        System.out.print("\nDigite o numero de estados: "); //solicita o numero de estados do automato
        int numEstados = Integer.parseInt(scanner.nextLine()); //le o numero de estados

        System.out.print("\nDigite o alfabeto, separados por espacos: "); //solicita os simbolos do alfabeto
        String[] sigma = scanner.nextLine().split(" "); //le e divide os simbolos do alfabeto

        System.out.print("\nDigite o estado inicial: "); //solicita o estado inicial do automato
        int estadoInicial = Integer.parseInt(scanner.nextLine()); //le o estado inicial

        System.out.print("\nDigite os estados finais (separe por espacos): "); //solicita os estados finais do automato
        Set<Integer> aceitacao = new HashSet<>(); //conjunto vazio de elementos do tipo inteiro  que não permite elementos duplicados armazenada na variavel aceitacao
        
        for (String estado : scanner.nextLine().split(" ")) { //le cada estado final separado por espacos
        
            aceitacao.add(Integer.parseInt(estado)); //adiciona os estados finais ao conjunto na variavel aceitacao
        
        }

        int[][] transicoes = new int[numEstados][sigma.length]; // cria matriz de transicoes
        
        for (int[] linha : transicoes) {
        
            Arrays.fill(linha, -1); //inicializa a matriz com transicoes indefinidas
        
        }

        System.out.println("\nTRANSICOES"); //mensagem da entrada das transicoes
        System.out.println("OBS: Caso nao exista uma transicao, insira -1"); //mensagem explicativa
        
        for (int estado = 0; estado < numEstados; estado++) { //loop para definir as transicoes
            
            System.out.println("\nEstado q" + estado + ", digite a transicao:"); //solicita as transicoes para cada estado
            
            for (int qntSimbolos = 0; qntSimbolos < sigma.length; qntSimbolos++) { //loop para cada simbolo do alfabeto
            
                System.out.print("Para simbolo '" + sigma[qntSimbolos] + "': "); //solicita a transicao para cada simbolo
                int novoEstado = Integer.parseInt(scanner.nextLine()); //le o novo estado
            
                transicoes[estado][qntSimbolos] = novoEstado; //define a transicao
            }
        }

        System.out.println("\nTABELA DE TRANSICOES:"); //mensagem indicando a exibicao da tabela de transicoes
       
        System.out.print("    "); //espacamento
       
        for (String simbolo : sigma) { //loop para imprimir os simbolos do alfabeto
       
            System.out.print(simbolo + " "); //imprime os simbolos
       
       
        }
       
        System.out.println(); //pular linha
       
        for (int estado = 0; estado < numEstados; estado++) { //loop para imprimir as transicoes
       
            System.out.print("q" + estado + " "); //imprime o estado atual
       
            for (int qntSimbolos = 0; qntSimbolos < sigma.length; qntSimbolos++) { //loop para imprimir as transicoes
       
                int proximoEstado = transicoes[estado][qntSimbolos]; //variavel auxiliar pra mostra tabela
       
                if (proximoEstado == -1) { //verifica se a transicao e indefinida
       
                    System.out.print("x "); //imprime 'x'
       
                } else {
       
                    System.out.print("q" + proximoEstado + " "); //imprime o proximo estado
       
                }
            }
       
            System.out.println(); //quebra de linha
        }

        while (true) { //loop infinito para testar cadeias
          
            System.out.print("\nInforme uma cadeia (Digite 'sair' para fechar o programa): "); //solicita uma cadeia
            String cadeia = scanner.nextLine(); //le a cadeia
          
            if (cadeia.equalsIgnoreCase("sair")) { //verifica se o usuario deseja sair
          
                break; //encerra o loop se o usuario digitar 'sair'
          
            }

            int estadoAtual = estadoInicial; // define o estado atual como o estado inicial
            boolean naoAceita = false; // flag para verificar se a cadeia e rejeitada

            for (int posicao = 0; posicao < cadeia.length(); posicao++) { //loop para percorrer a cadeia atras de um simbolo que nao foi definido
            
                char simbolo = cadeia.charAt(posicao); //obtem o simbolo da cadeia
                int qntSimbolos = Arrays.asList(sigma).indexOf(String.valueOf(simbolo)); //converte o array sigma em uma lista e dps retorna o índice do elemento na lista, ou -1 se o símbolo não for encontrado.

                if (qntSimbolos == -1) { //verifica se o simbolo nao esta no alfabeto
            
                    System.out.println("\nCadeia contem simbolo invalido no alfabeto."); //mensagem de erro caso nao esteja
                    naoAceita = true; //define a flag de rejeicao como true
            
                    break; //sai do loop
            
                }

                int proximoEstado = transicoes[estadoAtual][qntSimbolos]; //usa a matriz de transições para determinar o próximo estado baseado no estado atual e no símbolo atual.
              
                if (proximoEstado == -1) { //verifica se a transicao e indefinida de acordo com a tabela 
              
                    System.out.println("Transicao indefinida para simbolo '" + simbolo + "' a partir do estado q" + estadoAtual); //mensagem de erro
                    naoAceita = true; //define a flag de rejeicao como true
              
                    break; //sai do loop
              
                }

                estadoAtual = proximoEstado; //atualiza o estado atual
            }

            if (naoAceita || !aceitacao.contains(estadoAtual)) { //verifica se 'naoAceita' e true e se o estado atual não está presente no conjunto de estados finais. 
          
                System.out.println("Nao aceita."); //imprime que a cadeia nao e aceita
          
            } else {
          
                System.out.println("Aceita."); //imprime que a cadeia e aceita
          
            }
        }

        scanner.close(); //fecha o Scanner
    }
}
