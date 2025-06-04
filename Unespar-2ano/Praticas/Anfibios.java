import javax.swing.*;
import javax.swing.UIManager;

public class Anfibios {
    public static void main(String[] args) {
        if (Sim("Possuo uma pele sensível?")) {
            if (Sim("Já apareci em um filme da Disney?")) {
                if (Sim("Sou um sapo?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em um sapo!\nBeijar um sapo não é recomendado devido ao risco potencial de exposição a doenças ou substâncias tóxicas. Se quiser alguém, baixe o Tinder.");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else if (Sim("Possuo uma pele lisa, úmida e muitas vezes brilhante?")) {
                if (Sim("Sou uma rã?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em uma rã!\nNa China, as rãs são associadas à boa sorte e à prosperidade. Já pode andar carregando uma rã.");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else if (Sim("Possuo a capacidade de regenerar partes do corpo perdidas?")) {
                if (Sim("Sou um tritão?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em um tritão!\nNão aquele ser mitológico, o animal mesmo.");
                } else if (Sim("Sou uma salamandra?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em uma salamandra!\nSou frequentemente associada ao fogo e à transformação, tendo citações em títulos como Harry Potter e Hobbit. E você nada em?");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
            }
        } else if (Sim("Sou da ordem Anura (Anfíbios sem cauda)?")) {
            if (Sim("Sou uma rã?")) {
                JOptionPane.showMessageDialog(null, "Você está pensando em uma rã!\nNa China, as rãs são associadas à boa sorte e à prosperidade. Já pode andar carregando uma rã.");
            } else if (Sim("Sou um sapo?")) {
                JOptionPane.showMessageDialog(null, "Você está pensando em um sapo!\nBeijar um sapo não é recomendado devido ao risco potencial de exposição a doenças ou substâncias tóxicas. Se quiser alguém, baixe o Tinder.");
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
        }
    }

    private static boolean Sim(String pergunta) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int resposta = JOptionPane.showConfirmDialog(null, pergunta, "Pergunta", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }
}
