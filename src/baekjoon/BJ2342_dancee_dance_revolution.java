package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2342_dancee_dance_revolution {

    static int[][][] cache = new int[5][5][100_010];
    static int[] instructions = new int[100_010];

    public static void main(String[] args) throws IOException {
        // 1, 2, 3, 4 -> 위, 왼, 아래, 오른
        init();
        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        return recursiveSolve(0, 0, 0);
    }

    private static int recursiveSolve(int l, int r, int idx) {
        if (idx != 0 && l == r) {
            return Integer.MAX_VALUE / 3;
        }

        if (instructions[idx] == 0) {
            return 0;
        }

        if (cache[l][r][idx] != -1) {
            return cache[l][r][idx];
        }

        cache[l][r][idx] = Integer.MAX_VALUE / 3;
        int next = instructions[idx];
        // 바로그자리 - 1점, 중앙에서다른위치 - 2점, 임점위치 - 3점, 반대위치 4점
        // 1. 왼발을 움직인다.
        int score = 0;
        if (l == next) {
            // 바로그자리
            score = 1;
        } else if (l == 0) {
            // 중앙에서 다른위치
            score = 2;
        } else if (Math.abs(l - next) == 2) {
            // 반대위치
            score = 4;
        } else {
            // 인접위치
            score = 3;
        }
        cache[l][r][idx] = Math.min(cache[l][r][idx], score + recursiveSolve(next, r, idx + 1));

        // 2. 오른발을 움직인다.
        if (r == next) {
            // 바로그자리
            score = 1;
        } else if (r == 0) {
            // 중앙에서 다른위치
            score = 2;
        } else if (Math.abs(r - next) == 2) {
            // 반대위치
            score = 4;
        } else {
            // 인접위치
            score = 3;
        }
        cache[l][r][idx] = Math.min(cache[l][r][idx], score + recursiveSolve(l, next, idx + 1));

        return cache[l][r][idx];
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        int l = 0;

        while (st.hasMoreTokens()) {
            instructions[l++] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
    }

}
