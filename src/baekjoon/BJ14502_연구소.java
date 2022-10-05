package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502_연구소 {
    static int R, C;
    static int[][] board;
    static Pair[] choice;
    static int totalSafeArea;
    private static Queue<Pair> virus;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(perm(0));
    }

    private static int perm(int count) {
        if (count == 3) {
            return simulate();
        }
        int ret = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] == 0) {
                    board[r][c] = 1;
                    ret = Math.max(ret, perm(count + 1));
                    board[r][c] = 0;
                }
            }
        }
        return ret;
    }

    private static int simulate() {
        boolean[][] visited = new boolean[R][C];

        Queue<Pair> queue = new LinkedList<>(virus);

        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int visitCnt = queue.size();

        while (!queue.isEmpty()) {
            final Pair current = queue.poll();
            for (var move : moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
                        visited[nr][nc] || board[nr][nc] == 1 || board[nr][nc] == 2)
                    continue;

                visited[nr][nc] = true;
                visitCnt++;
                queue.offer(new Pair(nr, nc));
            }
        }

        return totalSafeArea - visitCnt;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][];
        virus = new LinkedList<>();
        totalSafeArea = -3;

        for (int r = 0; r < R; r++) {
            board[r] = new int[C];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if (board[r][c] == 2)
                    virus.offer(new Pair(r, c));
                if (board[r][c] != 1)
                    totalSafeArea++;
            }
        }

        choice = new Pair[3];
    }

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
