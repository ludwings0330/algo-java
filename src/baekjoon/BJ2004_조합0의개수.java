package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * nCm = n-1Cm-1 + n-1Cm
 */
public class BJ2004_조합0의개수 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());
        long t2 = 0;
        long t5 = 0;
        for (long i = 2; i <= N; i *= 2) {
            t2 += N / i;
        }
        for (long i = 5; i <= N; i *= 5) {
            t5 += N / i;
        }
        for (long i = 2; i <= M; i *= 2) {
            t2 -= M / i;
        }
        for (long i = 5; i <= M; i *= 5) {
            t5 -= M / i;
        }
        for (long i = 2; i <= N - M; i *= 2) {
            t2 -= (N - M) / i;
        }
        for (long i = 5; i <= N - M; i *= 5) {
            t5 -= (N - M) / i;
        }

        System.out.println((t2 > t5) ? t5 : t2);
    }

}
