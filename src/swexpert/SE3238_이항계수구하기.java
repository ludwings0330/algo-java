package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SE3238_이항계수구하기 {
    static long n, r;
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

        while (r > 0 || n > 0) {
            int tn = (int) (n % p);
            int tr = (int) (r % p);

            if (tr > tn) return 0;

            ret = (ret * factorial[tn]) % p;
            for (int i = 0; i < p - 2; i++) {
                ret = (((ret * factorial[tn - tr]) % p) * factorial[tr]) % p;
            }
            n /= p;
            r /= p;
        }

        return ret;
    }

    private static void init() throws IOException {
        final StringTokenizer sb = new StringTokenizer(br.readLine());

        n = Long.parseLong(sb.nextToken());
        r = Long.parseLong(sb.nextToken());
        p = Integer.parseInt(sb.nextToken());

        factorial = new long[p + 1];
        factorial[0] = 1;

        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = (factorial[i - 1] * i) % p;
        }

    }
}
