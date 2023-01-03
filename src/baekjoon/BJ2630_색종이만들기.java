package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2630_색종이만들기 {
    static int[][] board = null;
    static int N = 0;
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N][];
        for (int r = 0; r < N; r++) {
            board[r] = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // white 0
        // blue 1
        recursive(0, 0, N);
        sb.append(white).append('\n').append(blue);
        System.out.println(sb.toString());
    }

    static void recursive(int r, int c, int l) {
        if (l == 1) {
            if (board[r][c] == 0) white++;
            else blue++;

            return;
        }
        int root = board[r][c];
        for (int rr = r; rr < r + l; rr++) {
            for (int cc = c; cc < c + l; cc++) {
                if (board[rr][cc] != root) {
                    int ll = l / 2;
                    recursive(r, c, ll);
                    recursive(r, c + ll, ll);
                    recursive(r + ll, c, ll);
                    recursive(r + ll, c + ll, ll);
                    return;
                }
            }
        }
        if (root == 0) white++;
        else blue++;
    }
}
