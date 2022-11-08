package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ2210_숫자판점프 {
    static char[][] board;
    static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Set<String> answer = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];
        for (int r = 0; r < 5; r++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());

            for (int c = 0; c < 5; c++) {
                board[r][c] = st.nextToken().charAt(0);
            }
        }

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                dfs(r, c, 0);
            }
        }

        System.out.println(answer.size());
    }

    private static void dfs(int r, int c, int cnt) {
        if (cnt == 6) {
            answer.add(sb.toString());
            return;
        }
        sb.append(board[r][c]);
        for (var move :
                moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
                continue;
            }
            dfs(nr, nc, cnt + 1);
        }
        sb.deleteCharAt(sb.length() - 1);
    }
}
