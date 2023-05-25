package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ4179_불 {

    static int R, C;
    static char[][] board;
    static int[][] moves = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    static LinkedList<Pos> jh;
    static LinkedList<Pos> fires;

    public static void main(String[] args) throws IOException {
        init();
        int answer = solve();

        System.out.println((answer > -1) ? answer : "IMPOSSIBLE");
    }

    private static int solve() {
        int ret = 0;
        boolean terminated = false;

        while (!terminated) {
            terminated = process();
            ret++;
        }

        return (!jh.isEmpty()) ? ret : -1;
    }

    private static boolean process() {
        LinkedList<Pos> newFires = new LinkedList<>();

        // 1. 불 전파
        while (!fires.isEmpty()) {
            final Pos current = fires.pop();

            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || board[nr][nc] == 'F' || board[nr][nc] == '#') {
                    continue;
                }

                board[nr][nc] = 'F';
                newFires.add(new Pos(nr, nc));
            }
        }

        fires = newFires;
        // 2. 지훈 이동
        LinkedList<Pos> newJh = new LinkedList<>();
        while (!jh.isEmpty()) {
            Pos current = jh.pop();
            if (current.c == 0 || current.c == C - 1 || current.r == 0 || current.r == R - 1) {
                jh.add(current);
                return true;
            }

            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || board[nr][nc] != '.') {
                    continue;
                }

                board[nr][nc] = 'J';
                newJh.add(new Pos(nr, nc));
            }
        }

        jh = newJh;

        return jh.isEmpty();
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        fires = new LinkedList<>();
        jh = new LinkedList<>();
        board = new char[R][];

        for (int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                switch (board[r][c]) {
                    case 'J':
                        jh.add(new Pos(r, c));
                        break;
                    case 'F':
                        fires.add(new Pos(r, c));
                        break;
                }
            }
        }

    }

    static class Pos {

        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

}
