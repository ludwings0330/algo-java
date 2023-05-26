package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12869_뮤탈리스크 {

    static int N;
    static int[] scvs;
    static int[][][] cache;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        return recursiveSolve(scvs[2], scvs[1], scvs[0]);
    }

    // 체력 a, b, c 일때
    private static int recursiveSolve(int a, int b, int c) {

        if (a <= 0 && b <= 0 && c <= 0) {
            return 0;
        }

        a = Math.max(a, 0);
        b = Math.max(b, 0);
        c = Math.max(c, 0);

        if (cache[a][b][c] != Integer.MAX_VALUE) {
            return cache[a][b][c];
        }

        for (int i = 1; i <= 9; i *= 3) {
            for (int j = 1; j <= 9; j *= 3) {
                for (int k = 1; k <= 9; k *= 3) {
                    if (i != j && j != k && k != i) {
                        cache[a][b][c] = Math.min(cache[a][b][c], recursiveSolve(a - i, b - j, c - k) + 1);
                    }
                }
            }
        }

        // 총 6 가지 경우의 수

        return cache[a][b][c];
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scvs = new int[3];

        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scvs[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(scvs);

        cache = new int[61][61][61];
        for (int a = 0; a < 61; a++) {
            for (int b = 0; b < 61; b++) {
                Arrays.fill(cache[a][b], Integer.MAX_VALUE);
            }
        }
    }

}
