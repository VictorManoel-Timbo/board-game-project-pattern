package factory.casas;

import models.Casa;
import models.Jogador;

import java.util.ArrayList;

public class CasaPrisao extends Casa {
    public CasaPrisao(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        jogador.setJogadaPreso(jogador.getJogadas());
        jogador.setPreso(true);
        System.out.println("Casa Prisão: Jogador fica preso por 2 rodadas");
    }
}