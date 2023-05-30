package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 로봇의 이동 경로가 단순할 확률 동(E), 서(W), 북(N), 남(S)
 */
public class BJ1405_미친로봇 {

    // N <= 14
    static boolean[][] visited;
    static int N;
    static double[] possibility;
    static int[][] moves = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    public static void main(String[] args) throws IOException {
        init();
        double answer = recursiveSolve(0, 15, 15);
        System.out.println(answer);
    }

    private static double recursiveSolve(int n, int r, int c) {
        if (n != 0 && visited[r][c]) {
            return 0;
        }

        if (n == N) {
            return 1;
        }
        visited[r][c] = true;

        double ret = 0;
        for (int i = 0; i < 4; i++) {
            ret += recursiveSolve(n + 1, r + moves[i][0], c + moves[i][1]) * possibility[i];
        }

        visited[r][c] = false;
        return ret;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        possibility = new double[4];
        visited = new boolean[31][31];
        visited[15][15] = true;

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            possibility[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }
    }

}
