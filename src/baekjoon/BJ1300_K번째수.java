package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1300_K번째수 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        int ret = 0;

        int left = 1, right = K;
        // [left, right]
        while (left < right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(N, mid / i);
            }
            // 모자르면 숫자를 더 크게 해줘야지
            if (cnt < K) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
    }

}
