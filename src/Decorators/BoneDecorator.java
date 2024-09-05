package Decorators;

import Models.Jogador.Jogador;

class BoneDecorator extends JogadorDecorator {
    public BoneDecorator(Jogador jogador) {
        super(jogador);
    }

    @Override
    public int jogarDados() {
        return 0;
    }

    @Override
    public void aplicarRegraEspecial() {
        jogadorDecorado.adicionarMoeda(1); // Sempre ganha 1 moeda extra em casa simples
    }
}