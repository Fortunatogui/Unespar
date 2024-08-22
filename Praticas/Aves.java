import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Aves {

    public static void main(String[] args) {
        if (Sim("Sou uma ave de pequeno porte?")) {
            if (Sim("Sou bem colorido?")) {
                if (Sim("Posso falar?")) {
                    if (Sim("Sou um Periquito?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else {
                    if (Sim("Sou um Diamante Mandarim?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                }
            } else {
                if (Sim("Sou conhecido por cantar?")) {
                    if (Sim("Sou um Canário?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else {
                    if (Sim("Sou um Azulão?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                }
            }
        } else if (Sim("Sou uma ave de médio porte?")) {
            if (Sim("Tenho bochechas vermelhas?")) {
                if (Sim("Sou uma Calopsita?")) {
                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else if (Sim("Posso falar?")) {
                if (Sim("Sou um Papagaio?")) {
                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else if (Sim("Sou um animal urbano?")) {
                if (Sim("Sou um Pombo?")) {
                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
            }
        } else if (Sim("Sou uma ave de grande porte?")) {
            if (Sim("Sou uma ave tropical?")) {
                if (Sim("Sou protagonista de um filme?")) {
                    if (Sim("Sou uma Arara?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else if (Sim("Tenho penas brancas e uma crista?")) {
                    if (Sim("Sou uma Cacatua?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else if (Sim("Sou o parente vivo mais próximo dos dinossauros?")) {
                if (Sim("Sou uma Galinha?")) {
                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
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