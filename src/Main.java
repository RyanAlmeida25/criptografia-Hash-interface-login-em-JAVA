import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[] credenciais = Login.capturarCredenciais(scanner);
        String loginInput = credenciais[0];
        String senhaInput = credenciais[1];

        Admin admin = new Admin();
        Usuario[] usuarios = new Usuario[2];


        if (admin.login.equals(loginInput) && admin.senha.equals(senhaInput)) {
            System.out.println("\nLogin de administrador efetuado");
            System.out.print("Gostaria de cadastrar novos usuários? s/n: ");
            String entrada = scanner.nextLine().toLowerCase();

            if (entrada.equals("s")) {
                for (int i = 0; i < 2; i++) {
                    System.out.println("\nCadastro do usuário " + (i + 1));
                    System.out.print("Login: ");
                    String loginCadastro = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaCadastro = scanner.nextLine();

                    usuarios[i] = new Usuario(loginCadastro, Criptografia.gerarHash(senhaCadastro));
                }

                System.out.println("\nUsuários cadastrados:");
                for (Usuario usr : usuarios) {
                    System.out.println("Login: " + usr.login + " | Senha (hash): " + usr.senhaHash);
                }

                System.out.print("\nDeseja logar como usuário? s/n: ");
                String entrada2 = scanner.nextLine().toLowerCase();

                if (entrada2.equals("s")) {
                    String[] credenciaisUsuario = Login.capturarCredenciais(scanner);
                    String loginUser = credenciaisUsuario[0];
                    String senhaUser = credenciaisUsuario[1];
                    String hashInput = Criptografia.gerarHash(senhaUser);

                    boolean exist = false;
                    for (Usuario usr : usuarios) {
                        if (usr.login.equals(loginUser) && usr.senhaHash.equals(hashInput)) {
                            System.out.printf("\nLogin efetuado com sucesso! Bem-vindo %s\n", usr.login);
                            exist = true;

                            System.out.printf("Escreva uma mensagem para %s: ", usuarios[1].login);
                            String mensagem = scanner.nextLine();
                            String mensagemCript = Criptografia.gerarHash(mensagem);

                            System.out.print("\nDefina a senha para a mensagem: ");
                            String senhaMensagem = scanner.nextLine();

                            System.out.print("\nDeseja voltar ao login? s/n: ");
                            String entrada3 = scanner.nextLine().toLowerCase();

                            if (entrada3.equals("s")) {
                                String[] credenciaisUsuario2 = Login.capturarCredenciais(scanner);
                                String loginUser2 = credenciaisUsuario2[0];
                                String senhaUser2 = credenciaisUsuario2[1];
                                String hashInput2 = Criptografia.gerarHash(senhaUser2);

                                for (Usuario usr2 : usuarios) {
                                    if (usr2.login.equals(loginUser2) && usr2.senhaHash.equals(hashInput2)) {
                                        System.out.printf("\nLogin efetuado com sucesso! Bem-vindo %s\n", usr2.login);
                                        System.out.printf("O usuário %s te enviou uma mensagem:\n", usr.login);
                                        System.out.println(mensagemCript);

                                        System.out.print("\nDigite a senha para descriptografar: ");
                                        String entradaSenha = scanner.nextLine();

                                        if (entradaSenha.equals(senhaMensagem)) {
                                            System.out.println("Senha correta!");
                                            System.out.printf("Exibindo mensagem: %s\n", mensagem);
                                        } else {
                                            System.out.println("Senha incorreta.");
                                        }
                                    }
                                }
                            } else {
                                System.out.println("\nSaindo...");
                            }
                        }
                    }
                    if (!exist) {
                        System.out.println("\nLogin ou senha incorretos.");
                    }
                } else {
                    System.out.println("\nSaindo...");
                }
            } else {
                System.out.println("\nSaindo...");
            }
        } else {
            System.out.println("\nErro: login ou senha inválidos");
        }

        scanner.close();
    }
}
