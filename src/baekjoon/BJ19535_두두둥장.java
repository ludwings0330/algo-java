package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ19535_두두둥장 {
    static int N;
    static List<Integer>[] graph;
    static boolean[] visited;
    static long d = 0;
    static long g = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        visited[1] = true;
        dfs(1);
        visited[1] = false;

        if (d > g * 3) {
            System.out.println('D');
        } else if (d < g * 3) {
            System.out.println('G');
        } else {
            System.out.println("DUDUDUNGA");
        }

    }

    private static void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (graph[current].size() >= 3) {
                int n = graph[current].size();
                g += ((long) (n) * (n - 1) * (n - 2)) / (3L * 2);
            }

            for (int next :
                    graph[current]) {

                if (visited[next])
                    continue;

                visited[next] = true;
                d += (Math.max(0l, graph[current].size() - 1)) * (Math.max(0, graph[next].size() - 1));
                stack.push(next);
            }
        }
    }
}
