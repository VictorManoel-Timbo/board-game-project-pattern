import decorator.JogadorComBone;
import decorator.JogadorComMoletom;
import decorator.JogadorComOculosEscuros;
import factory.casas.CasaSimples;
import factory.jogadores.JogadorComum;
import models.Casa;
import models.Jogador;

public class Main {

    public static void main(String[] args) {

        Jogador carro = new JogadorComum();
        Casa casa = new CasaSimples();
        casa.aplicarRegra(carro);
        System.out.println("Equipamentos: " + carro.getEquipamentos());

        System.out.println("Moedas: R$ " + carro.getMoedas());
        

        carro = new JogadorComBone(carro);

        carro = new JogadorComMoletom(carro);

        carro = new JogadorComOculosEscuros(carro);

        System.out.println("Equipamentos: " + carro.getEquipamentos());

        System.out.println("Moedas: R$ " + carro.getMoedas());

    }

}