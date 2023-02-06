package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17837_새로운게임2 {

    static int N;
    static int[][] board;
    static int K;
    static Horse[] horses;
    static List<Horse>[][] horsesMap;
    static int[][] moves = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        int turn = 0;

        while (turn++ <= 1000) {
            for (Horse horse :
                    horses) {
                if (move(horse)) {
                    return turn;
                }
            }
        }

        return -1;
    }

    private static boolean move(Horse horse) {
        int r = horse.r;
        int c = horse.c;
        int d = horse.d;

        if (horsesMap[r][c].size() >= 4) {
            return true;
        }
        int index = horsesMap[r][c].indexOf(horse);
        List<Horse> candidate = new ArrayList<>(horsesMap[r][c].subList(index, horsesMap[r][c].size()));
        List<Horse> front = new ArrayList<>(horsesMap[r][c].subList(0, index));

        horsesMap[r][c] = front;

        int nr = r + moves[d][0];
        int nc = c + moves[d][1];

        if ((0 > nr || nr >= N || 0 > nc || nc >= N) || board[nr][nc] == 2) {
            // 파란색이거나 밖으로 나감
            if (d == 0 || d == 2) {
                d += 1;
            } else {
                d -= 1;
            }
            horse.d = d;
            nr = r + moves[d][0];
            nc = c + moves[d][1];

            if ((0 > nr || nr >= N || 0 > nc || nc >= N) || board[nr][nc] == 2) {
                // 이동하지 않는다.
                horsesMap[r][c].addAll(candidate);
            } else if (board[nr][nc] == 0) {
                for (Horse h :
                        candidate) {
                    h.r = nr;
                    h.c = nc;
                }
                horsesMap[nr][nc].addAll(candidate);
                if (horsesMap[nr][nc].size() >= 4) {
                    return true;
                }
            } else {
                // 빨간색
                for (Horse h :
                        candidate) {
                    h.r = nr;
                    h.c = nc;
                }
                Collections.reverse(candidate);
                horsesMap[nr][nc].addAll(candidate);
                if (horsesMap[nr][nc].size() >= 4) {
                    return true;
                }
            }

        } else if (board[nr][nc] == 1) {
            // 빨간색
            for (Horse h :
                    candidate) {
                h.r = nr;
                h.c = nc;
            }
            Collections.reverse(candidate);
            horsesMap[nr][nc].addAll(candidate);
            if (horsesMap[nr][nc].size() >= 4) {
                return true;
            }
        } else {
            // 흰색
            for (Horse h :
                    candidate) {
                h.r = nr;
                h.c = nc;
            }
            horsesMap[nr][nc].addAll(candidate);
            if (horsesMap[nr][nc].size() >= 4) {
                return true;
            }

        }

        return false;

    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][];
        horsesMap = new ArrayList[N][];
        for (int r = 0; r < N; r++) {
            board[r] = new int[N];
            horsesMap[r] = new ArrayList[N];

            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                horsesMap[r][c] = new ArrayList<>();
            }
        }

        horses = new Horse[K];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            horses[k] = new Horse(r, c, d);
            horsesMap[r][c].add(horses[k]);
        }
    }

    enum Color {WHITE, RED, BLUE}

    static class Horse {

        int r, c, d;

        public Horse(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

    }

}
