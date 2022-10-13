package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SE5607_이항계수 {
    static int n, r;
    static int p;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long[] factorial;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            sb.append("#").append(tc).append(" ").append(solve()).append('\n');
        }
        System.out.println(sb.toString());
    }

    private static long solve() throws IOException {
        init();

        long ret = 1;

        ret = (((factorial[n] * pow(factorial[n - r], p - 2)) % p) * pow(factorial[r], p - 2)) % p;

        return ret;
    }

    private static void init() throws IOException {
        final StringTokenizer sb = new StringTokenizer(br.readLine());

        n = Integer.parseInt(sb.nextToken());
        r = Integer.parseInt(sb.nextToken());
        p = 1234567891;

        factorial = new long[n + 1];
        factorial[0] = 1;

        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = (factorial[i - 1] * i) % p;
        }
    }

    private static long pow(long base, long x) {
        if (x == 1)
            return base % p;
        if (x == 0)
            return 0;
        long temp = pow(base, x / 2);
        if (x % 2 == 1)
            return ((temp * temp) % p) * base % p;

        return (temp * temp) % p;
    }
}
