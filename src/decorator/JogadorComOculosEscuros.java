package decorator;

import models.Jogador;

public class JogadorComOculosEscuros extends JogadorDecorator {
    private final int moedas;
    private final String equipamentos;
    public JogadorComOculosEscuros(Jogador JogadorDecorator) {
        super(JogadorDecorator);
        this.moedas = 3;
        this.equipamentos = "Oculos Escuros";
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
