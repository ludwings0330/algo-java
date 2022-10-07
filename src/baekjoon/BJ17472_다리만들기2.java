package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ17472_다리만들기2 {
    static int R, C;
    static int[][] board;
    static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static PriorityQueue<Pair> pq;

    static int group = 0;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        init();

        setGroup();

        getAllBridge();

        System.out.println(solve());
    }

    private static int solve() {
        boolean[] visited = new boolean[group + 1];

        parent = new int[group];
        for (int i = 0; i < group; i++) {
            parent[i] = i;
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            final Pair poll = pq.poll();

            if (!union(poll.from, poll.to))
                continue;

            visited[poll.from] = visited[poll.to] = true;
            ans += poll.dist;
        }
        for (int i = 1; i < group - 1; i++) {
            if (union(i, i + 1))
                return -1;
        }

        return ans;
    }

    private static void getAllBridge() {
        boolean[][] visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visited[r][c]) {
                    getAllBridge(r, c);
                    visited[r][c] = true;
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static int find(int x) {
        if (parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y)
            return false;

        parent[y] = x;

        return true;
    }

    private static void getAllBridge(int r, int c) {
        for (int[] move :
                moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (!inRange(nr, nc) || board[nr][nc] == board[r][c])
                continue;

            int dist = 0;
            while (inRange(nr, nc) && board[nr][nc] == 0) {
                nr += move[0];
                nc += move[1];
                dist++;
            }
            if (inRange(nr, nc) && dist > 1) {
                final Pair bridge = new Pair();
                bridge.from = board[r][c];
                bridge.to = board[nr][nc];
                bridge.dist = dist;

                pq.add(bridge);
            }
        }
    }

    private static void setGroup() {
        boolean[][] visited = new boolean[R][C];

        group = 1;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visited[r][c] && board[r][c] != 0)
                    setGroupBFS(r, c, group++, visited);
            }
        }
    }

    private static void setGroupBFS(int r, int c, int group, boolean[][] visited) {
        Queue<Pair> queue = new LinkedList<>();
        visited[r][c] = true;
        final Pair pair = new Pair(r, c);
        queue.offer(pair);
        board[r][c] = group;

        while (!queue.isEmpty()) {
            final Pair current = queue.poll();
            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || board[nr][nc] == 0)
                    continue;
                board[nr][nc] = group;
                visited[nr][nc] = true;
                queue.offer(new Pair(nr, nc));
            }
        }

    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][];

        for (int r = 0; r < R; r++) {
            board[r] = new int[C];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.dist - o2.dist;
            }
        });
    }

    private static class Pair {
        int r, c;
        int dist;
        int from;
        int to;
        int direction;

        public Pair() {
        }

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
