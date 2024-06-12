import javax.swing.JOptionPane;
  public class TesteMes {

    public static void main(String[] args) {
        
      Mes mes = new Mes();

        String meses = JOptionPane.showInputDialog("Digite o numero do mes: ");
        int MES = Integer.parseInt(meses);
        
        String idiomas = JOptionPane.showInputDialog("Digite o codigo do idioma(1 para portugues, 2 para ingles): ");
        int idioma = Integer.parseInt(idiomas);

        if(idioma == 1){

        JOptionPane.showMessageDialog(null,"Idioma: Portugues" + "\nMês: " + MES + "-" + mes.getMesPorExtenso(MES, idioma)); 
        
        } else {
        JOptionPane.showMessageDialog(null,"Idioma: Ingles" + "\nMês: " + MES + "-" + mes.getMesPorExtenso(MES, idioma)); 
        }
    }
}