package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2573_빙산 {
    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[][] board;
    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int year = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(year);
    }

    private static void solve() {
        while (!isEnd()) {
            year++;
            List<Pair> ice = new ArrayList<>();
            findIce(ice);
            melt(ice);
        }
    }

    private static void melt(List<Pair> ice) {
        int[][] tBoard = new int[N][M];

        for (Pair current :
                ice) {
            tBoard[current.r][current.c] = Math.max(0, board[current.r][current.c] - getSurround(current.r, current.c));
        }
        board = tBoard;
    }

    private static int getSurround(int r, int c) {
        int count = 0;
        for (int[] move :
                moves) {
            int nr = r + move[0];
            int nc = c + move[1];
            if (inRange(nr, nc)) {
                if (board[nr][nc] == 0)
                    count++;
            }
        }

        return count;
    }

    private static void findIce(List<Pair> ice) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] != 0) {
                    ice.add(new Pair(r, c));
                }
            }
        }
    }

    private static boolean isEnd() {
        // 모두녹았을때
        // 대륙이 하나일때,
        // 대륙이 두개일때
        int countContinents = 0;
        boolean[][] visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] != 0 && !visited[r][c]) {
                    countContinents++;
                    bfs(r, c, visited);
                }
            }
        }

        if (countContinents == 1) {
            return false;
        } else {
            if (countContinents == 0)
                year = 0;
            return true;
        }
    }

    private static void bfs(int r, int c, boolean[][] visited) {
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(r, c));
        visited[r][c] = true;

        while (!que.isEmpty()) {
            final Pair current = que.poll();

            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];
                if (inRange(nr, nc) && canMove(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    que.add(new Pair(nr, nc));
                }
            }
        }
    }

    private static boolean canMove(int nr, int nc) {
        return board[nr][nc] != 0;
    }

    private static boolean inRange(int nr, int nc) {
        return 0 <= nr && nr < N && 0 <= nc && nc < M;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][];
        for (int r = 0; r < N; r++) {
            board[r] = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
