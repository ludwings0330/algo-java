package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ1941_소문난칠공주 {

    static char[][] board;
    static int[] selected;
    static boolean[][] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        return combSolve(0, -1);
    }

    private static int combSolve(int count, int idx) {
        if (count == 7) {
            if (isConnected() && isUpperDasom()) {
                return 1;
            }
            return 0;
        }
        if (idx >= 25) {
            return 0;
        }

        int ret = 0;
        for (int next = idx + 1; next < 5 * 5; next++) {
            visited[next / 5][next % 5] = true;
            selected[count] = next;

            ret += combSolve(count + 1, next);

            selected[count] = -1;
            visited[next / 5][next % 5] = false;
        }

        return ret;
    }

    private static boolean isUpperDasom() {
        int dasom = 0;
        int doyeon = 0;
        for (int idx :
                selected) {
            if (board[idx / 5][idx % 5] == 'S') {
                dasom++;
            } else {
                doyeon++;
            }
        }
        return dasom > doyeon;
    }

    private static boolean isConnected() {
        int[][] moves = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int count = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        boolean[][] dfsVisit = new boolean[5][5];

        stack.push(selected[0]);
        count++;
        dfsVisit[selected[0] / 5][selected[0] % 5] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();

            int r = current / 5;
            int c = current % 5;

            for (int[] move :
                    moves) {
                int nr = r + move[0];
                int nc = c + move[1];
                if (nr < 0 || 5 <= nr || nc < 0 || 5 <= nc) {
                    continue;
                }

                if (!visited[nr][nc] || dfsVisit[nr][nc]) {
                    continue;
                }

                dfsVisit[nr][nc] = true;
                stack.push(nr * 5 + nc);
                count++;
            }
        }

        return count == 7;
    }

    private static void init() throws Exception {
        board = new char[5][];
        visited = new boolean[5][5];
        selected = new int[7];
        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }
    }

}
