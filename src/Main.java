import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static String gerarHash(String senha) {
        try {
            MessageDigest gerador = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = gerador.digest(senha.getBytes());

            StringBuilder builder = new StringBuilder();
            for (byte b : hashBytes) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin u = new Admin();
        Usuario[] usuarios = new Usuario[2];

        System.out.print("Login: ");
        String loginInput = scanner.nextLine();
        System.out.print("Senha: ");
        String senhaInput = scanner.nextLine();

        if (u.login.equals(loginInput) && u.senha.equals(senhaInput)) {
            System.out.println("\nLogin de administrador efetuado");
            System.out.print("Gostaria de cadastrar novos usuarios? s/n: ");
            String entrada = scanner.nextLine().toLowerCase();

            if (entrada.equals("s")) {

                for (int i = 0; i < 2; i++) {
                    System.out.println("\nCadastro do usuário " + (i + 1));
                    System.out.print("Login: ");
                    String loginCadastro = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaCadastro = scanner.nextLine();

                    usuarios[i] = new Usuario(loginCadastro, gerarHash(senhaCadastro));
                }

                System.out.println("\nUsuários cadastrados:");
                for (Usuario usr : usuarios) {
                    System.out.println("Login: " + usr.login + " | Senha (hash): " + usr.senhaHash);
                }

                System.out.print("\nLogar como usuario? s/n: ");
                String entrada2 = scanner.nextLine().toLowerCase();

                if (entrada2.equals("s")) {

                    System.out.print("\nLogin: ");
                    String loginInput2 = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaInput2 = scanner.nextLine();

                    boolean exist = false;
                    String hashInput = gerarHash(senhaInput2);

                    for (Usuario usr : usuarios) {
                        if (usr.login.equals(loginInput2) && usr.senhaHash.equals(hashInput)) {
                            System.out.printf("\nLogin efetuado com sucesso! Bem vindo %s\n", usr.login);
                            exist = true;
                            System.out.printf("Escreva uma mensagem e enviar para %s: ", usuarios[1].login);
                            String mensagem = scanner.nextLine();
                            String mensagemCript = gerarHash(mensagem);
                            System.out.print("\nDefina a senha para a mensagem: ");
                            String senhaMensagem = scanner.nextLine();

                            System.out.print("\nVoltar ao login? s/n: ");
                            String entrada3 = scanner.nextLine().toLowerCase();

                            if (entrada3.equals("s")) {
                                System.out.print("\nLogin: ");
                                String loginInput3 = scanner.nextLine();
                                System.out.print("Senha: ");
                                String senhaInput3 = scanner.nextLine();

                                boolean exist2 = false;
                                String hashInput2 = gerarHash(senhaInput3);

                                for (Usuario usr2 : usuarios) {
                                    if (usr2.login.equals(loginInput3) && usr2.senhaHash.equals(hashInput2)) {
                                        System.out.printf("\nLogin efetuado com sucesso! Bem vindo %s\n", usr2.login);
                                        exist2 = true;
                                        System.out.printf("O Usuario %s te enviou uma mensagem: \n", usr.login);
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
