package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3197_백조의호수 {
    static int R, C;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][];
        for (int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {

            }
        }
    }
}
