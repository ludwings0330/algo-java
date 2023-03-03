package codetree.크리스마스코딩퀴즈2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D_루돌프월드컵 {

    static double[] F;
    static int[] scores = new int[4];
    static List<int[]> matches = new ArrayList<>();
    static double answer = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        recursiveSolve(0, 1);
        System.out.println(Math.round(answer * 1000) / 1000.0);
        System.out.printf("%.3f", answer);
    }

    private static void recursiveSolve(int cnt, double probability) {
        if (cnt == matches.size()) {
            int win = 0;
            for (int i = 1; i < scores.length; i++) {
                if (scores[0] < scores[i]) {
                    win++;
                }
            }

            // 2등안에 들었으면 확률을 더한다.
            // 나보다 큰놈이 1명보다 작으면 뽑힌거;
            if (win <= 1) {
                answer += probability * 100;
            }

            return;
        }
        int a = matches.get(cnt)[0];
        int b = matches.get(cnt)[1];

        // 무승부
        double d = (F[a] + F[b]) / (5 * F[a] + 5 * F[b]);

        scores[a] += 1;
        scores[b] += 1;
        recursiveSolve(cnt + 1, probability * d);
        // 원복
        scores[a] -= 1;
        scores[b] -= 1;

        // a 승
        double w = ((double) 4 * F[a]) / (5 * F[a] + 5 * F[b]);
        double l = ((double) 4 * F[b]) / (5 * F[a] + 5 * F[b]);
        scores[a] += 3;
        recursiveSolve(cnt + 1, probability * w);
        // 원복
        scores[a] -= 3;

        // b 승
        // a가승 - 비길확률 이면
        scores[b] += 3;
        recursiveSolve(cnt + 1, probability * l);
        // 원복
        scores[b] -= 3;
    }


    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = new double[4];
        for (int i = 0; i < F.length; i++) {
            F[i] = Double.parseDouble(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                matches.add(new int[] { i, j });
            }
        }

        answer = 0;
    }

}
