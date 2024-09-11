package decorator;

import models.Jogador;

public class JogadorComMoletom extends JogadorDecorator {
    private int moedas;
    private String equipamentos;
    public JogadorComMoletom(Jogador JogadorDecorator) {
        super(JogadorDecorator);
        this.moedas = 2;
        this.equipamentos = "Moletom";
    }
    @Override
    public int getMoedas() {
        return super.getMoedas() + moedas;
    }
    @Override
    public String getEquipamentos() {
        return super.getEquipamentos() + ", " + equipamentos;
    }

}