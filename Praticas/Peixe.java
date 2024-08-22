import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Peixe {

    static void peixe(){
        if(Sim("Sou um peixe pequeno e muito colorido?")){
            if(Sim("Minha calda parece um lindo vestido longo que flutua pela agua?")){
                if(Sim("Eu sou pacifico e gosto de companhia no aquario?")){
                    if(Sim("Eu sou um peixe Guppy?")){
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }//
                }else{
                    if(Sim("Eu sou um peixe Beta?")){
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    }else{
                        JOptionPane.showMessageDialog(/null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }//
                }//
            }else{
                if(Sim("Eu sou pacifico e gosto de companhia no aquario?")){
                    if(Sim("Eu realizo desova em plantas?")){
                        if(Sim("Eu um peixe Tetra?")){
                            JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }//
                    }else{
                        if(Sim("Em vez de colocar ovos, dou aluz filhos já formados, como se fosse uma pequena 'maternidade aquatica'?")){
                            if(Sim("Eu sou um peixe Guppy?")){
                                JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                            }else{
                                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                            }//
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }//
                    }//
                }else{
                    JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                }//
                
            }//
        }else{
            if(Sim("Eu sou um artista subaquatico que espalha ovos como se fossem pequenas perolas em um esconderijo secreto?")){
                if(Sim("Eu sou um dos peixes mais conhecidos e posso brilhar como ouro?")){
                    if(Sim("Eu sou um Peixinho Dourado?")){
                        JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }//
                }else{
                    if(Sim("As pessoas costumam me tatuar em seu corpo?")){
                        if(Sim("Eu sou uma Carpa?")){
                            JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }//
                    }
                }//
            }else{
                if(Sim("Participei de um filme famoso da Disney?")){
                    if(Sim("O meu nome sugere que sou engraçado?")){
                        if(Sim("Eu sou um Peixe-palhaço?")){
                            JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }//
                    }else{
                        if(Sim("Tenho perda de memoria recente?")){
                            if(Sim("Eu sou um peixe Cirurgiao-patela?")){
                                JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                            }else{
                                JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                            }//
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }//
                    }//
                }else{
                    if(Sim("Eu possuo bigodes como um gato?")){
                        if(Sim("Eu sou um peixe Bagre")){
                            JOptionPane.showMessageDialog(null, "Acertei, obrigado por jogar!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                        }//
                    }else{
                        JOptionPane.showMessageDialog(null, "Desculpe, não vendemos esse animal! Posso tentar adivinhar outro?");
                    }//
                }//
            }//
        }//
    }

    public static void main(String[] args){

    }
    
    private static boolean Sim(String pergunta) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int resposta = JOptionPane.showConfirmDialog(null, pergunta, "Pergunta", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }
}
