package programmers.summerwintercoding2018;

import java.util.Arrays;

public class 숫자게임 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < B[i])
                answer++;
        }
        return answer;
    }
}
