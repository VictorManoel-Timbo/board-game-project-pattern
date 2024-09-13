package factory.casas;

import models.Casa;
import models.Jogador;

public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        jogador.setMoedas(jogador.getMoedas()+1);
    }
}