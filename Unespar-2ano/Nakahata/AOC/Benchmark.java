import java.util.Arrays;//Biblioteca para usar vetores
import java.util.Random;//Biblioteca para variáveis aleatórias

public class Benchmark {//Classe

    private static int[][] criarMatriz(int tamanho) {//metódo para criar a matrix de x posições
        int[][] matriz = new int[tamanho][tamanho];//define o tamanho da matriz  
        return matriz;//retorna uma matriz com x posições de valor inteiro
    }


    private static void sequencial(int[][] matriz, int tamanho) { //metódo pra preencher   de forma sequencial
        int valor = 0;//inicializa o valor como 0 pra ir até a posição x da matriz
        for (int i = 0; i < tamanho; i++) {//define o número de linhas da matriz
            for (int j = 0; j < tamanho; j++) {//define o número de colunas da matriz
                matriz[i][j] = valor++;//preenche a matriz de 1 em 1 
            }
        }
    }

    private static void aleatoria(int[][] matriz, int tamanho) {//metódo para preencher de forma aleatória
       
        int valor = 0;//inicializa o valor como 0 pra ir até a posição x da matriz
        int tamanhoMatriz = tamanho * tamanho;//define o tamanho da matriz
        boolean[] posicoesUsadas = new boolean[tamanhoMatriz];// cada elemento do array corresponde a uma posição na matriz, e seu valor booleano indica se essa posição já foi usada

        
        
        Random random = new Random(); // incrementa a seed de aleatoriedade
        random.setSeed(System.currentTimeMillis() + 1);  // incrementa a seed em 1 a cada execução

        for (int i = 0; i < tamanhoMatriz; i++) {//preenchendo posições aleatórias
            int posicao;// encontra uma posição não utilizada para inserir um valor
            do {
                posicao = random.nextInt(tamanhoMatriz);//gera um número aleatório que pode ser usado como índice
            } while (posicoesUsadas[posicao]);//Enquanto a posição escolhida já estiver sendo usada((posicoesUsadas[posicao]) for 'true'), posição continuará sendo aleatório

            int linha = posicao / tamanho; //representa o número da linha na matriz onde o próximo valor aleatório será inserido.
            int coluna = posicao % tamanho;//representa o número da coluna na matriz onde o próximo valor aleatório será inserido.
            
            matriz[linha][coluna] = valor++;//preeche a posiçao aleatória com um número de 1 a 40.000
            posicoesUsadas[posicao] = true;//muda o valor de posição pra true impossibilitando ser preenchida novamente
        }
    }

    private static double medirTempo(Runnable func, int[][] matriz, int tamanho) {//metódo para medir o tempo de cada operação
        long inicio = System.currentTimeMillis();//cria uma variável que armazena o tempo de início da operação em milisegundos
        func.run();//executa a operação que foi passada como parâmetro para o método 
        long fim = System.currentTimeMillis();//cria uma variável que armazena o tempo final da operação em milisegundos
        return (fim - inicio);//acha o tempo em milisegundos que a operação terminou e retorna o valor como double
    }

    private static void liberarMatriz(int[][] matriz) {// metódo para limpar todos os elementos da matriz
        for (int i = 0; i < matriz.length; i++) {//determina o número de linhas
            for (int j = 0; j < matriz[i].length; j++) {//determina o número de elementos na linha
                matriz[i][j] = 0; //define todos os elementos da matriz como 0.
            }
        }
        matriz = null; //libera a memória associada à matriz
    }

    public static void main(String[] args) {//metódo principal que realiza as operações e mostra os respectivos resultados
        int tamanho = 200;  // Tamanho da matriz (200x200)
    
        int[][] matriz = criarMatriz(tamanho);//chama o metódo que cria a matriz

        double tempoSequencial = medirTempo(() -> sequencial(matriz, tamanho), matriz, tamanho);//chama o método sequencial que preenche a matriz de forma sequencial, e mede o tempo necessário para preencher a matriz de forma sequencial armazenando esse tempo na variável tempoSequencial
        System.out.printf("Tempo para preencher a matriz sequencialmente: %f milisegundos\n", (tempoSequencial));//imprime o tempo que levará para preencher a matriz sequencialmente
        double memoriaSequencial = (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);  //calcula a quantidade de memória alocada pelo programa após o preenchimento sequencial da matriz e a armazena na variável memoriaSequencial
        System.out.printf("Memória alocada para preenchimento sequencial: %f MB\n", memoriaSequencial);//imprime a quantidade de memória alocada para o preenchimento sequencial da matriz

        double tempoAleatorio = medirTempo(() -> aleatoria(matriz, tamanho), matriz, tamanho);//chama o método aleatória que preenche a matriz de forma aleatória, e mede o tempo necessário para preencher a matriz de forma aleatória armazenando esse tempo na variável tempoAleatorio
        System.out.printf("Tempo para preencher a matriz aleatoriamente: %f milisegundos\n", (tempoAleatorio));//imprime o tempo que levará para preencher a matriz aleatoriamente
        double memoriaAleatoria = (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);//calcula a quantidade de memória alocada pelo programa após o preenchimento aleatório da matriz e a armazena na variável memoriaAleatoria
        System.out.printf("Memória alocada para preenchimento aleatório: %f MB\n", memoriaAleatoria);//imprime a quantidade de memória alocada para o preenchimento aleatório da matriz

        liberarMatriz(matriz);//libera a matriz
    }
}
