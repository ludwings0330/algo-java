package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2636_치즈 {
    static int R, C;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Node> que;

    public static void main(String[] args) throws IOException {
        init();
        int remains = 0;
        int time = 0;
        while (que.size() != 0) {
            remains = que.size();
            time++;
            bfs();
        }
        System.out.println(time);
        System.out.println(remains);
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][];
        visited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            board[r] = new int[C];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        que = new LinkedList<>();

        visited[0][0] = true;
        que.offer(new Node(0, 0));
        bfs();
    }

    private static void bfs() {
        Queue<Node> tmp = new LinkedList<>();
        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!que.isEmpty()) {
            final Node current = que.poll();
            for (var move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                if (board[nr][nc] == 1) {
                    tmp.offer(new Node(nr, nc));
                } else {
                    que.offer(new Node(nr, nc));
                }
            }
        }

        que = tmp;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
