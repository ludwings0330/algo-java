package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ1715_카드정렬 {
    static int N;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(answer);
    }

    private static void solve() {
        while (pq.size() > 1) {
            long a = pq.poll();
            answer += a;
            if (pq.isEmpty()) break;
            long b = pq.poll();
            answer += b;
            pq.add(a + b);
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        answer = 0;

        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }
    }
}
