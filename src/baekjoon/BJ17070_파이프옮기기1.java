package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17070_파이프옮기기1 {
    static int[][] board;
    static long[][][] cache;
    static int[][][] moves;
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static {
        moves = new int[3][][];
        moves[0] = new int[][]{{0, 1}, {0, 0}, {1, 1}};
        moves[1] = new int[][]{{0, 0}, {1, 0}, {1, 1}};
        moves[2] = new int[][]{{0, 1}, {1, 0}, {1, 1}};
    }

    public static long solve1() throws IOException {
        return recursiveSolve(0, 1, 0);

    }

    public static void main(String[] args) throws IOException {
        input();
//        System.out.println(solve1());
        System.out.println(solve2());
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        cache = new long[N][N][3];
        board = new int[N][];
        for (int r = 0; r < N; r++) {
            board[r] = new int[N];
            final StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(stringTokenizer.nextToken());
                Arrays.fill(cache[r][c], -1);
            }
        }
    }

    private static long solve2() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                Arrays.fill(cache[r][c], 0);
            }
        }
        cache[0][1][0] = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!(r - 1 < 0 || c - 1 < 0) &&
                        (board[r][c] == 0 && board[r - 1][c] == 0 && board[r][c - 1] == 0)) {
                    cache[r][c][2] += cache[r - 1][c - 1][0] + cache[r - 1][c - 1][1] + cache[r - 1][c - 1][2];
                }

                if (board[r][c] == 0) {
                    if (c - 1 >= 0)
                        cache[r][c][0] += cache[r][c - 1][0] + cache[r][c - 1][2];
                    if (r - 1 >= 0)
                        cache[r][c][1] += cache[r - 1][c][1] + cache[r - 1][c][2];
                }
            }
        }

        return Arrays.stream(cache[N - 1][N - 1]).sum();
    }

    private static long recursiveSolve(int r, int c, int type) {
        if (r == N - 1 && c == N - 1) {
            return 1;
        }
        if (cache[r][c][type] != -1)
            return cache[r][c][type];

        cache[r][c][type] = 0;

        for (int nextType = 0; nextType < moves[type].length; nextType++) {
            int dr = moves[type][nextType][0];
            int dc = moves[type][nextType][1];
            if (dr == dc && dr == 0)
                continue;
            int nr = r + dr;
            int nc = c + dc;
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;
            if (board[nr][nc] == 1)
                continue;
            if (nextType == 2)
                if (board[nr - 1][nc] == 1 || board[nr][nc - 1] == 1)
                    continue;

            cache[r][c][type] += recursiveSolve(nr, nc, nextType);
        }

        return cache[r][c][type];
    }
}
