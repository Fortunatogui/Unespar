import javax.swing.*;
import javax.swing.UIManager;

public class Invertebrados {

    public static void main(String[] args) {
        if (Sim("Tenho 6 patas ou mais?")) {
            if (Sim("Sou carnívora?")) {
                if (Sim("Posso ser formidável quando se trata de caçar?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em uma aranha!\nUma das minhas espécies mais conhecidas tem como hábito devorar o macho depois do acasalamento. Errada não tá né");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else if (Sim("Sou onívoro?")) {
                if (Sim("Posso criar túneis para uma melhor locomoção do grupo?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em uma formiga!\nA rainha é a única fêmea reprodutora em uma colônia, ela é responsável por pôr os ovos que darão origem a novas formigas. Viva a monogamia!");
                } else {
                    JOptionPane.showMessageDialog(null, "Você está pensando em um caranguejo!\nAs conchas desempenham um papel crucial na proteção e no bem-estar dos caranguejos. Pensa bem antes de ir pra praia e pegar um monte pra depois jogar fora");
                }
            } else if (Sim("Sou um ótimo saltador?")) {
                if (Sim("Sou herbívoro?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em um gafanhoto!\nUma das 10 pragas enviadas por Deus no Egito devorando todas as plantações restantes e deixando o país em ruínas. Se eu visse um enxame disso já estaria em outro país");
                } else {
                    JOptionPane.showMessageDialog(null, "Você está pensando em um grilo!\nUm dos grilos mais icônicos da cultura popular é o Grilo Falante, personagem que atua como a consciência de Pinóquio. Bem que podia falar só na animação mesmo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
            }
        } else if (Sim("Me movo usando contrações musculares?")) {
            if (Sim("Preciso do auxílio de algo material para sobreviver?")) {
                if (Sim("Posso ser usado como isca?")) {
                    JOptionPane.showMessageDialog(null, "Você está pensando em um caracol!\nEm muitas culturas europeias, especialmente na França, caracóis são considerados uma iguaria. Eles são servidos como um prato sofisticado. Hmm que delicinha um caracol");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else if (Sim("Posso criar túneis para uma melhor locomoção do grupo?")) {
                JOptionPane.showMessageDialog(null, "Você está pensando em uma minhoca!\nAs minhocas são cegas e surdas, precisam do tato e da detecção química para navegar e interagir com o ambiente. Guerreira, periférica e humildade nem se fala");
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
