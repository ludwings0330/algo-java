package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1563_개근상 {

    // 지각 두 번 이상, 결석 세 번 연속
    // 출석 : O , 지각 : L, 결석 : A
    static int MOD = 1_000_000;
    static int cache[][][] = new int[2][3][1000];
    static int N;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 1 <= N <= 1,000

        System.out.println(recursiveSolve(0, 0, 0));
    }

    private static int recursiveSolve(int l, int a, int n) {
        if (n == N) {
            return 1;
        }

        if (cache[l][a][n] != -1) {
            return cache[l][a][n];
        }

        cache[l][a][n] = 0;

        // 1. 오늘 출석
        // 오늘 출석했으므로 연속 결석 깨짐
        cache[l][a][n] += recursiveSolve(l, 0, n + 1);

        // 2. 오늘 결석 (오늘 결석하더라도 연속 결석 3번 미만)
        if (a + 1 < 3) {
            cache[l][a][n] = (cache[l][a][n] + recursiveSolve(l, a + 1, n + 1)) % MOD;
        }

        // 3. 오늘 지각 (오늘 지각하더라도 전체 지각 2번 미만)
        if (l + 1 < 2) {
            // 오늘 지각이므로 연속 결석 깨짐
            cache[l][a][n] = (cache[l][a][n] + recursiveSolve(l + 1, 0, n + 1)) % MOD;
        }

        return cache[l][a][n];
    }

}
