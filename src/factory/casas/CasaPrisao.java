package factory.casas;

import models.Casa;
import models.Jogador;

public class CasaPrisao extends Casa {
    @Override
    public void aplicarRegra(Jogador jogador) {
        jogador.setPreso(true);
        System.out.printf("Casa Prisão: Fica preso por %d rodadas\n");
    }
}