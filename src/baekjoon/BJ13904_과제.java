package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13904_과제 {

    static int N;
    static HomeWork[] hws;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        PriorityQueue<HomeWork> pq = new PriorityQueue<>();

        Arrays.sort(hws, new Comparator<HomeWork>() {
            @Override
            public int compare(HomeWork o1, HomeWork o2) {
                return -o1.d + o2.d;
            }
        });

        int day = hws[0].d;
        int idx = 0;
        while (day > 0) {
            while (idx < hws.length && hws[idx].d >= day) {
                pq.add(hws[idx++]);
            }
            if (!pq.isEmpty()) {
                answer += pq.poll().w;
            }
            day--;
        }

        System.out.println(answer);
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = 0;

        N = Integer.parseInt(br.readLine());

        hws = new HomeWork[N];
        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            hws[i] = new HomeWork(d, w);
        }
    }

    static class HomeWork implements Comparable<HomeWork> {

        int d;
        int w;

        public HomeWork(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(HomeWork o) {
            return -this.w + o.w;
        }

    }

}
