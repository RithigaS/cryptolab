import java.util.*;

public class CaesarCipher {
    public static String encrypt(String text, int key) {
        String result = "";

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';

                // Shift character by key
                result += (char) ((ch - base + key) % 26 + base);
            } else {
                result += ch; // Keep spaces/symbols same
            }
        }
        return result;
    }

    public static String decrypt(String text, int key) {
        // Reverse shift for decryption
        return encrypt(text, 26 - key);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter key: ");
        int key = sc.nextInt();

        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
