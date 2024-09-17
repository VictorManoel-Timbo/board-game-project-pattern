package factory.casas;

import models.Casa;
import models.Jogador;

import java.util.ArrayList;

public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        jogador.setMoedas(jogador.getMoedas()+1);
    }
}