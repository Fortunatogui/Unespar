import javax.swing.JFrame;

public class CarregaGuiDialogoConfirmacao {

    public static void main(String[] args){

        JFrame frame = new JFrame ("Uso da caixa de opcao");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GuiDialogoConfirmacao());
        frame.setBounds(0,0,500,300);

        frame.setVisible (true);
    }
}
