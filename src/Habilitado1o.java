public class Habilitado1o extends Situacao {

    @Override
    public void proximaFase(Pessoa pessoa) {
        pessoa.setSituacao(new Tomou1o());
    }

    @Override
    public String toString() {
        return "Habilitado para primeira dose";
    }
}
