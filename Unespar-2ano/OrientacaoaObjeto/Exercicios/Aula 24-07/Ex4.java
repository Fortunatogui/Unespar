import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Ex4 extends JFrame {

    private JLabel statusLabel;
    private JButton okButton;
    private static final String[] DIAS_DA_SEMANA = {
        "Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira",
        "Quinta-feira", "Sexta-feira", "Sábado"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ex4 frame = new Ex4();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public Ex4() {
        inicializarComponentes();
        atualizarMensagem();
    }

    private void inicializarComponentes() {
        setTitle("Status de Atendimento");
        setSize(400, 200);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(statusLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        okButton = new JButton("Ok");
        okButton.setFont(new Font("Arial", Font.PLAIN, 14));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void atualizarMensagem() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        LocalTime time = now.toLocalTime();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
        String formattedTime = now.format(timeFormatter);
        String dayOfWeekString = DIAS_DA_SEMANA[dayOfWeek.getValue() % 7]; // Ajuste para array 0-6

        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 0);

        boolean isWeekday = dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
        boolean isWithinWorkingHours = !time.isBefore(startTime) && !time.isAfter(endTime);

        String status;
        if (isWeekday && isWithinWorkingHours) {
            status = "Estamos atendendo";
        } else {
            status = "Expediente encerrado";
        }

        statusLabel.setText(dayOfWeekString + " - " + formattedTime + " - " + status);
    }
}