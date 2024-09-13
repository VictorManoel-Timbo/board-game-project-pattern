package factory.casas;

import factory.jogadores.JogadorAzarado;
import factory.jogadores.JogadorSortudo;
import models.Casa;
import models.Jogador;

public class CasaAzar extends Casa {
    public CasaAzar(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        if (!(jogador instanceof JogadorSortudo)) {
            System.out.println("Casa Azar: O jogador perde 3 moedas!");
            jogador.setMoedas(jogador.getMoedas()-3);
        } else {
            System.out.println("Casa Azar: O jogador sortudo escapa da praga!");
        }
    }
}
