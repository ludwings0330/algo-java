package codetree.NoviceHigh.그래프알고리즘.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ2665_미로만들기 {
    static char[][] board;
    static int N;


    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][];

        for (int r = 0; r < N; r++) {
            board[r] = br.readLine().toCharArray();
        }

        int result = dijkstra(0, 0);
        System.out.println(result);
    }

    private static int dijkstra(int r, int c) {
        int[][] dists = new int[N][N];
        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int mr = 0; mr < N; mr++) {
            for (int mc = 0; mc < N; mc++) {
                dists[mr][mc] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Room> pq = new PriorityQueue<Room>();
        dists[0][0] = 0;
        pq.offer(new Room(0, 0, 0));

        while (!pq.isEmpty()) {
            Room current = pq.poll();

            // 들어온 값이 이미 크면 확인할 필요도 없음
            if (dists[current.r][current.c] < current.destroy)
                continue;

            for (int i = 0; i < 4; i++) {
                int nr = current.r + moves[i][0];
                int nc = current.c + moves[i][1];

                if (0 > nr || nr >= N || nc < 0 || nc >= N) continue;

                int nd = current.destroy + ((board[nr][nc] == '1') ? 0 : 1);

                // 원래 가는 경로보다 거쳐서가는 경로가 더 가까우면 업데이트
                if (dists[nr][nc] > nd) {
                    dists[nr][nc] = nd;
                    pq.offer(new Room(nr, nc, nd));
                }
            }
        }

        return dists[N - 1][N - 1];
    }

    private static class Room implements Comparable<Room> {
        int r, c;
        int destroy;

        public Room(int r, int c, int destroy) {
            this.r = r;
            this.c = c;
            this.destroy = destroy;
        }

        @Override
        public int compareTo(Room o) {
            return this.destroy - o.destroy;
        }
    }
}
