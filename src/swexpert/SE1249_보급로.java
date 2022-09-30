package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SE1249_보급로 {
    static int TC;
    static int N;
    static int[][] board;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i++) {
            sb.append("#").append(i).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int solve() throws IOException {
        int answer = 0;

        init();
        answer = dijkstra();
        return answer;
    }

    private static int dijkstra() {
        int[][] dists = new int[N][N];

        for (int r = 0; r < N; r++) {
            Arrays.fill(dists[r], Integer.MAX_VALUE);
        }

        dists[0][0] = 0;
        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        PriorityQueue<Pair> hq = new PriorityQueue<Pair>();
        hq.offer(new Pair(0, 0, 0));
        while (!hq.isEmpty()) {
            Pair current = hq.poll();

            // 그사이에 dists 값이 변경되었을 수도 있으니까
            if (dists[current.r][current.c] < current.d)
                continue;

            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;

                int nextDistance = current.d + board[nr][nc];
                // 그냥 가는것보다 next 를 거쳐서 가는게 더 빠르면 업데이트
                if (nextDistance < dists[nr][nc]) {
                    dists[nr][nc] = nextDistance;
                    Pair next = new Pair(nr, nc, nextDistance);
                    hq.offer(next);
                }
            }
        }

        return dists[N - 1][N - 1];
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        board = new int[N][];
        for (int r = 0; r < N; r++) {
            board[r] = new int[N];
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                board[r][c] = tmp[c] - '0';
            }
        }
    }

    private static class Pair implements Comparable<Pair> {
        int r, c;
        int d;

        public Pair(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Pair o) {
            return this.d - o.d;
        }
    }
}
