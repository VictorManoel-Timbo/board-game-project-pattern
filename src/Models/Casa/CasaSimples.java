package Models.Casa;

import Models.Jogador.Jogador;

public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        jogador.adicionarMoeda(1);
        System.out.println(jogador.getNome() + " ganhou 1 moeda.");
    }
}