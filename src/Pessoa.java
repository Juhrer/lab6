import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class Pessoa {

    private final String nome;
    private final String cpf;
    private String endereco;
    private Long cartao;
    private String email;
    private String telefone;
    private String profissao;
    private Set<String> comorbidades;
    private final LocalDate nascimento;
    private Situacao situacao;

    public Pessoa(String nome, String cpf, String endereco, Long cartao, String email, String telefone, String profissao
            , Set<String> comorbidades, LocalDate nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cartao = cartao;
        this.email = email;
        this.telefone = telefone;
        this.profissao = profissao;
        this.comorbidades = comorbidades;
        this.nascimento = nascimento;
        this.situacao = new NaoHabilitado();
    }

    public void proximaFase() {
        situacao.proximaFase(this);
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getProfissao() {
        return profissao;
    }

    public Set<String> getComorbidades() {
        return comorbidades;
    }

    public int getIdade() {
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCartao(Long cartao) {
        this.cartao = cartao;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public void setComorbidades(Set<String> comorbidades) {
        this.comorbidades = comorbidades;
    }
}
