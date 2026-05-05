import java.util.*;

public class PlayfairCipher {
    static char[][] matrix = new char[5][5];

    static void createMatrix(String key) {
        key = key.toUpperCase().replace("J", "I");
        String all = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String used = "";

        int index = 0;

        for (char ch : all.toCharArray()) {
            if (used.indexOf(ch) == -1 && ch >= 'A' && ch <= 'Z') {
                used += ch;

                // Fill 5x5 matrix
                matrix[index / 5][index % 5] = ch;
                index++;
            }
        }
    }

    static int[] findPosition(char ch) {
        if (ch == 'J') ch = 'I';

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            result += text.charAt(i);

            // Add X if same letters appear together
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                result += 'X';
            }
        }

        // Make length even
        if (result.length() % 2 != 0)
            result += 'X';

        return result;
    }

    static String encrypt(String text) {
        text = prepareText(text);
        String result = "";

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] p1 = findPosition(a);
            int[] p2 = findPosition(b);

            // Same row
            if (p1[0] == p2[0]) {
                result += matrix[p1[0]][(p1[1] + 1) % 5];
                result += matrix[p2[0]][(p2[1] + 1) % 5];
            }
            // Same column
            else if (p1[1] == p2[1]) {
                result += matrix[(p1[0] + 1) % 5][p1[1]];
                result += matrix[(p2[0] + 1) % 5][p2[1]];
            }
            // Rectangle rule
            else {
                result += matrix[p1[0]][p2[1]];
                result += matrix[p2[0]][p1[1]];
            }
        }
        return result;
    }

    static String decrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] p1 = findPosition(a);
            int[] p2 = findPosition(b);

            // Same row
            if (p1[0] == p2[0]) {
                result += matrix[p1[0]][(p1[1] + 4) % 5];
                result += matrix[p2[0]][(p2[1] + 4) % 5];
            }
            // Same column
            else if (p1[1] == p2[1]) {
                result += matrix[(p1[0] + 4) % 5][p1[1]];
                result += matrix[(p2[0] + 4) % 5][p2[1]];
            }
            // Rectangle rule
            else {
                result += matrix[p1[0]][p2[1]];
                result += matrix[p2[0]][p1[1]];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        createMatrix(key);

        String encrypted = encrypt(text);
        String decrypted = decrypt(encrypted);

        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
