import java.security.MessageDigest;

class SHA1Hashing {

    // Generate SHA-1 Hash
    static String generateSHA1(String text) {

        try {
            MessageDigest md =
                    MessageDigest.getInstance("SHA-1");

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

        String hash = generateSHA1(text);

        System.out.println("Original Text : " + text);

        System.out.println("SHA-1 Hash    : " + hash);
    }
}
