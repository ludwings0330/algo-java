package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ1926_그림 {

    static int R, C;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        init();
        int[] ret = solve();

        System.out.println(ret[0]);
        System.out.println(ret[1]);
    }

    private static int[] solve() {
        int[] ret = new int[2];

        boolean[][] visited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (visited[r][c] || board[r][c] == 0) {
                    continue;
                }
                ret[0]++;
                ret[1] = Math.max(ret[1], dfs(r, c, visited));
            }
        }

        return ret;
    }

    private static int dfs(int r, int c, boolean[][] visited) {
        Stack<Integer[]> stack = new Stack<>();
        stack.push(new Integer[] { r, c });
        int[][] moves = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        int count = 0;
        visited[r][c] = true;

        while (!stack.isEmpty()) {
            count++;
            Integer[] pos = stack.pop();
            int cr = pos[0];
            int cc = pos[1];

            for (int[] move :
                    moves) {
                int nr = cr + move[0];
                int nc = cc + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }

                if (visited[nr][nc] || board[nr][nc] == 0) {
                    continue;
                }

                visited[nr][nc] = true;
                stack.push(new Integer[] { nr, nc });
            }

        }

        return count;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][];

        for (int r = 0; r < R; r++) {
            board[r] = new int[C];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
