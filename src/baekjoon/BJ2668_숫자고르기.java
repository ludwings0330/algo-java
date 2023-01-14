package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BJ2668_숫자고르기 {

    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] nums;
    static Set<Integer> answer = new HashSet<>();

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int start = 1; start <= N; start++) {
            dfs(start);
        }

        System.out.println(answer.size());
        answer.stream().sorted().forEach(n -> System.out.println(n));
    }

    private static void dfs(int start) {
        boolean[] visited = new boolean[N + 1];
        Set<Integer> upper = new HashSet<>();
        Set<Integer> lower = new HashSet<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            int next = nums[current];

            visited[current] = true;
            upper.add(current);
            lower.add(next);

            if (visited[next]) {
                continue;
            }

            stack.add(next);
        }

        if (upper.size() == lower.size()) {
            answer.addAll(upper);
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
    }

}
