package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벽돌깨기 {
    static int T;
    static int N, C, R;
    static int[][] board;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] choice;
    static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            init();
            sb.append("#").append(i).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int solve() {
        int ret = 0;

        ret = perm(0);

        return ret;
    }

    private static int perm(int count) {
        if (count == N) {
            return simulate();
        }

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < C; i++) {
            choice[count] = i;
            ret = Math.min(ret, perm(count + 1));
        }

        return ret;
    }

    private static int simulate() {
        int[][] tBoard = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                tBoard[r][c] = board[r][c];
            }
        }

        for (int i = 0; i < N; i++) {
            int peekCol = choice[i];
            int peekRow = getFirstBlockRow(tBoard, peekCol);

            if (peekRow == -1) continue;

            recursiveRemove(tBoard, peekRow, peekCol);
            compressRow(tBoard);
        }

        return countBlocks(tBoard);
    }

    private static void compressRow(int[][] tBoard) {
        for (int c = 0; c < C; c++) {
            for (int r = R - 1; r >= 0; r--) {
                if (tBoard[r][c] == 0) {
                    int nr = r;
                    while (0 < nr && tBoard[nr][c] == 0) {
                        nr--;
                    }
                    tBoard[r][c] = tBoard[nr][c];
                    tBoard[nr][c] = 0;
                }
            }
        }
    }

    private static int countBlocks(int[][] tBoard) {
        int cnt = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (tBoard[r][c] != 0)
                    cnt++;
            }
        }

        return cnt;
    }

    private static void recursiveRemove(int[][] tBoard, int r, int c) {
        int k = tBoard[r][c];
        tBoard[r][c] = 0;

        for (int[] move :
                moves) {
            int nr = r;
            int nc = c;

            for (int i = 0; i < k - 1; i++) {
                nr += move[0];
                nc += move[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                    continue;

                recursiveRemove(tBoard, nr, nc);
            }
        }
    }

    private static int getFirstBlockRow(int[][] tBoard, int c) {
        for (int i = 0; i < R; i++) {
            if (tBoard[i][c] != 0)
                return i;
        }

        return -1;
    }

    public static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        choice = new int[N];

        board = new int[R][];
        for (int r = 0; r < R; r++) {
            board[r] = new int[C];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
