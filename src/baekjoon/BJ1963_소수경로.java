package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1963_소수경로 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] prime = new boolean[10000];

    public static void main(String[] args) throws IOException {
        Arrays.fill(prime, true);

        for (int i = 2; i < 10000; i++) {
            int k = i;
            if (!prime[k]) {
                continue;
            }
            while (k + i < 10000) {
                k += i;
                prime[k] = false;
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            solve();
        }
    }

    private static void solve() throws IOException {
        int answer = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int fr = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[10000];
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[] { fr, 0 });
        visited[fr] = true;

        while (!que.isEmpty()) {
            final Integer[] current = que.poll();
            if (current[0] == to) {
                answer = current[1];
                break;
            }
            // 1000
            for (int i = 0; i < 10; i++) {
                for (int j = 1; j <= 1000; j *= 10) {
                    int next = current[0] - ((current[0] % (j * 10)) / j) * j + i * j;
                    // 방문했거나, 소수가 아니라면 무시
                    if (next < 1000 || visited[next] || !prime[next]) {
                        continue;
                    }
                    // 처음방문한 소수여야 최소횟수로 이동한것 + 방문 표시
                    visited[next] = true;
                    que.add(new Integer[] { next, current[1] + 1 });
                }
            }
        }
        System.out.println((answer == -1) ? "Impossible" : answer);
    }

}
