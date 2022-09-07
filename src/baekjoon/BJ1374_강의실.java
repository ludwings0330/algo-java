package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1374_강의실 {
    static int N;
    static PriorityQueue<Lecture> dq = new PriorityQueue<>(new Comparator<Lecture>() {
        @Override
        public int compare(Lecture o1, Lecture o2) {
            return o1.end - o2.end;
        }
    });
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new Lecture(no, start, end));
        }

        while (!pq.isEmpty()) {
            Lecture current = pq.poll();

            while (!dq.isEmpty() && dq.peek().end <= current.start)
                dq.poll();

            dq.offer(current);
            ans = Math.max(ans, dq.size());
        }
        System.out.println(ans);
    }

    private static class Lecture implements Comparable<Lecture> {
        int start;
        int end;
        int no;

        public Lecture(int no, int start, int end) {
            this.start = start;
            this.end = end;
            this.no = no;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start != o.start)
                return this.start - o.start;
            return this.end - o.end;
        }
    }
}
