import javax.swing.JFrame;

public class CarregaGuiLabel {
    public static void main(String[] args){
        JFrame frame = new JFrame ("Uso de Label");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GuiLabel());
        frame.setBounds(0,0,500,300);
        frame.setVisible (true);
    }
}