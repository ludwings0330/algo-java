package programmers.연습문제;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 야근지수 {
    PriorityQueue<Integer> pqWork;

    public long solution(int n, int[] works) {
        long answer = 0;
        pqWork = new PriorityQueue<>(Comparator.reverseOrder());
        for (var work :
                works) {
            pqWork.offer(work);
        }

        while (!pqWork.isEmpty() && n-- > 0) {
            int t = pqWork.poll();
            t -= 1;
            if (t <= 0) continue;
            pqWork.offer(t);
        }

        while (!pqWork.isEmpty()) {
            int t = pqWork.poll();
            answer += (long) t * t;
        }

        return answer;
    }
}
