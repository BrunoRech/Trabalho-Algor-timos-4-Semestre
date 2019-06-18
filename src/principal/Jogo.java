/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import control.ArrayList;
import control.ArrayQueue;
import control.ArrayStack;
import control.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * @since 29/08/2018
 * @version
 * @author Bruno Galeazzi Rech
 */
public class Jogo {

    //declaração das listas
    public static String vezDeQuem = "inicio";
    static List<Carta> maoJogador = new ArrayList<Carta>(5);
    static List<Carta> maoComputador = new ArrayList<Carta>(5);
    static ArrayList<Carta> monte = new ArrayList<Carta>(20);
    static ArrayStack<Integer> idMaoJ = new ArrayStack<>(5);
    static ArrayStack<Integer> idMaoP = new ArrayStack<>(5);
    static ArrayQueue<Carta> monteJogador = new ArrayQueue<Carta>(20);
    static ArrayQueue<Carta> monteComputador = new ArrayQueue<Carta>(20);

    public static void main(String[] args) {//main
        Jogo.setup();
        new PainelJogador().setVisible(true);
    }

    public static void setup() { //processamento inicial do jogo
        Random r = new Random();
        monte.add(0, new Carta(3217, 369, 280, 1590, 451, "Maserati Assetto Corsa"));
        monte.add(0, new Carta(3996, 363, 250, 1715, 476, "Jaguar XKR"));
        monte.add(0, new Carta(3600, 462, 315, 1451, 445, "Porshe 911 GT2"));
        monte.add(0, new Carta(5935, 460, 306, 1863, 467, "Aston Martin Vanquish"));
        monte.add(0, new Carta(5379, 326, 225, 2480, 565, "Rolls-Royce Park Ward"));
        monte.add(0, new Carta(6258, 444, 250, 2035, 516, "Mercedes S 63 AMG"));
        monte.add(0, new Carta(3996, 363, 252, 1820, 515, "Daimler Super V8"));
        monte.add(0, new Carta(6750, 426, 270, 2450, 522, "Bentley Continental T"));
        monte.add(0, new Carta(2995, 201, 180, 1865, 458, "Lexus RX 300"));
        monte.add(0, new Carta(5439, 347, 235, 2300, 464, "Mercedes ML 55 AMG"));
        monte.add(0, new Carta(4553, 218, 196, 2120, 471, "Range Rover"));
        monte.add(0, new Carta(4398, 286, 207, 2170, 466, "BMW X5"));
        monte.add(0, new Carta(7993, 1001, 404, 1385, 438, "Bugatti EB 16.4 Veyron"));
        monte.add(0, new Carta(5474, 485, 320, 1690, 455, "Ferrari 550 Maranello"));
        monte.add(0, new Carta(3760, 680, 365, 1300, 435, "B. Engineering Edonis"));
        monte.add(0, new Carta(5992, 550, 332, 1625, 447, "Lamborghini Diablo"));
        monte.add(0, new Carta(4266, 279, 230, 2180, 579, "Binz XL"));
        monte.add(0, new Carta(5409, 380, 200, 2870, 640, "American Custom Lincoln"));
        monte.add(0, new Carta(4398, 286, 258, 1000, 409, "Morgan Aero 8"));
        monte.add(0, new Carta(5987, 1200, 400, 1380, 412, "Lotec Sirius"));
        for (int i = 0; i < 5; i++) {
            idMaoJ.push(i);
            idMaoP.push(i);
        }

        for (int i = 0; i < monte.size(); i++) {
            aplicarPontuacao(monte.get(i));
        }
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                monteJogador.enqueue(monte.removeAndMove(r.nextInt(monte.size())));
            } else {
                monteComputador.enqueue(monte.removeAndMove(r.nextInt(monte.size())));
            }
        }
        pescarCartas("jogador");
        pescarCartas("pc");

    }

    private static void aplicarPontuacao(Carta c) { //aplicar pontuação referente a cada carta
        int pontuacao = 0;
        if (c.getCilindradas() < 4990) {
            pontuacao--;
        } else if (c.getCilindradas() >= 4990 && c.getCilindradas() < 6000) {
            pontuacao += 2;
        } else {
            pontuacao += 3;
        }
        if (c.getPotencia() < 456) {
            pontuacao--;
        } else if (c.getPotencia() >= 456 && c.getPotencia() <= 680) {
            pontuacao += 2;
        } else {
            pontuacao += 3;
        }
        if (c.getVelMax() < 274) {
            pontuacao--;
        } else if (c.getVelMax() >= 274 && c.getVelMax() <= 320) {
            pontuacao += 2;
        } else {
            pontuacao += 3;
        }
        if (c.getPeso() < 1864) {
            pontuacao--;
        } else if (c.getPeso() >= 1864 && c.getPeso() <= 2200) {
            pontuacao += 2;
        } else {
            pontuacao += 3;
        }
        if (c.getComprimento() < 482) {
            pontuacao--;
        } else if (c.getComprimento() >= 482 && c.getComprimento() <= 515) {
            pontuacao += 2;
        } else {
            pontuacao += 3;
        }
        c.setPontuacao(pontuacao);
    }

    public static void addMonteVencedor(String vencedor, Carta c1, Carta c2) {//adiciona as cartas da rodada ao baralho do vencedor
        if (vencedor.equalsIgnoreCase("pc")) {
            monteComputador.enqueue(c1);
            monteComputador.enqueue(c2);
        } else if (vencedor.equalsIgnoreCase("jogador")) {
            monteJogador.enqueue(c1);
            monteJogador.enqueue(c2);
        }
    }

    public static void proseguir(Carta pc, Carta j, String atr) {//método auxiliar
        new Duelo(j, pc, atr).setVisible(true);//interface que compara as cartas dos 2 jogadores
        Jogo.quemGanha(j, pc, atr);
    }

    public static void quemGanha(Carta jogador, Carta pc, String atributo) {//comparação de atributos das cartas da rodada
        switch (atributo) {
            case "Potencia":
                if (pc.getPotencia() > jogador.getPotencia()) {
                    addMonteVencedor("pc", jogador, pc);
                    JOptionPane.showMessageDialog(null, "Vencedor Pc");
                } else if (pc.getPotencia() < jogador.getPotencia()) {
                    addMonteVencedor("jogador", pc, jogador);
                    JOptionPane.showMessageDialog(null, "Vencedor Jogador");
                }
                break;
            case "Velocidade":
                if (pc.getVelMax() > jogador.getVelMax()) {
                    addMonteVencedor("pc", jogador, pc);
                    JOptionPane.showMessageDialog(null, "Vencedor Pc");
                } else if (pc.getVelMax() < jogador.getVelMax()) {
                    addMonteVencedor("jogador", pc, jogador);
                    JOptionPane.showMessageDialog(null, "Vencedor Jogador");
                }
                break;
            case "Cilindradas":
                if (pc.getCilindradas() > jogador.getCilindradas()) {
                    addMonteVencedor("pc", jogador, pc);
                    JOptionPane.showMessageDialog(null, "Vencedor Pc");
                } else if (pc.getCilindradas() < jogador.getCilindradas()) {
                    addMonteVencedor("jogador", pc, jogador);
                    JOptionPane.showMessageDialog(null, "Vencedor Jogador");
                }
                break;
            case "Comprimento":
                if (pc.getComprimento() > jogador.getComprimento()) {
                    addMonteVencedor("pc", jogador, pc);
                    JOptionPane.showMessageDialog(null, "Vencedor Pc");
                } else if (pc.getComprimento() < jogador.getComprimento()) {
                    addMonteVencedor("jogador", pc, jogador);
                    JOptionPane.showMessageDialog(null, "Vencedor Jogador");
                }
                break;
            case "Peso":
                if (pc.getPeso() > jogador.getPeso()) {
                    JOptionPane.showMessageDialog(null, "Vencedor Pc");
                    addMonteVencedor("pc", jogador, pc);

                } else if (pc.getPeso() < jogador.getPeso()) {
                    JOptionPane.showMessageDialog(null, "Vencedor Jogador");
                    addMonteVencedor("jogador", pc, jogador);

                }
                break;

        }
    }

    public static void pescarCartas(String jogador) {//pescar cartas
        Carta c;
        if (jogador.equalsIgnoreCase("jogador")) {
            while (!idMaoJ.isEmpty()) {
                c = monteJogador.dequeue();
                if (c == null) {
                    break;
                } else {
                    maoJogador.set(idMaoJ.pop(), c);
                }
            }
        } else {
            while (!idMaoP.isEmpty()) {
                c = monteComputador.dequeue();
                if (c == null) {
                    break;
                } else {
                    maoComputador.set(idMaoP.pop(), c);
                }
            }
        }

    }

    public static Carta PcEscolherCarta(String atr) {//algorítimo do pc para escolha das suas cartas na rodada
        Carta c = new Carta();
        if (atr.equalsIgnoreCase("none")) {
            c = maoComputador.remove(0);
            idMaoP.push(0);
            for (int i = 0; i < 4; i++) {
                if (maoComputador.get(i + 1) != null && c != null) {
                    if (c.getPontuacao() < maoComputador.get(i + 1).getPontuacao()) {
                        maoComputador.set(idMaoP.pop(), c);
                        c = maoComputador.remove(i + 1);
                        idMaoP.push(i + 1);
                    }
                } else if (c == null) {
                    maoComputador.set(idMaoP.pop(), c);
                    idMaoP.push(i + 1);
                    c = maoComputador.remove(i + 1);
                }
            }
            return c;
        } else {
            for (int i = 0; i < 4; i++) {
                c = maoComputador.get(i);
                if (c == null && maoComputador.get(i + 1) != null) {
                    i++;
                    c = maoComputador.get(i);
                }
                try {
                    switch (atr) {
                        case "Cilindradas":
                            if (c.getCilindradas() > 4990) {
                                maoComputador.remove(i);
                                idMaoP.push(i);
                                return c;
                            }
                            break;
                        case "Potencia":
                            if (c.getPotencia() > 456) {
                                maoComputador.remove(i);
                                idMaoP.push(i);
                                return c;
                            }
                            break;
                        case "Velocidade":
                            if (c.getVelMax() > 274) {
                                maoComputador.remove(i);
                                idMaoP.push(i);
                                return c;
                            }
                            break;
                        case "Peso":
                            if (c.getPeso() > 1864) {
                                maoComputador.remove(i);
                                idMaoP.push(i);
                                return c;
                            }
                            break;
                        case "Comprimento":
                            if (c.getComprimento() > 482) {
                                maoComputador.remove(i);
                                idMaoP.push(i);
                                return c;
                            }
                            break;
                    }
                } catch (Exception e) {
                    //próxima carta também é nula
                }

            }
        }
        return c;
    }

    public static String PcEscolherAtributo() {//algorítimo para a escolha do atributo na vez do computador
        //4990 456  274  1864  482
        Random r = new Random();
        String atributoBom = "none";
        for (int i = 0; !atributoBom.equalsIgnoreCase("none") == false; i++) {
            try {
                switch (r.nextInt(4)) {
                    case 0:
                        if (maoComputador.get(i).getCilindradas() > 4990) {
                            atributoBom = "Cilindradas";
                        }
                        break;
                    case 1:
                        if (maoComputador.get(i).getPotencia() > 456) {
                            atributoBom = "Potencia";
                        }
                        break;
                    case 2:
                        if (maoComputador.get(i).getVelMax() > 274) {
                            atributoBom = "Velocidade";
                        }
                        break;
                    case 3:
                        if (maoComputador.get(i).getPeso() > 1864) {
                            atributoBom = "Peso";
                        }
                        break;
                    case 4:
                        if (maoComputador.get(i).getComprimento() > 482) {
                            atributoBom = "Comprimento";
                        }
                        break;
                }
            } catch (Exception e) { //significa que o espaço da mão está vazio

            }
            if (i >= 4) {
                i = 0;
            }
        }
        return atributoBom;
    }

}
