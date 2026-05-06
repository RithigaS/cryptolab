import java.math.BigInteger;
import java.security.SecureRandom;

class RSAAlgorithm {

    BigInteger n, e, d;
    int bitlen = 512;

    // Key Generation
    RSAAlgorithm() {

        SecureRandom random = new SecureRandom();

        BigInteger p = BigInteger.probablePrime(bitlen, random);
        BigInteger q = BigInteger.probablePrime(bitlen, random);

        n = p.multiply(q);

        BigInteger phi =
                (p.subtract(BigInteger.ONE))
                .multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(65537);

        d = e.modInverse(phi);
    }

    // Encryption
    BigInteger encrypt(BigInteger msg) {
        return msg.modPow(e, n);
    }

    // Decryption
    BigInteger decrypt(BigInteger cipher) {
        return cipher.modPow(d, n);
    }

    public static void main(String[] args) {

        RSAAlgorithm rsa = new RSAAlgorithm();

        String text = "HELLO RSA";

        BigInteger message =
                new BigInteger(text.getBytes());

        // Encrypt
        BigInteger cipherText =
                rsa.encrypt(message);

        // Decrypt
        BigInteger decrypted =
                rsa.decrypt(cipherText);

        System.out.println("Plain Text  : " + text);

        System.out.println("Cipher Text : "
                + cipherText);

        System.out.println("Decrypted   : "
                + new String(decrypted.toByteArray()));
    }
}
