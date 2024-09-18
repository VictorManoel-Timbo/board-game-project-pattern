package models;

public class Jogo {
    Tabuleiro tabuleiro = Tabuleiro.instancia();
    public void config(int numJogadores) {
        tabuleiro.setarJogadores(numJogadores);
    }

    public void configTabuleiro(int numCasas) {
        tabuleiro.setarCasas(numCasas);
    }

    public void printTabuleiro() {
        tabuleiro.mostrarEstado();
    }

    public void start() {
        tabuleiro.isFinished();
    }


}
