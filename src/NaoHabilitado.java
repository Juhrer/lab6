public class NaoHabilitado extends Situacao {

    @Override
    public void proximaFase(Pessoa pessoa) {
        pessoa.setSituacao(new Habilitado1o());
    }

    @Override
    public String toString() {
        return "Não habilitado";
    }
}
