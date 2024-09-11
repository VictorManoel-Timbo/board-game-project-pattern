package factory.casas;

import factory.jogadores.JogadorFactory;
import models.Casa;
import models.Jogador;

import java.util.Random;

public class CasaSurpresa extends Casa {
    Jogador novoTipo;
    @Override
    public void aplicarRegra(Jogador jogador) {
        Random random = new Random();
        int novoTipo = random.nextInt(3)+1;
    }
}