package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1006_습격자초라기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, W;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            System.out.println(solve());
        }
    }

    private static int solve() throws IOException {
        int ret = 0;
        init();

        return ret;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new int[2][];

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            board[i] = new int[N];
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
