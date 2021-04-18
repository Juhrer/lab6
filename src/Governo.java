import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Governo {

    private HashSet<String> comorbidades;
    private HashSet<String> profissoes;
    private int idadeMinima;
    private final HashMap<String, Pessoa> pessoas;

    public Governo() {
        comorbidades = new HashSet<>();
        profissoes = new HashSet<>();
        pessoas = new HashMap<>();
        idadeMinima = 100;
    }

    public void setComorbidades(HashSet<String> comorbidades) {
        this.comorbidades = comorbidades;
        atualizouCondicoes();
    }

    public void setProfissoes(HashSet<String> profissoes) {
        this.profissoes = profissoes;
        atualizouCondicoes();
    }

    public void setIdadeMinima(int idadeMinima) {
        this.idadeMinima = idadeMinima;
        atualizouCondicoes();
    }

    private void atualizouCondicoes() {
        for(Pessoa pessoa: pessoas.values()) {
            if(pessoa.getSituacao().toString().equals("Não habilitado"))
                isHabilitado(pessoa);
        }
    }

    public void cadastraPessoa(String nome, String cpf, String endereco, Long cartao, String email, String telefone
            , String profissao, Set<String> comorbidades, LocalDate nascimento) {
        Pessoa pessoa = new Pessoa(nome, cpf, endereco, cartao, email, telefone, profissao, comorbidades, nascimento);
        pessoas.put(cpf, pessoa);
        isHabilitado(pessoa);
    }

    private void isHabilitado(Pessoa pessoa) {
        if (profissoes.contains(pessoa.getProfissao())) {
            pessoa.proximaFase();
            return;
        } for(String comobidade : comorbidades){
            if(pessoa.getComorbidades().contains(comobidade)) {
                pessoa.proximaFase();
                return;
            }
        } if(idadeMinima <= pessoa.getIdade())
            pessoa.proximaFase();
    }

    public boolean tomaVacina(String cpf) {
        Pessoa pessoa = pessoas.get(cpf);
        String situacao = pessoa.getSituacao().toString();
        if(situacao.equals("Habilitado para primeira dose") || situacao.equals("Habilitado para segunda dose")) {
            pessoa.proximaFase();
            return true;
        } else
            return false;
    }

    public boolean passa20Dias(String cpf) {
        Pessoa pessoa = pessoas.get(cpf);
        if(pessoa.getSituacao().toString().equals("Tomou a primeira dose")) {
            pessoa.proximaFase();
            return true;
        } else
            return false;
    }

    public void setEnderecoP(String cpf, String endereco) {
        pessoas.get(cpf).setEndereco(endereco);
    }

    public void setCartaoP(String cpf, Long cartao) {
        pessoas.get(cpf).setCartao(cartao);
    }

    public void setEmailP(String cpf, String email) {
        pessoas.get(cpf).setEmail(email);
    }

    public void setTelefoneP(String cpf, String telefone) {
        pessoas.get(cpf).setTelefone(telefone);
    }

    public void setProfissaoP(String cpf, String profissao) {
        Pessoa pessoa = pessoas.get(cpf);
        pessoa.setProfissao(profissao);
        if(pessoa.getSituacao().toString().equals("Não habilitado"))
            isHabilitado(pessoa);
    }

    public void setComorbidadesP(String cpf, Set<String> comorbidades) {
        Pessoa pessoa = pessoas.get(cpf);
        pessoa.setComorbidades(comorbidades);
        if (pessoa.getSituacao().toString().equals("Não habilitado"))
            isHabilitado(pessoa);
    }

    public String getStatus(String cpf) {
        return pessoas.get(cpf).getSituacao().toString();
    }
}
