public class Habilitado2o extends Situacao {
    @Override
    public void proximaFase(Pessoa pessoa) {
        pessoa.setSituacao(new Vacinado());
    }

    @Override
    public String toString() {
        return "Habilitado para segunda dose";
    }
}
