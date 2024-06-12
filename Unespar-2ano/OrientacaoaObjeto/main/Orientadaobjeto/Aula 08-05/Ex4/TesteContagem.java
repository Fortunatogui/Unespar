public class TesteContagem {
    public static void main(String[] args) {

        Contagem contagem = new Contagem();
            
        //Metodo 1
        System.out.println("Contar até 10:");
        contagem.contar();
        System.out.println();
            
        //Metodo 2
        System.out.println("Contar até o fim:");
        contagem.contar(15);
        System.out.println();
            
        //Metodo 3
        System.out.println("Contar de inicio ao fim:");
        contagem.contar(5, 19);
        System.out.println();
            
        //Metodo 4
        System.out.println("Contar de inicio ao fim, pausadamente:");
        contagem.contar(1, 9, 1);

        }
    }