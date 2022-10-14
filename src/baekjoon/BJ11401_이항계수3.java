package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11401_이항계수3 {
    static long N, K, P;
    static long[] fac;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        P = 1_000_000_000 + 7;

        fac = new long[(int) N + 1];
        fac[0] = 1;

        for (int i = 1; i <= N; i++) {
            fac[i] = (fac[i - 1] * i) % P;
        }
        System.out.println(fac[(int) N] % P * pow(fac[(int) (N - K)], P - 2) % P * pow(fac[(int) K], P - 2) % P);

    }

    public static long pow(long base, long exp) {
        if (exp == 1)
            return base % P;

        long tmp = pow(base, exp / 2);

        if (exp % 2 == 1)
            return ((tmp * tmp) % P) * base % P;

        return tmp * tmp % P;
    }
}
