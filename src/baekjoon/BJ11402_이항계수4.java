package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11402_이항계수4 {
    static long N, R, P;
    static long[] fac;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        R = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        fac = new long[(int) P + 1];
        fac[0] = 1;
        for (int i = 1; i <= P; i++) {
            fac[i] = fac[i - 1] * i % P;
        }
        long answer = 1;
        while (N > 0 || R > 0) {
            int tn = (int) (N % P);
            int tr = (int) (R % P);
            if (tr > tn) {
                answer = 0;
                break;
            }
            answer = answer * fac[tn] % P;
            for (int i = 0; i < P - 2; i++) {
                answer = answer * fac[tn - tr] % P * fac[tr] % P;
            }

            N /= P;
            R /= P;
        }

        System.out.println(answer);
    }
}
