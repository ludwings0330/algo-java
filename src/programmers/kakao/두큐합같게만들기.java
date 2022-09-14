package programmers.kakao;

import java.util.LinkedList;
import java.util.Queue;

public class 두큐합같게만들기 {
    public static void main(String[] args) {

        System.out.println(solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
        System.out.println(solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
        System.out.println(solution(new int[]{1, 1}, new int[]{1, 5}));
    }

    public static int solution(int[] queue1, int[] queue2) {
        int answer = -1;

        long sum1 = 0;
        long sum2 = 0;


        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for (int j : queue1) {
            q1.add(j);
            sum1 += j;
        }

        for (int j : queue2) {
            q2.add(j);
            sum2 += j;
        }
        int cnt = 0;

        while (cnt++ < 600_000) {
            if (sum1 > sum2) {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.offer(q1.poll());

            } else if (sum1 < sum2) {
                sum2 -= q2.peek();
                sum1 += q2.peek();
                q1.offer(q2.poll());
            } else {
                answer = cnt - 1;
                break;
            }
        }

        return answer;
    }

}
