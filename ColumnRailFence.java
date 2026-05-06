class RailFence {

    static String encrypt(String text, int key) {
        StringBuilder[] rail = new StringBuilder[key];

        for (int i = 0; i < key; i++)
            rail[i] = new StringBuilder();

        int row = 0, dir = 1;

        for (char ch : text.toCharArray()) {
            rail[row].append(ch);

            if (row == 0)
                dir = 1;
            else if (row == key - 1)
                dir = -1;

            row += dir;
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder r : rail)
            result.append(r);

        return result.toString();
    }

    static String decrypt(String cipher, int key) {
        char[][] rail = new char[key][cipher.length()];

        int row = 0, dir = 1;

        // mark zigzag places
        for (int i = 0; i < cipher.length(); i++) {
            rail[row][i] = '*';

            if (row == 0)
                dir = 1;
            else if (row == key - 1)
                dir = -1;

            row += dir;
        }

        // fill cipher text
        int index = 0;

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*') {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        // read zigzag
        StringBuilder result = new StringBuilder();

        row = 0;
        dir = 1;

        for (int i = 0; i < cipher.length(); i++) {
            result.append(rail[row][i]);

            if (row == 0)
                dir = 1;
            else if (row == key - 1)
                dir = -1;

            row += dir;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String text = "attack at once";
        int key = 2;

        String cipher = encrypt(text, key);
        String plain = decrypt(cipher, key);

        System.out.println("Plain Text  : " + text);
        System.out.println("Cipher Text : " + cipher);
        System.out.println("Decrypted   : " + plain);
    }
}
