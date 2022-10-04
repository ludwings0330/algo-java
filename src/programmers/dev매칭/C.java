package programmers.dev매칭;

import java.util.Arrays;

public class C {
    // 0 - 6
    // 1 - 2
    // 2 - 5
    // 3 - 5
    // 4 - 4
    // 5 - 5
    // 6 - 6
    // 7 - 3
    // 8 - 7
    // 9 - 6
    static long[] cache;

    public static long solution(int k) {
        long answer = 0;
        cache = new long[51];

        Arrays.fill(cache, -1);

        if (k == 6) return 7;
        // 모든 경우(0이 맨앞) - 맨앞에 0 이 있는 경우
        answer = recursiveSolve(k) - recursiveSolve(k - 6);
        return answer;
    }

    private static long recursiveSolve(int k) {
        // 숫자를 완성한 경우
        if (k == 0)
            return 1;

        // 성냥개비가 남으면 안된다.
        if (k < 2)
            return 0;

        // 이미 계산한 값이라면? 그대로 리턴
        if (cache[k] != -1)
            return cache[k];

        cache[k] = 0;
        long ret = 0;
        // 현재 숫자를 2개로 만들고 다음 숫자를 결정 1
        ret += recursiveSolve(k - 2);
        // 7
        ret += recursiveSolve(k - 3);
        // 4
        ret += recursiveSolve(k - 4);
        // 2, 3, 5
        ret += 3 * recursiveSolve(k - 5);
        // 0, 6, 9
        ret += 3 * recursiveSolve(k - 6);
        // 8
        ret += recursiveSolve(k - 7);

        return cache[k] = ret;
    }

    public static void main(String[] args) {
//        System.out.println(solution(5));
        System.out.println(solution(0));
        System.out.println(solution(1));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(5));
        System.out.println(solution(8));
        System.out.println(solution(11));
        System.out.println(solution(1));
    }
}
