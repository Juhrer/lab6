
public class Tomou1o extends Situacao {

    private int dias;

    public Tomou1o() {
        dias = 0;
    }

    @Override
    public void proximaFase(Pessoa pessoa) {
        dias = 20;
        pessoa.setSituacao(new Habilitado2o());
    }

    @Override
    public String toString() {
        return "Tomou a primeira dose";
    }
}