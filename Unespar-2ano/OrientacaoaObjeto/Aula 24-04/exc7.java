import java.util.Scanner;

import javax.swing.JOptionPane;

public class exc7 {
    public static void main(String[] args) {
        while (true) {
            
            String[] options = { "Exercício 1", "Exercício 2", "Exercício 3", "Exercico 4", "Exercico 5", "Exercico 6", "Sair" };
            int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu Principal", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    exc1();
                    break;
                case 1:
                    exc2();
                    break;
                case 2:
                    exc3();
                    break;
                case 3:
                    exc4();
                    break;
                case 4:
                    exc5();
                    break;
               // case 5:
                  //  exc6();
                   // break;
                case 6:
                    System.exit(0); 
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    public static void exc1() {
        String nome = JOptionPane.showInputDialog("Insira o nome do Produto");
        String x = JOptionPane.showInputDialog("Insira o valor do Produto");

        if(x != null) {

            try {

                int valor = Integer.parseInt(x);

                if(valor >= 50 && valor < 200){

                    double desconto = (valor - (valor * 0.05));
                   JOptionPane.showMessageDialog(null, "O nome do produto era: \n" + nome + "\n O valor original era de: " + valor + "\n O valor com desconto aplicado foi de: " + desconto); 
                
                }else 

                if(valor >= 200 && valor < 500){

                    double desconto = (valor - (valor * 0.06));
                    JOptionPane.showMessageDialog(null, "O nome do produto era: " + nome + " O valor original era de" + valor + " O valor com desconto aplicado foi de: " + desconto); 

                }else 

                if(valor >= 500 && valor < 1000){

                    double desconto = (valor - (valor * 0.07));
                    JOptionPane.showMessageDialog(null, "O nome do produto era: " + nome + " O valor original era de" + valor + " O valor com desconto aplicado foi de: " + desconto); 

                }

                else {

                    double desconto = (valor - (valor * 0.08));
                    JOptionPane.showMessageDialog(null, "O nome do produto era: " + nome + " O valor original era de" + valor + " O valor com desconto aplicado foi de: " + desconto); 

                }

            } catch (NumberFormatException erro) {

                JOptionPane.showMessageDialog(null, "Digite apenas valores inteiros" + erro);
                
            }
        } else {

            JOptionPane.showMessageDialog(null, "Operacao Cancelada");

        }

    }

    public static void exc2() {
        double[] resistencia = new double[4];
        double RE = 0;
        double maior;
        double menor;

        for(int i = 0; i < 4; i++){
            String r = JOptionPane.showInputDialog("Insira a resistencia: ");
            resistencia[i] = Double.parseDouble(r);
            RE += resistencia[i];
        }

        maior = resistencia[0];
        menor = resistencia[0];

        for(int i = 1; i < 4; i++){
            if(maior < resistencia[i]){
                maior = resistencia[i];
            }
            if(menor > resistencia[i]){
                menor = resistencia[i];
            }
        }

        JOptionPane.showMessageDialog(null, "Resistencia fornecida: " + resistencia[0] + ", "+ resistencia[1] + ", " + resistencia[2] + ", " + resistencia[3] + "\nA maior resistencia e: " + maior + "\nA menor resistencia e: " + menor + "\nResistencia Equivalente: " + RE);
    }

    public static void exc3() {
        String loginOriginal = "java8";
        String senhaOriginal = "java8";
        int tentativas = 3;

        Scanner scanner = new Scanner(System.in);

        while (tentativas > 0) {
            System.out.println("Digite seu login:");
            String login = scanner.nextLine();

            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();

            if (login.equals(loginOriginal) && senha.equals(senhaOriginal)) {
                System.out.println("Acesso liberado!");
                break;
            } else {
                tentativas--;
                System.out.println("Dados incorretos. Você tem mais " + tentativas + " tentativas.");
            }
        }

        if (tentativas == 0) {
            System.out.println("Tentativas esgotadas. Acesso negado.");
        }
        scanner.close();
    }

    public static void exc4() {
        String valor = JOptionPane.showInputDialog("Forneca um numero");
        int valor1 = Integer.parseInt(valor);

        for(int i = 0; i <= 10; i++){
            int tabuada = valor1 * i;
            JOptionPane.showMessageDialog(null, "4 * " + i + " = " + tabuada); 
        }
    }

    public static void exc5() {
        String frase = JOptionPane.showInputDialog("Digite uma frase:");

        String fraseInvertida = "";
        for (int i = frase.length() - 1; i >= 0; i--) {
            fraseInvertida += frase.charAt(i);
        }

        JOptionPane.showMessageDialog(null, "Frase invertida: " + fraseInvertida);
    }

    /*public static void exc6() {
        //Não consegui fazer o exercicio executar aqui.
        private static boolean palavraImpropria(String frase) {
            frase = frase.toLowerCase(); 
            return frase.contains("sexo") || frase.contains("sexual"); 
        }
        
        public static void main(String[] args) {
            String frase = JOptionPane.showInputDialog("Forneça uma frase:");
        
            if (palavraImpropria(frase)) {
                JOptionPane.showMessageDialog(null, "Conteúdo impróprio"); 
            } else {
                JOptionPane.showMessageDialog(null, "Conteúdo liberado"); 
            }
        }
        
    } */
}