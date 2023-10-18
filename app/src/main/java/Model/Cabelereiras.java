package Model;

public class Cabelereiras {
    private String Nome;
    private String Telefone;
    private Float Avaliacao;

    public Cabelereiras() {
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public Float getAvaliacao() {
        return Avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        Avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Calereiras{" +
                "Nome='" + Nome + '\'' +
                ", Telefone='" + Telefone + '\'' +
                ", avaliacao=" + Avaliacao +
                '}';
    }
}
