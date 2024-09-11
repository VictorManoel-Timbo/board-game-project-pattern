package factory.casas;

import decorator.JogadorComBone;
import models.Casa;
import models.Jogador;

public class CasaTroca extends Casa {
    @Override
    public void aplicarRegra(Jogador jogador) {
        System.out.println("Casa Troca: Você compra um novo equipamento");
        System.out.println("Seus equipamentos são: " + ((jogador.getEquipamentos()==null) ? "Sem Equipamentos" : jogador.getEquipamentos()));
        if (jogador.getEquipamentos() == null) {
            System.out.println("Comprou um boné por 2 moedas");
            jogador = new JogadorComBone(jogador);
        }
    }
}