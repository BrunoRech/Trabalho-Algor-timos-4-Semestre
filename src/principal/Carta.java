/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

/**
 * @since 29/08/2018
 * @version
 * @author Bruno Galeazzi Rech
 */
public class Carta {
    private int peso,potencia,velMax,cilindradas,comprimento,pontuacao;
    private String nome;

    public Carta(int cilindradas, int potencia, int velMax, int peso, int comprimento, String nome) {
        this.peso = peso;
        this.potencia = potencia;
        this.velMax = velMax;
        this.cilindradas = cilindradas;
        this.nome = nome;
        this.comprimento = comprimento;
    }

    Carta() {
       this.peso = 0;
        this.potencia = 0;
        this.velMax = 0;
        this.cilindradas = 0;
        this.nome = "null";
        this.comprimento = 0;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPeso() {
        return peso;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getVelMax() {
        return velMax;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public int getComprimento() {
        return comprimento;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + "\n\nPeso = " + peso + "Kg\n\nPotência = " + potencia + "Cv\n\nVelMax = " + velMax + "Km/hr\n\nCilindradas = " + cilindradas + "Cc\n\nComprimento = " + comprimento + "Cm";
    }


  
    
}
