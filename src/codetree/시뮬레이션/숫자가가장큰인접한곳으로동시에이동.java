package codetree.시뮬레이션;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 숫자가가장큰인접한곳으로동시에이동 {
    static int N, M, T;
    static int[][] board;
    static int[][] cnt;

    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<Bead> beads = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        cnt = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            beads.add(new Bead(r, c));
        }

        while (T > 0) {
            T--;
            simulation();
        }

        int ans = beads.size();
        System.out.println(ans);
    }

    private static void simulation() {
        moveAllBeads();
    }

    private static void moveAllBeads() {
        List<Bead> nextBeads = new ArrayList<>();

        for (Bead bead :
                beads) {
            cnt[bead.r][bead.c] = 0;
        }

        for (Bead current :
                beads) {
            int max = 0;
            for (int i = 0; i < 4; i++) {
                int nr = current.r + moves[i][0];
                int nc = current.c + moves[i][1];

                if (!inRange(nr, nc))
                    continue;

                max = Math.max(max, board[nr][nc]);
            }

            for (int i = 0; i < 4; i++) {
                int nr = current.r + moves[i][0];
                int nc = current.c + moves[i][1];

                if (!inRange(nr, nc))
                    continue;

                if (board[nr][nc] == max) {
                    cnt[nr][nc]++;
                    nextBeads.add(new Bead(nr, nc));
                    break;
                }
            }
        }

        beads.clear();
        for (Bead bead :
                nextBeads) {
            if (cnt[bead.r][bead.c] >= 2)
                continue;
            beads.add(bead);
        }
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    private static class Bead {
        int r, c;

        public Bead(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
