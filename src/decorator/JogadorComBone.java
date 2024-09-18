package decorator;

import models.Jogador;

public class JogadorComBone extends JogadorDecorator {
    private final int moedas;
    private final String equipamentos;
    public JogadorComBone(Jogador JogadorDecorator) {
        super(JogadorDecorator);
        this.moedas = 1;
        this.equipamentos = "Boné";
    }
    @Override
    public int getMoedas() {
        return super.getMoedas() + moedas;
    }
    @Override
    public String getEquipamentos() {
        return equipamentos;
    }
}