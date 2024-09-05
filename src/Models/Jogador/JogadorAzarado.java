package Models.Jogador;

public class JogadorAzarado extends Jogador {
    public JogadorAzarado(String nome) {
        super(nome);
    }

    @Override
    public int jogarDados() {
        return (int)(Math.random() * 6) + 1; // Garante que será <= 6
    }

    @Override
    public void aplicarRegraEspecial() {
        // Regras especiais para jogador azarado
    }
}