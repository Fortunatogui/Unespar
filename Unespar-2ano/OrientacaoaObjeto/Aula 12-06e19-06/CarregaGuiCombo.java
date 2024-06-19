import javax.swing.JFrame;

public class CarregaGuiCombo {

    public static void main(String[] args){

        JFrame frame = new JFrame ("Uso de Combo");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GuiCombo());
        frame.setBounds(0,0,500,300);

        frame.setVisible (true);
    }
}
