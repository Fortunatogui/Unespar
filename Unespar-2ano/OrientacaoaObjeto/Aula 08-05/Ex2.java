import javax.swing.JOptionPane;
import java.util.Random;

public class Ex2 {

    public static void main(String[] args) {
        
        int [] n = new int[11];
        Random random = new Random();
        int mes = random.nextInt(11);

        if(mes == 0){
            System.out.println("Mês sorteado: Janeiro");
        } else if(mes == 1){
            System.out.println("Mês sorteado:Fevereiro");
        } else if(mes == 2){
            System.out.println("Mês sorteado:Março");
        } else if(mes == 3){
            System.out.println("Mês sorteado:Abril");
        } else if(mes == 4){
            System.out.println("Mês sorteado:Maio");
        } else if(mes == 5){
            System.out.println("Mês sorteado:Junho");
        } else if(mes == 6){
            System.out.println("Mês sorteado:Julho");
        } else if(mes == 7){
            System.out.println("Mês sorteado:Agosto");
        } else if(mes == 8){
            System.out.println("Mês sorteado:Setembro");
        } else if(mes == 9){
            System.out.println("Mês sorteado:Outubro");
        } else if(mes == 10){
            System.out.println("Mês sorteado:Novembro");
        } else {
            System.out.println("Mês sorteado:Dezembro");
        }
    } 
}
