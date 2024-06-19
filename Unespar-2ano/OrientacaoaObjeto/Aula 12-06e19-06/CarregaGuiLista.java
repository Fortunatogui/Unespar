import javax.swing.JFrame;

public class CarregaGuiLista {

    public static void main(String[] args){

        JFrame frame = new JFrame ("Uso de lista");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GuiLista());
        frame.setBounds(0,0,500,300);

        frame.setVisible (true);
    }
}
