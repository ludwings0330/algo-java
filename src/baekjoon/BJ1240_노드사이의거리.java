package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1240_노드사이의거리 {
    static int N, M;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[s].add(new int[]{e, d});
            graph[e].add(new int[]{s, d});
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int ans = solve(s, e);
            System.out.println(ans);
        }

    }

    static int solve(int s, int e) {
        Queue<int[]> q = new LinkedList<>();
        int ret = 0;

        boolean[] visited = new boolean[N + 1];
        visited[s] = true;
        q.offer(new int[]{s, 0});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            if (current[0] == e)
                return current[1];
            for (int[] next :
                    graph[current[0]]) {
                if (visited[next[0]])
                    continue;
                visited[next[0]] = true;
                q.offer(new int[]{next[0], next[1] + current[1]});
            }
        }

        return -1;
    }
}
