package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SE4014_활주로건설 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, X;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int solve() throws IOException {
        int ret = 0;
        init();

        for (int r = 0; r < N; r++) {
            if (checkRow(r))
                ret++;
            if (checkCol(r))
                ret++;
        }

        return ret;
    }

    private static boolean checkCol(int c) {
        boolean[] checked = new boolean[N];

        for (int r = 0; r < N - 1; r++) {
            int gap = board[r][c] - board[r + 1][c];
            if (Math.abs(gap) > 1) return false;
            if (gap == 1) {
                // 앞이 더 크다 (뒤로 확인)
                int cnt = 0;
                int t = r + 1;
                while (t < N && cnt < X && board[t][c] == board[r + 1][c]) {
                    checked[t] = true;
                    cnt++;
                    t++;
                }
                if (cnt != X)
                    return false;
            } else if (gap == -1) {
                // 뒤가 더 크다 ( 앞으로 확인)
                int cnt = 0;
                int t = r;
                while (t >= 0 && cnt < X && board[t][c] == board[r][c] && !checked[t]) {
                    checked[t] = true;
                    cnt++;
                    t--;
                }
                if (cnt != X)
                    return false;
            }
        }
        return true;
    }

    private static boolean checkRow(int r) {
        boolean[] checked = new boolean[N];

        for (int c = 0; c < N - 1; c++) {
            int gap = board[r][c] - board[r][c + 1];

            if (Math.abs(gap) > 1) return false;

            if (gap == 1) {
                // 앞이 더 크면 뒤로 X 개 만큼 건설할 수 있는지 확인
                int cnt = 0;
                int t = c + 1;
                while (t < N && cnt < X && board[r][t] == board[r][c + 1]) {
                    checked[t] = true;
                    cnt++;
                    t++;
                }
                if (cnt != X)
                    return false;
            } else if (gap == -1) {
                // 뒤가 더 크면 앞으로 X 개 만큼 현재 위치랑 같은 사이즈인지 확인
                int cnt = 0;
                int t = c;
                while (t >= 0 && cnt < X && board[r][t] == board[r][c] && !checked[t]) {
                    checked[t] = true;
                    cnt++;
                    t--;
                }
                if (cnt != X)
                    return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
