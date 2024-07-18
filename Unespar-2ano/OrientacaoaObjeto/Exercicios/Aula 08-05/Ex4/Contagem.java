public class Contagem {

     
    private void pausarPorSegundo(int segundos) {
        long inicio = System.nanoTime();
        long pausa = segundos * 1_000_000_000L;
    
         while (System.nanoTime() - inicio < pausa);
    }

    // Método 1: Contagem de 1 a 10
    public void contar() {
        for (int i = 0; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Método 2: Contagem de 1 até fim
    public void contar(int fim) {
        for (int i = 0; i <= fim; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Método 3: Contagem de início até fim
    public void contar(int inicio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Método 4: Contagem de início até fim com pausa
    public void contar(int inicio, int fim, int pausa) {
        for (int i = inicio; i <= fim; i++) {
            System.out.print(i + " ");
            pausarPorSegundo(pausa);
        }
        System.out.println();
    }
}