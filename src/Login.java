import java.util.Scanner;

public class Login {


    public static String[] capturarCredenciais(Scanner scanner) {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new String[]{ login, senha };
    }
}

