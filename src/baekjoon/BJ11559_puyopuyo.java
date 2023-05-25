package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BJ11559_puyopuyo {

    static int R = 12;
    static int C = 6;

    static char[][] board;

    public static void main(String[] args) throws IOException {
        // R = 12, C = 6
        init();
        int answer = solve();

        System.out.println(answer);
    }

    private static int solve() {
        int ret = -1;
        boolean changed = true;

        while (changed) {
            changed = deletePuyos();
            moveDown();
            ret++;
        }

        return ret;
    }

    private static void moveDown() {
        for (int c = 0; c < C; c++) {
            for (int r = 1; r < R; r++) {
                if (board[r][c] == '.') {
                    continue;
                }

                int rr = r;

                while (rr > 0 && board[rr - 1][c] == '.') { // 아래로 내려갈 공간이 있는한 계속 내려간다.
                    board[rr - 1][c] = board[rr][c];
                    board[rr][c] = '.';
                    rr--;
                }
            }
        }
    }

    private static boolean deletePuyos() {
        boolean changed = false;
        boolean[][] visited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 아직 처리하지 않았고, 빈칸이 아니라면 처리시작
                if (!visited[r][c] && board[r][c] != '.' && shouldDelete(visited, r, c)) {
                    changed = true;
                }
            }
        }

        return changed;
    }

    /**
     * 주변에 4개이상 연결되어있다면 연결된 모든 요소들이 제거된다.
     *
     * @param visited
     * @param r
     * @param c
     * @return
     */
    private static boolean shouldDelete(boolean[][] visited, int r, int c) {
        boolean deleted = false;
        int[][] moves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        LinkedList<Integer[]> stack = new LinkedList<>();
        LinkedList<Integer[]> candidate = new LinkedList<>();

        visited[r][c] = true;
        stack.push(new Integer[] { r, c });
        candidate.push(new Integer[] { r, c });
        char baseChar = board[r][c];

        // 연결된 것이 4개 넘는지 탐색
        while (!stack.isEmpty()) {
            final Integer[] current = stack.pop();
            int cr = current[0];
            int cc = current[1];

            for (int[] move :
                    moves) {
                int nr = cr + move[0];
                int nc = cc + move[1];
                // 범위를 벗어나거나 이미 방문했다면 또는, 같은 종류라면?
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || board[nr][nc] != baseChar) {
                    continue;
                }

                visited[nr][nc] = true;
                stack.push(new Integer[] { nr, nc });
                candidate.push(new Integer[] { nr, nc });
            }
        }

        deleted = candidate.size() >= 4;

        while (deleted && !candidate.isEmpty()) {
            final Integer[] current = candidate.pop();
            board[current[0]][current[1]] = '.';
        }

        return deleted;
    }

    private static void init() throws IOException {
        board = new char[R][];
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int r = R - 1; r >= 0; r--) {
            board[r] = br.readLine().toCharArray();
        }
    }

}
