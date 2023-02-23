package codetree.크리스마스코딩퀴즈2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C_연탄의크기 {

    static int N;
    static int[] outerRadius;
    static int[] cnt = new int[100 + 1];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {

        for (int radius :
                outerRadius) {
            factorization(radius);
        }
        return Arrays.stream(cnt).max().getAsInt();
    }

    private static void factorization(int radius) {
        for (int i = 2; i <= radius; i++) {
            if (radius % i == 0) {
                cnt[i]++;
            }
        }
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        outerRadius = new int[N];
        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            outerRadius[i] = Integer.parseInt(st.nextToken());
        }
    }

}
