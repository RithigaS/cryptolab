import java.math.BigInteger;
import java.security.SecureRandom;

class DiffieHellman {

    static final int BIT_LENGTH = 512;

    public static void main(String[] args) {

        SecureRandom random = new SecureRandom();

        // Public values
        BigInteger p =
                BigInteger.probablePrime(BIT_LENGTH, random);

        BigInteger g =
                BigInteger.probablePrime(256, random);

        // Alice private & public key
        BigInteger a =
                new BigInteger(BIT_LENGTH, random);

        BigInteger A =
                g.modPow(a, p);

        // Bob private & public key
        BigInteger b =
                new BigInteger(BIT_LENGTH, random);

        BigInteger B =
                g.modPow(b, p);

        // Shared Secret Keys
        BigInteger aliceKey =
                B.modPow(a, p);

        BigInteger bobKey =
                A.modPow(b, p);

        // Output
        System.out.println("Public Prime (p) : " + p);

        System.out.println("Public Base (g)  : " + g);

        System.out.println("Alice Public Key : " + A);

        System.out.println("Bob Public Key   : " + B);

        System.out.println("Alice Shared Key : " + aliceKey);

        System.out.println("Bob Shared Key   : " + bobKey);

        System.out.println("Keys Match       : "
                + aliceKey.equals(bobKey));
    }
}
