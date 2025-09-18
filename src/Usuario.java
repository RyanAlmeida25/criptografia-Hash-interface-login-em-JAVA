public class Usuario {
    String login;
    String senhaHash;

    Usuario(String login, String senhaHash) {
        this.login = login;
        this.senhaHash = senhaHash;
    }

}
