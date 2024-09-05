package Models.Jogador;

public class JogadorNormal extends Jogador {
    public JogadorNormal(String nome) {
        super(nome);
    }

    @Override
    public int jogarDados() {
        return (int)(Math.random() * 11) + 2; // Pode ser de 2 a 12
    }

    @Override
    public void aplicarRegraEspecial() {
        // Regras especiais para jogador normal
    }
}