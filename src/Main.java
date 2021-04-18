import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private static Governo governo;

    public static void main(String[] args) {
        governo = new Governo();
        Scanner sc = new Scanner(System.in);
        boolean controle = true;
        while (controle) {
            System.out.println(menu());

            switch (sc.nextLine().toLowerCase()) {
                case "c":
                    System.out.println("""
                            Envie os dados da pessoa na respectiva ordem, nome completo, CPF, endereco,
                            numero do cartao do SUS, e-mail, telefone, profissao,
                            comorbidades(comorbidade1, comorbidade2, ..., comorbidaden) e data de nascimento(yyyy-MM-dd)""");
                    String nome = sc.nextLine();
                    String cpf = sc.nextLine();
                    String endereco = sc.nextLine();
                    Long cartao = sc.nextLong();
                    sc.nextLine();
                    String email = sc.nextLine();
                    String telefone = sc.nextLine();
                    String profissao = sc.nextLine();
                    HashSet<String> comorbidades = new HashSet<>(Arrays.asList(sc.nextLine().split(", ")));
                    LocalDate nascimento = LocalDate.parse(sc.nextLine());
                    governo.cadastraPessoa(nome, cpf, endereco, cartao, email, telefone, profissao, comorbidades, nascimento);
                    break;

                case "a":
                    System.out.println("Digite o cpf");
                    cpf = sc.nextLine();
                    System.out.println("O que gostaria de alterar?(endereco(E) numero do cartao do SUS(S), e-mail(M), " +
                            "\ntelefone(T), profissao(P), comorbidades(B)");
                    String aux = sc.nextLine().toLowerCase();
                    System.out.println("Digite o dado atualizado");

                    switch (aux) {
                        case "e" -> governo.setEnderecoP(cpf, sc.nextLine());
                        case "s" -> {
                            governo.setCartaoP(cpf, sc.nextLong());
                            sc.nextLine();
                        }
                        case "m" -> governo.setEmailP(cpf, sc.nextLine());
                        case "t" -> governo.setTelefoneP(cpf, sc.nextLine());
                        case "p" -> governo.setProfissaoP(cpf, sc.nextLine());
                        case "b" -> governo.setComorbidadesP(cpf, new HashSet<>(Arrays.asList(sc.nextLine().split(", "))));
                        default -> System.out.println("Entrada invalida");
                    }
                    break;

                case "s":
                    System.out.println("Digite o cpf");
                    System.out.println(governo.getStatus(sc.nextLine()));
                    break;

                case "g":
                    System.out.println("O que gostaria de alterar?(comorbidades(C), profissoes(P), idade minima(D))");
                    aux = sc.nextLine().toLowerCase();
                    System.out.println("Digite o dado atualizado");
                    switch (aux) {
                        case "c" -> governo.setComorbidades(new HashSet<>(Arrays.asList(sc.nextLine().split(", "))));
                        case "p" -> governo.setProfissoes(new HashSet<>(Arrays.asList(sc.nextLine().split(", "))));
                        case "d" -> {
                            governo.setIdadeMinima(sc.nextInt());
                            sc.nextLine();
                        }
                        default -> System.out.println("Entrada invalida");
                    } break;

                case "v":
                    System.out.println("Digite o cpf");
                    if(!governo.tomaVacina(sc.nextLine()))
                        System.out.println("Pessoa não habilitada para tomar a vacina");
                    break;

                case "p":
                    System.out.println("Digite o cpf");
                    if(!governo.passa20Dias(sc.nextLine()))
                        System.out.println("Pessoa não tomou a primeira dose ainda");
                    break;

                case "x":
                    controle = false;
                    break;
            }
        }
    }

    private static String menu() {
        return """
                Cadastrar pessoa(C)
                Alterar dados de uma pessoa(A)
                Consultar status(S)
                Atualizar condicoes para atualizar para a primeira dose(G)
                Cadastrar vacina(V)
                Passar 20 dias(P)
                Sair(X)""";
    }
}
