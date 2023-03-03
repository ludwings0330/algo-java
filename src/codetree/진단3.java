package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 진단3 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];

        for (int r = 0; r < n; r++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;

        for (int rr = 0; rr <= r; rr++) {
            for (int cc = 0; cc <= c; cc++) {
                if (board[rr][cc] < board[r][c]) {
                    board[rr][cc] = 0;
                }
            }
        }

        for (int rr = 0; rr < n; rr++) {
            for (int cc = 0; cc < n; cc++) {
                System.out.print(board[rr][cc] + " ");
            }
            System.out.println();
        }
    }

}
