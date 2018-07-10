/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import tab.Tabuleiro;
import java.util.Random;
import java.util.ArrayList;


/**
 *
 * @author Jefferson
 */
public class Wumpus {

    public static Object tabs;

    String Heroi = "H";
    String Wumpus = "W";
    String Fedor = "F";
    String Poço = "P";
    String Brisa = "B";
    String Ouro = "O";

    int LinhaHeroi = 3;
    int ColunaHeroi = 0;
    boolean GameOver = false;

    String[][] Mapa;

    
    String[][] Visão
            = {{"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {Heroi, "", "", ""}};
    private boolean venceu = false;

    public void setMapa() {
        Tabuleiro mundoWumpus = new Tabuleiro();
        this.Mapa = mundoWumpus.Tabuleiro();
    }

    public void printMapa() {

        for (int i = 0; i < Mapa.length; i++) {
            for (int j = 0; j < Mapa[0].length; j++) {
                System.out.print(this.Mapa[i][j] + " | ");

            }
            System.out.println();
        }

        System.out.println("**********");

    }

    public void visão() {
        for (int i = 0; i < Visão.length; i++) {
            for (int j = 0; j < Mapa.length; j++) {
                System.out.print(this.Visão[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("==============");

    }

    public double[] Sensores() {

        double[] direcao = {8,8,8,8};

        if (Mapa[LinhaHeroi][ColunaHeroi].equals(Fedor) && Mapa[LinhaHeroi][ColunaHeroi].equals(Fedor)) {
            if (LinhaHeroi - 1 >= 0) {
                if (!Visão[LinhaHeroi-1][ColunaHeroi].equals("k") && !Visão[LinhaHeroi-1][ColunaHeroi].equals("")) {
                    direcao[0] = 1;
                }else{
                    direcao[0] = 8;
                }
            }if (LinhaHeroi + 1 <= 3) {
                if (!Visão[LinhaHeroi+1][ColunaHeroi].equals("k")&& !Visão[LinhaHeroi+1][ColunaHeroi].equals("")) {
                    direcao[1] = 1;
                }else{
                    direcao[1] = 8;
                }
            }if (ColunaHeroi - 1 >= 0) {
                if (!Visão[LinhaHeroi][ColunaHeroi-1].equals("k")&& !Visão[LinhaHeroi][ColunaHeroi-1].equals("")) {
                    direcao[2] = 1;
                }else{
                    direcao[2] = 8;
                }
            }if (ColunaHeroi + 1 <= 3) {
                if (!Visão[LinhaHeroi][ColunaHeroi+1].equals("k")&& !Visão[LinhaHeroi][ColunaHeroi+1].equals("")) {
                    direcao[3] = 1;
                }else{
                    direcao[3] = 8;
                }
            }
        }
        
        return direcao;

    }

    public void Movimentar() {
        Random ran = new Random();
        double[] senso =  Sensores();
        double sum = 0;
        for (int i = 0; i < senso.length; i++) {
            sum+=senso[i];
        }
        double prob[] = new double[senso.length];
        double probSum[] = new double[senso.length];
        for (int i = 0; i < prob.length; i++) {
            prob[i] = senso[i]/sum;
        }
        sum = 0;
        for (int i = 0; i < probSum.length; i++) {
            probSum[i] = prob[i]+sum;
            sum+=prob[i];
        }
        int opcao = 0;

        double value = ran.nextDouble();
        if (value<=probSum[0]) {
            opcao = 0;
        }else if (value<=probSum[1]) {
            opcao = 1;
        }else if (value<=probSum[2]) {
            opcao = 2;
        }else if (value<=probSum[3]) {
            opcao = 3;
        }
        
        switch (opcao) {
            case 0:
                if (LinhaHeroi == 0) {
                    Movimentar();
                } else {
                    AndarCima();
                }
                break;

            case 1:
                if (LinhaHeroi == 3) {
                    Movimentar();
                } else {
                    AndarBaixo();
                }
                break;

            case 2:
                if (ColunaHeroi == 3) {
                    Movimentar();
                } else {
                    AndarDireita();
                }
                break;

            case 3:
                if (ColunaHeroi == 0) {
                    Movimentar();
                } else {
                    AndarEsquerda();
                }
                break;
        }

        Verificar();
    }

    public void AndarCima() {
        
        Visão[LinhaHeroi][ColunaHeroi] ="k";
        LinhaHeroi = LinhaHeroi - 1;
        Visão[LinhaHeroi][ColunaHeroi] = Mapa[LinhaHeroi][ColunaHeroi] + Heroi;
        
    }

    public void AndarBaixo() {
        
        Visão[LinhaHeroi][ColunaHeroi] ="k";
        LinhaHeroi = LinhaHeroi + 1;
        Visão[LinhaHeroi][ColunaHeroi] = Mapa[LinhaHeroi][ColunaHeroi] + Heroi;
        
    }

    public void AndarDireita() {
        
        Visão[LinhaHeroi][ColunaHeroi] ="k";
        ColunaHeroi = ColunaHeroi + 1;
        Visão[LinhaHeroi][ColunaHeroi] = Mapa[LinhaHeroi][ColunaHeroi] + Heroi;
        
    }

    public void AndarEsquerda() {
        
        Visão[LinhaHeroi][ColunaHeroi] ="k";
        ColunaHeroi = ColunaHeroi - 1;
        Visão[LinhaHeroi][ColunaHeroi] = Mapa[LinhaHeroi][ColunaHeroi] + Heroi;
        
    }

    public void Verificar() {
        if (Mapa[LinhaHeroi][ColunaHeroi] == Poço || Mapa[LinhaHeroi][ColunaHeroi] == Wumpus) {
            GameOver = true;
            System.out.println("you lose!!!");

        }

        if (Mapa[LinhaHeroi][ColunaHeroi] == Ouro) {
            GameOver = true;
            venceu = true;
            System.out.println(" VOCÊ PEGOU O OURO!! VOLTE AO INICIO!");
        }
    }

    public void mapa1(String[] args) {

        setMapa();

    }

    public static void main(String[] args) {
        Wumpus c = new Wumpus();
        Tabuleiro tab = new Tabuleiro();

        c.Mapa = tab.Tabuleiro();
        c.printMapa();

        c.visão();
        ArrayList<Integer> i = new ArrayList<>();
        ArrayList<Integer> j = new ArrayList<>();
        i.add(c.LinhaHeroi);
        j.add(c.ColunaHeroi);
        while (c.GameOver == false) {
            c.printMapa();
            c.Movimentar();
            c.visão();
            i.add(c.LinhaHeroi);
            j.add(c.ColunaHeroi);
            // Thread.sleep(3000);
        }
        if (c.venceu == true) {
            
            System.out.println("*****************");
            for (int k = i.size() - 1; k >= 0; k--) {
                if (c.LinhaHeroi == 3 && c.ColunaHeroi == 0) {
                    break;
                }
                c.Visão[c.LinhaHeroi][c.ColunaHeroi] = c.Mapa[i.get(k)][j.get(k)] + "";
                c.LinhaHeroi = i.get(k);
                c.ColunaHeroi = j.get(k);
                c.Visão[c.LinhaHeroi][c.ColunaHeroi] = c.Mapa[i.get(k)][j.get(k)] + "H";
                c.visão();

            }

            System.out.println("YEAHHHH, YOU'RE BADASS !!!!!");

        }
    }
}

