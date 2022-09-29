package programmers.연습문제;

public class 타일링 {
    public int solution(int n) {
        int answer = 0;
        int[] cache = new int[60_001];
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
            cache[i] %= 1000000007;
        }

        return answer = cache[n];
    }
}
