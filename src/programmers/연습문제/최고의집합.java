package programmers.연습문제;

import java.util.Arrays;

public class 최고의집합 {
    public int[] solution(int n, int s) {
        int[] answer;

        if (n > s) {
            return new int[]{-1};
        }

        int base = s / n;
        answer = new int[n];
        Arrays.fill(answer, base);

        for (int cnt = 0, i = answer.length - 1; cnt < s % n; cnt++, i--) {
            answer[i]++;
        }

        return answer;
    }
}
