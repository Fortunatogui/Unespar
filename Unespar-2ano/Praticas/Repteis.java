import javax.swing.*;

public class Repteis extends JFrame{

    static void repteis(){
        if (Sim("Eu gosto de ficar em terra firma?")) {
            if (Sim("Levo um grande peso nas costas?")) {
                if (Sim("Eu sou um Jabuti?")) {
                    JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                } else {
                    if(Sim("Sou conhecido nas histórias por ser lento?")){
                        if(Sim("Sou uma Tartaruga?")){
                            JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                }
            } else {
                if (Sim("Quando me sinto ameaçado, posso inflar meu pescoço?")) {
                    if(Sim("Eu consigo me camuflar no ambiente e possuo escamas que me ajudam a fazer isso?")){
                        if(Sim("Já me chamaram de Rango?")){
                            if(Sim("Sou uma Iguana?")){
                                JOptionPane.showMessageDialog(null, "Obrigada por jogar!");
                            }else{
                                if(Sim("Alguns da minha espécie são bem grandes e podem ter uma mordida supostamente venenosa?")){
                                    if(Sim("Eu sou um Lagarto?")){
                                        JOptionPane.showMessageDialog(null, "Obrigada por jogar!");
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }
                    }else{
                        if(Sim("Comumente me chamam de um ser mitológico?")){
                            if(Sim("Eu sou o Dragão-barbudo?")){
                                JOptionPane.showMessageDialog(null, "Obrigada por jogar!");    
                            }else{
                                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }
                    }
                }
                
            }
            
        }else{
            if(Sim("Estou sempre ou quase sempre na agua?")){
                if(Sim("Sou conhecida nas historias por ser lenta?")){
                    if(Sim("Eu sou uma Tartaruga?")){
                        JOptionPane.showMessageDialog(null, "Obrigada por jogar!");    
                    }else{
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                }else{
                    if(Sim("Eu não possuo pernas, por isso me rastejo pelo chão?")){
                        if(Sim("Eu sou uma Cobra ou Serpente?")){
                            JOptionPane.showMessageDialog(null, "Obrigada por jogar!");    
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
            }
        }
        
    }

    
    public static void main(String[] args){
        repteis();
    }

    private static boolean Sim(String pergunta) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int resposta = JOptionPane.showConfirmDialog(null, pergunta, "Pergunta", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }
}
