import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
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

}
