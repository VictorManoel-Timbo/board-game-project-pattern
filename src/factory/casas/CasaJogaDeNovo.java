package factory.casas;

import models.Casa;
import models.Jogador;

import java.util.ArrayList;
import java.util.HashMap;

import static models.Jogo.*;

public class CasaJogaDeNovo extends Casa {
    HashMap<String, String> nomeDasCores = new HashMap<>() {{
        put(RED, "vermelho");
        put(GREEN, "verde");
        put(YELLOW, "amarelo");
        put(BLUE, "azul");
        put(PURPLE, "roxo");
        put(CIANO, "ciano");
    }};
    public CasaJogaDeNovo(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        System.out.println("Casa Joga de Novo: O jogador lança os dados novamente!");
        int[] dados = jogador.jogarDados();
        int somaDados = dados[0] + dados[1];
        String nomeCor = nomeDasCores.get(jogador.getCor());
        System.out.println(jogador.getCor() + "Jogador " + nomeCor + " jogou os dados: " + dados[0] + " e " + dados[1] + ". Soma: " + somaDados + RESET);
        jogador.setPosicao(jogador.getPosicao()+somaDados);
        System.out.println(jogador.getCor() +"Jogador " + nomeCor + " moveu para a casa " + jogador.getPosicao() + RESET+ "\n");
        jogador.jogada();
    }
}