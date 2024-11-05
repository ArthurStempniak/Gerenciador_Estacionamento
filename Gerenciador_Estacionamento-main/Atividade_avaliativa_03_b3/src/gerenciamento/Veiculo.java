package gerenciamento;

public class Veiculo {
    private String placa;
    private String tamanho; // pequeno, médio, grande

    public Veiculo(String placa, String tamanho) {
        this.placa = placa;
        this.tamanho = tamanho;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        return "Veículo [Placa: " + placa + ", Tamanho: " + tamanho + "]";
    }
}
