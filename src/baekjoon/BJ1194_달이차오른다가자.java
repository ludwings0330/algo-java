package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1194_달이차오른다가자 {
    static int R, C;
    static char[][] board;
    static Pos start;
    static HashSet<Integer>[][] visited;
    static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.r][start.c].add(0);

        int ret = -1;
        while (!queue.isEmpty()) {
            final Pos current = queue.poll();

            if (board[current.r][current.c] == '1') {
                ret = current.dist;
                break;
            }
            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
                        board[nr][nc] == '#' || visited[nr][nc].contains(current.key))
                    continue;
                visited[nr][nc].add(current.key);
                final Pos next = new Pos(nr, nc, current.dist, current.key);

                if ('A' <= board[nr][nc] && board[nr][nc] <= 'Z') {
                    // 열쇠 없으면 continue;
                    if ((next.key & (1 << (board[nr][nc] - 'A'))) == 0)
                        continue;
                } else if ('a' <= board[nr][nc] && board[nr][nc] <= 'z') {
                    next.key = (next.key | (1 << (board[nr][nc] - 'a')));
                }
                next.dist += 1;
                queue.offer(next);
            }
        }

        return ret;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new HashSet[R][C];

        for (int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (board[r][c] == '0')
                    start = new Pos(r, c, 0, 0);

                visited[r][c] = new HashSet<>();
            }
        }
    }

    private static class Pos {
        int r, c, dist;
        int key;

        public Pos(int r, int c, int dist, int key) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.key = key;
        }
    }
}
