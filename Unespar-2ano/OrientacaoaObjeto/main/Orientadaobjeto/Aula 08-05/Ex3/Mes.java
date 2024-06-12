import javax.swing.JOptionPane;

public class Mes {

    private static final String[] MesesPortugues = {
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    };

    private static final String[] MesesIngles = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public String getMesPorExtenso(int mes, int idioma) {
        String[] meses;

        if (idioma == 1) { // Português
            meses = MesesPortugues;
        } else if (idioma == 2) { // Inglês
            meses = MesesIngles;
        } else {
            return "Idioma inválido";
        }

        if (mes < 1 || mes > 12) {
            return "Mês inválido";
        }

        return meses[mes - 1];
    }
}