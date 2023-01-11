package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1034_램프 {

    static int R, C, K;
    static boolean[][] lamps;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int r = 0; r < R; r++) {
            List<Integer> cols = new ArrayList<>();
            for (int c = 0; c < C; c++) {
                if (!lamps[r][c]) {
                    cols.add(c);
                }
            }

            if (cols.size() > K || (K - cols.size()) % 2 == 1) {
                continue;
            }

            int count = 0;
            for (int rr = 0; rr < R; rr++) {
                for (int cc :
                        cols) {
                    lamps[rr][cc] = !lamps[rr][cc];
                }
                boolean on = true;
                for (int cc = 0; cc < C; cc++) {
                    if (!lamps[rr][cc]) {
                        on = false;
                        break;
                    }
                }
                if (on) {
                    count++;
                }
                for (int cc :
                        cols) {
                    lamps[rr][cc] = !lamps[rr][cc];
                }
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        lamps = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            int c = 0;
            for (char ch :
                    br.readLine().toCharArray()) {
                if (ch == '1') {
                    lamps[r][c] = true;
                }
                c++;
            }
        }

        K = Integer.parseInt(br.readLine());
    }

}
