package programmers.practice;

import java.util.Arrays;

public class 멀리뛰기 {
    final static int MOD = 1234567;
    long[] cache;

    public long solution(int n) {
        cache = new long[2001];
        Arrays.fill(cache, -1);

        long answer = jump(0, n);

        return answer;
    }

    private long jump(int current, int n) {
        if (current == n) {
            return 1;
        } else if (current > n) {
            return 0;
        }

        if (cache[current] != -1)
            return cache[current];

        long ret = jump(current + 1, n) + jump(current + 2, n);

        return cache[current] = ret % MOD;
    }
}
