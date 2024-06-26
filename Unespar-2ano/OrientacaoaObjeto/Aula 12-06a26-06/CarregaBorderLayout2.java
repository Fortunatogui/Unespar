import javax.swing.*;

public class CarregaBorderLayout2 {
    public static void main(String[] args){
        JFrame frame = new JFrame ("Uso de BorderStyle");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GuiBorderStyle2());
        frame.setBounds(0,0,500,300);
        frame.setVisible (true);
    }
}
