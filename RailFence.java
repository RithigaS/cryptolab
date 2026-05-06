class RailFenceCipher {

    // Encryption
    public static String encrypt(String text, int depth) {
        char[][] rail = new char[depth][text.length()];

        int row = 0;
        int direction = 1;

        // Fill letters in zigzag pattern
        for (int i = 0; i < text.length(); i++) {
            rail[row][i] = text.charAt(i);

            row += direction;

            if (row == 0 || row == depth - 1) {
                direction *= -1;
            }
        }

        // Read row by row
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] != 0) {
                    cipherText.append(rail[i][j]);
                }
            }
        }

        return cipherText.toString();
    }

    // Decryption
    public static String decrypt(String cipherText, int depth) {
        char[][] rail = new char[depth][cipherText.length()];

        int row = 0;
        int direction = 1;

        // Mark zigzag places with '*'
        for (int i = 0; i < cipherText.length(); i++) {
            rail[row][i] = '*';

            row += direction;

            if (row == 0 || row == depth - 1) {
                direction *= -1;
            }
        }

        // Fill ciphertext in marked places
        int index = 0;

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < cipherText.length(); j++) {
                if (rail[i][j] == '*') {
                    rail[i][j] = cipherText.charAt(index++);
                }
            }
        }

        // Read again in zigzag to get plaintext
        StringBuilder plainText = new StringBuilder();

        row = 0;
        direction = 1;

        for (int i = 0; i < cipherText.length(); i++) {
            plainText.append(rail[row][i]);

            row += direction;

            if (row == 0 || row == depth - 1) {
                direction *= -1;
            }
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        String text = "HELLO RAIL FENCE".replace(" ", "");
        int depth = 3;

        String cipherText = encrypt(text, depth);
        String decryptedText = decrypt(cipherText, depth);

        System.out.println("Plaintext      : " + text);
        System.out.println("Ciphertext     : " + cipherText);
        System.out.println("Decrypted Text : " + decryptedText);
    }
}
