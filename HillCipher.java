class HillCipher {

    // 3x3 key matrix
    static int[][] keymat = {
        {1, 2, 1},
        {2, 3, 2},
        {2, 2, 1}
    };

    // inverse key matrix
    static int[][] invkeymat = {
        {-1, 0, 1},
        {2, -1, 0},
        {-2, 2, -1}
    };

    static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static String encode(char a, char b, char c) {
        int p1 = a - 'A', p2 = b - 'A', p3 = c - 'A';

        int x = p1 * keymat[0][0] + p2 * keymat[1][0] + p3 * keymat[2][0];
        int y = p1 * keymat[0][1] + p2 * keymat[1][1] + p3 * keymat[2][1];
        int z = p1 * keymat[0][2] + p2 * keymat[1][2] + p3 * keymat[2][2];

        return "" + key.charAt(x % 26) + key.charAt(y % 26) + key.charAt(z % 26);
    }

    static String decode(char a, char b, char c) {
        int p1 = a - 'A', p2 = b - 'A', p3 = c - 'A';

        int x = p1 * invkeymat[0][0] + p2 * invkeymat[1][0] + p3 * invkeymat[2][0];
        int y = p1 * invkeymat[0][1] + p2 * invkeymat[1][1] + p3 * invkeymat[2][1];
        int z = p1 * invkeymat[0][2] + p2 * invkeymat[1][2] + p3 * invkeymat[2][2];

        return "" + key.charAt((x % 26 + 26) % 26)
                  + key.charAt((y % 26 + 26) % 26)
                  + key.charAt((z % 26 + 26) % 26);
    }

    public static void main(String[] args) {
        String msg = "SecurityLaboratory";
        String enc = "", dec = "";

        System.out.println("Simulation of Hill Cipher");
        System.out.println("--------------------------");
        System.out.println("Input message : " + msg);

        msg = msg.toUpperCase().replaceAll("\\s", "");

        // Padding with X if length is not multiple of 3
        while (msg.length() % 3 != 0) {
            msg += "X";
        }

        System.out.println("Padded message : " + msg);

        for (int i = 0; i < msg.length(); i += 3) {
            enc += encode(msg.charAt(i), msg.charAt(i + 1), msg.charAt(i + 2));
        }

        System.out.println("Encoded message : " + enc);

        for (int i = 0; i < enc.length(); i += 3) {
            dec += decode(enc.charAt(i), enc.charAt(i + 1), enc.charAt(i + 2));
        }

        System.out.println("Decoded message : " + dec);
    }
}
