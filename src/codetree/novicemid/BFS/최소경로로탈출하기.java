package codetree.novicemid.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 최소경로로탈출하기 {

    static int N, M;
    static int[][] board;
    static int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            board[r] = new int[M];

            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        Deque<Integer[]> deque = new LinkedList<>();
        deque.offerLast(new Integer[]{0, 0, 0});
        while (!deque.isEmpty()) {
            Integer[] current = deque.pollFirst();
            if (current[0] == N - 1 && current[1] == M - 1) {
                System.out.println(current[2]);
                return;
            }
            for (int[] move :
                    moves) {
                int nr = current[0] + move[0];
                int nc = current[1] + move[1];

                if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && board[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    deque.offerLast(new Integer[]{nr, nc, current[2] + 1});
                }
            }
        }
        System.out.println(-1);
        return;
    }
}
