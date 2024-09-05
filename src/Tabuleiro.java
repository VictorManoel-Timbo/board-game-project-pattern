import Models.Casa.Casa;
import Models.Jogador.Jogador;

import java.util.ArrayList;

public class Tabuleiro {
    private static Tabuleiro instanciaUnica;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Casa> casas;

    private Tabuleiro() {
        jogadores = new ArrayList<>();
        casas = new ArrayList<>();
    }

    public static Tabuleiro getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Tabuleiro();
        }
        return instanciaUnica;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void adicionarCasa(Casa casa) {
        casas.add(casa);
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public void printStatus() {
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome() + " na casa " + jogador.getPosicao() + " com " + jogador.getMoedas() + " moedas.");
        }
    }
}
