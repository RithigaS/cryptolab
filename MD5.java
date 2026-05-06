import java.security.MessageDigest;

class MD5Hashing {

    // Generate MD5 Hash
    static String generateMD5(String text) {

        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");

            byte[] hash =
                    md.digest(text.getBytes());

            StringBuilder sb =
                    new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {

        String text = "Hello World";

        String hash = generateMD5(text);

        System.out.println("Original Text : " + text);

        System.out.println("MD5 Hash      : " + hash);
    }
}
