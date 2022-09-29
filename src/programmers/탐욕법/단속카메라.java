package programmers.탐욕법;

import java.util.PriorityQueue;

public class 단속카메라 {
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int[] route :
                routes) {
            Info info = new Info(route[0], route[1]);
            pq.offer(info);
        }

        while (!pq.isEmpty()) {
            answer++;
            int end = pq.poll().end;
            while (!pq.isEmpty() && pq.peek().start <= end) pq.poll();
        }

        return answer;
    }

    class Info implements Comparable<Info> {
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            return this.end - o.end;
        }
    }
}
