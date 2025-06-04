import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Mamiferos {

    public static void main(String[] args) {
        if (Sim("Sou carnívoro?")) {
            if (Sim("Gosto de escalar e explorar?")) {
                if (Sim("Sou adorado no Egito Antigo como um Deus?")) {
                    if (Sim("Sou um gato?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else {
                    if (Sim("Tenho um corpo longo e flexível e sou conhecido por ser um excelente caçador de roedores?")) {
                        if (Sim("Sou um furão?")) {
                            JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
            }
        } else if (Sim("Sou herbívoro?")) {
            if (Sim("Posso correr a 40 km/h?")) {
                if (Sim("Sou um coelho?")) {
                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else {
                if (Sim("Sou um animal que vive em grupo?")) {
                    if (Sim("Sou um porquinho-da-índia?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else {
                    if (Sim("Tenho bochechas grandes para armazenar comida?")) {
                        if (Sim("Sou um hamster?")) {
                            JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }
                    } else {
                        if (Sim("Sou um animal pequeno e ágil?")) {
                            if (Sim("Sou um rato doméstico?")) {
                                JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                            }
                        } else {
                            if (Sim("Minha pelagem é macia e densa?")) {
                                if (Sim("Sou uma chinchila?")) {
                                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                            }
                        }
                    }
                }
            }
        } else if (Sim("Sou onívoro?")) {
            if (Sim("Sou conhecido por ser parente próximo dos humanos?")) {
                if (Sim("Sou um macaco?")) {
                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
            } else {
                if (Sim("Algumas religiões não permitem o meu consumo?")) {
                    if (Sim("Sou um porco?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else if (Sim("Posso ser treinado para realizar truques?")) {
                    if (Sim("Sou um cachorro?")) {
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }
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
