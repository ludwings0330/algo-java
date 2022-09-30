package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1600_말이되고픈원숭이 {
    static int K;
    static int R, C;
    static int[][] board;
    static int moves[][] = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1},
            {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, 1}, {2, -1}};

    public static void main(String[] args) throws IOException {
        init();
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(0, 0, K, 0));

        int answer = -1;
        boolean[][][] visited = new boolean[R][C][K + 1];

        while (!queue.isEmpty()) {
            Info current = queue.poll();
            if (current.r == R - 1 && current.c == C - 1) {
                answer = current.dist;
                break;
            }

            for (int i = 0; i < moves.length; i++) {
                int nr = current.r + moves[i][0];
                int nc = current.c + moves[i][1];
                int nk = (i >= 4) ? current.jump - 1 : current.jump;

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || nk < 0 ||
                        visited[nr][nc][nk] || board[nr][nc] == 1)
                    continue;

                visited[nr][nc][nk] = true;
                queue.offer(new Info(nr, nc, nk, current.dist + 1));
            }
        }
        System.out.println(answer);
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Info {
        int r, c, jump, dist;

        public Info(int r, int c, int jump, int dist) {
            this.r = r;
            this.c = c;
            this.jump = jump;
            this.dist = dist;
        }
    }
}
