import javax.swing.JFrame;

public class CarregaGuiRadio {
    public static void main(String[] args){
        JFrame frame = new JFrame ("Uso de Radio");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GuiRadio());
        frame.setBounds(0,0,500,300);
        frame.setVisible (true);
    }
}