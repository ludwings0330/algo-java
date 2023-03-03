package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 진단5 {

    static int n;
    static int[][] board;
    static int[] posA = new int[2];
    static int[] posB = new int[2];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][];

        for (int r = 0; r < n; r++) {
            board[r] = new int[n];
            final StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if (board[r][c] == 2) {
                    posA = new int[] { r, c };
                }
                if (board[r][c] == 3) {
                    posB = new int[] { r, c };
                }
            }
        }

        System.out.print(countBfs(posA));
        System.out.print(" ");
        System.out.print(countBfs(posB));
    }

    private static int countBfs(int[] pos) {
        int ret = 0;
        Deque<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        visited[pos[0]][pos[1]] = true;
        int[][] moves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        ret++;
        que.add(pos);
        while (!que.isEmpty()) {
            final int[] current = que.poll();

            for (int[] move :
                    moves) {
                int nr = current[0] + move[0];
                int nc = current[1] + move[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (board[nr][nc] == 0 || (board[nr][nc] != 1 && board[pos[0]][pos[1]] != board[nr][nc])) {
                    continue;
                }
                que.add(new int[] { nr, nc });
                visited[nr][nc] = true;
            }
        }
        return ret;
    }

}
