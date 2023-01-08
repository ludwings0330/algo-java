package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1744_수묶기 {
    static int N;
    static int[] nums;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(answer);
    }

    // 음수, 음수, 양수, 양수
    // 음수, 양수
    // 음수, 음수, 0, 양수 양수, 양수
    // 음수, 0, 양수, 양수
    private static void solve() {
        if (N == 1) {
            answer = nums[0];
            return;
        }
        answer = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i % 2 == 0 && nums[i] < 0) {
                answer += nums[i];
                continue;
            }
            if ((i > 0 && nums[i] * nums[i - 1] > nums[i]) ||
                    (i > 0 && nums[i] == 0 && i % 2 == 1)) {
                answer += nums[i] * nums[i - 1];
                i--;
            } else {
                answer += nums[i];
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);
    }
}
