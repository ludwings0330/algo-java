package cheat;

// 최대 공약수
// gcd(A, B) = gcd(B, A%B)
public class GCD {
    public static void main(String[] args) {
        int a = 100;
        int b = 12;

        System.out.println(gcd(a, b));
    }

    private static int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }
}
