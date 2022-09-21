package codetree.NoviceHigh.그래프알고리즘.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ4485_녹색옷을입은애가젤다지 {
    static StringBuilder sb;
    static int N = -1;
    static int[][] board;
    static int[][] dists;
    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int ans;
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int tc = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            sb.append("Problem ").append(tc).append(": ").append(solve()).append("\n");
            tc++;
        }
        System.out.println(sb);
    }

    private static int solve() throws IOException {

        board = new int[N][N];
        for (int r = 0; r < N; r++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int ret = dijkstra(0, 0);
        return ret;
    }

    /**
     * 다익스트라 알고리즘은 Priority Qeue 를 활용
     * queue 에서 가까이 있는 node 를 구하고,
     * 해당 node 의 next 에 도착하기 위해 기존의 next 로 가는 경로가 가까운지
     * 이번 node 를 거쳐 next 가는게 가까운지 확인한다.
     * node 를 거쳐 next 로 가는게 더 가까우면 dists 에 update 가 발생한다.
     */
    private static int dijkstra(int sr, int sc) {
        dists = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dists[i], Integer.MAX_VALUE);
        }
        dists[0][0] = board[0][0];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sr, sc, dists[0][0]));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.dist > dists[current.r][current.c])
                continue;
            for (int i = 0; i < 4; i++) {
                int nr = current.r + moves[i][0];
                int nc = current.c + moves[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;
                if (dists[nr][nc] > current.dist + board[nr][nc]) {
                    dists[nr][nc] = current.dist + board[nr][nc];
                    pq.offer(new Node(nr, nc, dists[nr][nc]));
                }
            }
        }

        return dists[N - 1][N - 1];
    }

    private static class Node implements Comparable<Node> {
        int r, c;
        int dist;

        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

}
