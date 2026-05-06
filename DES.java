import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

class DESAlgorithm {

    // Generate DES key
    static SecretKey generateKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56); // DES uses 56-bit key
        return kg.generateKey();
    }

    // Encryption
    static String encrypt(String text, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decryption
    static String decrypt(String cipherText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decrypted = cipher.doFinal(
                Base64.getDecoder().decode(cipherText));

        return new String(decrypted);
    }

    public static void main(String[] args) {
        try {
            String text = "HELLODES";

            SecretKey key = generateKey();

            String cipherText = encrypt(text, key);

            String plainText = decrypt(cipherText, key);

            System.out.println("Plain Text  : " + text);
            System.out.println("Cipher Text : " + cipherText);
            System.out.println("Decrypted   : " + plainText);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
