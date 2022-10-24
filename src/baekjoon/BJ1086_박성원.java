package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1086_박성원 {
    static int N, K;
    static long[][] cache;
    static String[] num;
    static int[][] remainderCache;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new String[N];

        for (int i = 0; i < N; i++) {
            num[i] = br.readLine();
        }

        K = Integer.parseInt(br.readLine());
        cache = new long[(int) Math.pow(2, N)][K + 1];
        remainderCache = new int[N][K + 1];

        for (int i = 0; i < Math.pow(2, N); i++) {
            Arrays.fill(cache[i], -1);
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(remainderCache[i], -1);
        }
        long total = factorial(N);
        solve(0, 0, 0);
        long cnt = cache[0][0];
        if (cnt == 0)
            System.out.println("0/1");
        else {
            long k = gcd(total, cnt);
            System.out.println(cnt / k + "/" + total / k);
        }
    }

    public static long factorial(int n) {
        long ret = 1;

        for (long i = 1; i <= n; i++) {
            ret *= i;
        }

        return ret;
    }

    public static long gcd(long a, long b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }

    public static long solve(int visited, int m, int n) {
        if (n == N) {
            if (m == 0) return 1;
            else return 0;
        }
        if (cache[visited][m] != -1)
            return cache[visited][m];

        cache[visited][m] = 0;

        for (int i = 0; i < N; i++) {
            // 이미 방문했다면 continue;
            if ((visited & (1 << i)) > 0)
                continue;
            // i 번째를 이번에 추가했을때의 나머지 nm
            int nm = m;

            nm = getRemainder(i, m);
            cache[visited][m] += solve(visited | (1 << i), nm, n + 1);
        }

        return cache[visited][m];
    }

    private static int getRemainder(int i, int m) {
        if (remainderCache[i][m] != -1)
            return remainderCache[i][m];

        remainderCache[i][m] = m;
        for (int j = 0; j < num[i].length(); j++) {
            remainderCache[i][m] *= 10;
            remainderCache[i][m] %= K;
            remainderCache[i][m] += num[i].charAt(j) - '0';
            remainderCache[i][m] %= K;
        }

        return remainderCache[i][m];
    }
}