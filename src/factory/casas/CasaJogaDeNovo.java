package factory.casas;

import models.Casa;
import models.Jogador;

import java.util.Arrays;

public class CasaJogaDeNovo extends Casa {
    public CasaJogaDeNovo(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        System.out.println("Casa Joga de Novo: O jogador lança os dados novamente!");
        int casas = Arrays.stream(jogador.jogarDados()).sum();
        jogador.setPosicao(jogador.getPosicao()+casas);
    }
}