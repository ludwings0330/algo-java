package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ5427_불 {

    static int TC;
    static int R, C;
    static char[][] board;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static LinkedList<Pos> fires;
    static LinkedList<Pos> sgs;

    static int[][] moves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < TC; testCase++) {
            init();
            solve();
        }
    }

    private static void solve() {
        int time = 0;

        while (true) {
            time++;
            if (checkEnd()) {
                break;
            }
            moveFires();
            moveSg();
        }

        System.out.println((!sgs.isEmpty()) ? time : "IMPOSSIBLE");
    }

    private static void moveSg() {
        LinkedList<Pos> newSgs = new LinkedList<>();
        for (Pos sg :
                sgs) {
            for (int[] move :
                    moves) {
                int nr = sg.r + move[0];
                int nc = sg.c + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                if (board[nr][nc] != '.') {
                    continue;
                }

                board[nr][nc] = '@';
                newSgs.push(new Pos(nr, nc));
            }
        }

        sgs = newSgs;
    }

    private static void moveFires() {
        LinkedList<Pos> newFires = new LinkedList<>();
        for (Pos fire :
                fires) {
            for (int[] move : moves) {
                int nr = fire.r + move[0];
                int nc = fire.c + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                if (board[nr][nc] == '#' || board[nr][nc] == '*') {
                    continue;
                }

                board[nr][nc] = '*';
                newFires.push(new Pos(nr, nc));
            }
        }

        fires = newFires;
    }

    private static boolean checkEnd() {
        if (sgs.isEmpty()) {
            return true;
        }

        for (Pos sg :
                sgs) {
            if (sg.r == 0 || sg.r == R - 1 || sg.c == 0 || sg.c == C - 1) {
                return true;
            }
        }

        return false;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        fires = new LinkedList<>();
        sgs = new LinkedList<>();

        board = new char[R][];
        for (int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();

            for (int c = 0; c < C; c++) {
                if (board[r][c] == '@') {
                    sgs.push(new Pos(r, c));
                } else if (board[r][c] == '*') {
                    fires.push(new Pos(r, c));
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
