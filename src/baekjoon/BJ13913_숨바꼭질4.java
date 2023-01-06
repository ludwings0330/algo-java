package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ13913_숨바꼭질4 {
    static int N, K;
    static int[] cache;
    static List<Integer> answer = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        init();
        solve();
        print(N);
        if (K < N) {
            System.out.println(N - K);
            StringBuilder sb = new StringBuilder();
            for (int i = N; i >= K; i--) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString());
        } else {
            Collections.reverse(answer);
            System.out.println(answer.size() - 1);
            StringBuilder sb = new StringBuilder();
            answer.stream().forEach(n -> sb.append(n).append(" "));
            System.out.println(sb.toString());
        }

    }

    public static boolean print(int current) {
        if (current < 0 || current > 100000)
            return false;

        if (current == K) {
            answer.add(K);
            return true;
        }

        if (current + 1 < cache.length && cache[current + 1] == cache[current] + 1) {
            if (print(current + 1)) {
                answer.add(current);
                return true;
            }
        }

        if (0 < current - 1 && cache[current - 1] == cache[current] + 1) {
            if (print(current - 1)) {
                answer.add(current);
                return true;
            }
        }

        if (current * 2 < cache.length && cache[current * 2] == cache[current] + 1) {
            if (print(current * 2)) {
                answer.add(current);
                return true;
            }
        }

        return false;
    }

    public static void solve() {
        Queue<Integer> dq = new LinkedList<>();
        dq.add(N);
        cache[N] = 1;
        while (!dq.isEmpty()) {
            int current = dq.poll();
            if (current == K) {
                break;
            }
            if (current + 1 < cache.length && cache[current + 1] == -1) {
                cache[current + 1] = cache[current] + 1;
                dq.add(current + 1);
            }
            if (0 <= current - 1 && cache[current - 1] == -1) {
                cache[current - 1] = cache[current] + 1;
                dq.add(current - 1);
            }
            if (current * 2 < cache.length && cache[current * 2] == -1) {
                cache[current * 2] = cache[current] + 1;
                dq.add(current * 2);
            }
        }
    }

    public static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cache = new int[200000];
        Arrays.fill(cache, -1);
    }
}
