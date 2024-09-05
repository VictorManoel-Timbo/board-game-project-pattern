package Factories;

import Models.Jogador.Jogador;
import Models.Jogador.JogadorAzarado;
import Models.Jogador.JogadorComSorte;
import Models.Jogador.JogadorNormal;

public class JogadorFactory {
    public static Jogador criarJogador(String tipo, String nome) {
        switch (tipo) {
            case "sorte":
                return new JogadorComSorte(nome);
            case "azarado":
                return new JogadorAzarado(nome);
            case "normal":
            default:
                return new JogadorNormal(nome);
        }
    }
}