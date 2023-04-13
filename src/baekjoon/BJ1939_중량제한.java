package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ1939_중량제한 {

    static int N, M;
    static int start, end;
    static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();

        int answer = solve();

        System.out.println(answer);
    }

    private static int solve() {
        int left = 1;
        int right = 1_000_000_000;

        // [left, right]
        while (left <= right) {
            int mid = (left + right) / 2;

            // 가는 길이 존재하면 mid 가 정답일 가능 성이 있으므로 포함해야해 -> o
            // 가는 길이 존재하면 mid 를 더 키워서 테스트. 근데 mid 가 정답일 가능성이 있음.
            if (isExist(mid)) {
                left = mid + 1;
            } else {
                // 가는 길이 존재하지않으면, mid 는 정답이 될 가능성이 없으므로 제외
                right = mid - 1;
            }

        }

        return (left + right) / 2;
    }

    private static boolean isExist(int target) {
        boolean[] visited = new boolean[N + 1];

        visited[start] = true;
        Stack<Integer> stack = new Stack<>();

        final Map<Integer, Integer> temp = graph.get(start);

        for (Entry<Integer, Integer> set :
                temp.entrySet()) {
            int to = set.getKey();
            int cost = set.getValue();
            if (cost < target) {
                continue;
            }
            stack.push(to);
            visited[to] = true;
        }

        while (!stack.isEmpty()) {
            final Integer fr = stack.pop();
            if (fr == end) {
                return true;
            }

            for (Entry<Integer, Integer> set : graph.get(fr).entrySet()) {
                int to = set.getKey();
                int cost = set.getValue();

                if (visited[to] || cost < target) {
                    continue;
                }

                visited[to] = true;
                stack.push(to);
            }
        }

        return visited[end];
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Map<Integer, Integer> temp = graph.getOrDefault(fr, new HashMap<>());
            temp.merge(to, cost, (v1, v2) -> (v1 > v2) ? v1 : v2);
            graph.put(fr, temp);

            temp = graph.getOrDefault(to, new HashMap<>());
            temp.merge(fr, cost, (v1, v2) -> (v1 > v2) ? v1 : v2);
            graph.put(to, temp);

        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

}
